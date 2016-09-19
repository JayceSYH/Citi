package edu.nju.service.TradeService;

import edu.nju.model.*;
import edu.nju.service.AssetManagementService.AssetManagementService;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.ExceptionsAndError.NoSuchProductException;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.ExceptionsAndError.NothingToRedeemException;
import edu.nju.service.POJO.SimpleTradeInfo;
import edu.nju.service.PayService.PayService;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.UserService.UserService;
import edu.nju.service.Utils.MD5Utils;
import edu.nju.service.Utils.TimeTransformation;
import edu.nju.vo.OrderResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    UserService userService;
    @Autowired
    SearchService searchService;
    @Autowired
    AssetManagementService assetManagementService;
    @Autowired
    PayService payService;
    
    //10 minutes
    private final long expiration = 10 * 60 * 1000;

    @Override
    public OrderResultVO buyProduct(List<SimpleTradeInfo> tradeInfoList, FinanceCityUser financeCityUser) throws NotLoginException, NoSuchProductException {
        Timestamp expiration_time = new Timestamp(System.currentTimeMillis() + expiration);
        String checkCode = generateMD5(financeCityUser, tradeInfoList);
        double total_amount = 0;

        try {
            for (SimpleTradeInfo simpleTradeInfo : tradeInfoList) {
                UnpaidItem unpaidItem = new UnpaidItem();
                unpaidItem.setUserId(financeCityUser.getID());
                unpaidItem.setExpirationTime(expiration_time);
                unpaidItem.setTradingVolume(new BigDecimal(simpleTradeInfo.getAmount()));
                unpaidItem.setProductId(simpleTradeInfo.getProductId());
                unpaidItem.setCheckCode(checkCode);

                searchService.getProductByID(unpaidItem.getProductId());

                total_amount += unpaidItem.getTradingVolume().doubleValue();

                userService.getUserDao(financeCityUser).save(unpaidItem);
            }

            OrderResultVO orderResultVO = new OrderResultVO();
            orderResultVO.setCheckCode(checkCode);
            orderResultVO.setAmount(String.valueOf(total_amount));

            return orderResultVO;
        }
        catch (Exception e) {
            cancelUnpaid(checkCode, financeCityUser);
            throw e;
        }
    }



    @SuppressWarnings("unchecked")
    @Override
    public boolean cancelUnpaid(String checkCode, FinanceCityUser financeCityUser) {
        try {
            userService.getUserDao(financeCityUser).query("DELETE FROM UnpaidItem unpaidItem WHERE unpaidItem.checkCode='" +
            checkCode + "' AND unpaidItem.userId=" + financeCityUser.getID());

            return true;
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelUnpaid(int product_id, FinanceCityUser financeCityUser) {
        try {
            userService.getUserDao(financeCityUser).query("DELETE FROM UnpaidItem unpaidItem WHERE unpaidItem.productId=" +
                    product_id + " AND unpaidItem.userId=" + financeCityUser.getID());

            return true;
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelAllUnpaid(FinanceCityUser financeCityUser) {
        try {
            userService.getUserDao(financeCityUser).query("DELETE UnpaidItem u WHERE u.userId=" + financeCityUser.getID());
            return true;
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean redeemProduct(String checkCode, int productId, FinanceCityUser financeCityUser) throws NotLoginException, NothingToRedeemException {
        if (payService.redeemProduct()) {
            List list = userService.getCommonDao().
                    find("FROM InvestmentPortfolio i WHERE i.checkCode='" + checkCode + "'");

            if (list == null || list.size() == 0) {
                throw new NothingToRedeemException();
            }

            InvestmentPortfolio investmentPortfolio = (InvestmentPortfolio)list.get(0);

            list = userService.getUserDao(financeCityUser).
                    find("FROM InvestedProducts i WHERE i.portfolioId=" + investmentPortfolio.getId() +
                            " AND i.productId=" + productId + " AND i.state=1");
            if (list == null || list.size() == 0) {
                throw new NothingToRedeemException();
            }

            InvestedProducts investedProducts = (InvestedProducts)list.get(0);

            TradeHistory tradeHistory = new TradeHistory();
            tradeHistory.setTradeType("redeem");
            tradeHistory.setTradingVolume(investedProducts.getTotalAmount());
            tradeHistory.setTradeAt(new Timestamp(System.currentTimeMillis()));
            tradeHistory.setProductId(productId);
            tradeHistory.setUserId(financeCityUser.getID());
            tradeHistory.setCheckCode(checkCode);

            userService.getUserDao(financeCityUser).save(tradeHistory);

            investedProducts.setState((byte)0);
            userService.getUserDao(financeCityUser).update(investedProducts);

            return true;
        }

        return false;
    }

    @Override
    public boolean enforceInvestmentPlan() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ackPayment(String checkCode, FinanceCityUser financeCityUser) throws NotLoginException, NoSuchProductException {
        List<UnpaidItem> list = userService.getCommonDao().find("FROM UnpaidItem u WHERE u.checkCode='" + checkCode + "' AND u.userId=" +
        financeCityUser.getID());

        if (list != null && list.size() > 0) {
            double trading_volume = 0;

            for (UnpaidItem unpaidItem : list) {
                trading_volume += unpaidItem.getTradingVolume().doubleValue();
            }

            InvestmentPortfolio investmentPortfolio = new InvestmentPortfolio();
            investmentPortfolio.setDate(new Timestamp(System.currentTimeMillis()));
            investmentPortfolio.setTradingVolume(new BigDecimal(trading_volume));
            investmentPortfolio.setCheckCode(checkCode);
            investmentPortfolio.setTradingVolume(new BigDecimal(trading_volume));

            userService.getUserDao(financeCityUser).save(investmentPortfolio);

            int portfolioId = (Integer)userService.getCommonDao().find("SELECT p.id FROM InvestmentPortfolio p WHERE p.checkCode='" +
            checkCode +"'").get(0);

            for (UnpaidItem unpaidItem : list) {
                try {
                    Product product = searchService.getProductByID(unpaidItem.getProductId());

                    TradeHistory tradeHistory = new TradeHistory();
                    tradeHistory.setCheckCode(checkCode);
                    tradeHistory.setTradeAt(new Timestamp(System.currentTimeMillis()));
                    tradeHistory.setProductId(unpaidItem.getProductId());
                    tradeHistory.setTradingVolume(unpaidItem.getTradingVolume());
                    tradeHistory.setUserId(financeCityUser.getID());
                    tradeHistory.setTradeType("buy");
                    setTradHistory(tradeHistory, product);

                    userService.getUserDao(financeCityUser).save(tradeHistory);
                    int tradeId = (Integer)userService.getUserDao(financeCityUser).
                            find("SELECT t.id FROM TradeHistory t WHERE t.checkCode='" + checkCode + "' AND t.productId=" + product.getID()).get(0);

                    InvestedProducts investedProducts = new InvestedProducts();
                    investedProducts.setBuyingDate(new Date(System.currentTimeMillis()));
                    investedProducts.setEndDate(getEndDate(tradeHistory.getTradeAt(), product));
                    investedProducts.setPortfolioId(portfolioId);
                    investedProducts.setProductId(unpaidItem.getProductId());
                    investedProducts.setTotalAmount(unpaidItem.getTradingVolume());
                    investedProducts.setUserId(financeCityUser.getID());
                    investedProducts.setTradeId(tradeId);
                    investedProducts.setState((byte)1);

                    userService.getUserDao(financeCityUser).save(investedProducts);
                    userService.getUserDao(financeCityUser).delete(unpaidItem);
                }
                catch (NoSuchProductException n) {
                    n.printStackTrace();
                    throw new NoSuchProductException(unpaidItem.getProductId());
                }
            }

            InvestStatus investStatus = new InvestStatus();
            investStatus.setUserId(financeCityUser.getID());
            investStatus.setPortfolioId(portfolioId);

            userService.getUserDao(financeCityUser).save(investStatus);

            assetManagementService.updateAssetValue(financeCityUser);
        }
    }

    private Date getEndDate(Timestamp buyingDate, Product product) {
        double endtime = 0;

        if (product.getCategory().belongTo(ProductCategoryManager.categoryInsurance)) {
            ProductInsurance productInsurance = (ProductInsurance)product.getProduct();
            endtime = TimeTransformation.getTimeAfter(buyingDate, productInsurance.getWarrantyPeriod().doubleValue(),
                    TimeTransformation.year, TimeTransformation.microSecond);
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            ProductFund productFund = (ProductFund)product.getProduct();
            endtime = TimeTransformation.getTimeAfter(buyingDate, productFund.getLength().doubleValue(),
                    TimeTransformation.day, TimeTransformation.microSecond);
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBank)) {
            ProductBank productBank = (ProductBank) product.getProduct();
            endtime = TimeTransformation.getTimeAfter(buyingDate, productBank.getLength().doubleValue(),
                    TimeTransformation.day, TimeTransformation.microSecond);
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBond)) {
            ProductBond productBond = (ProductBond) product.getProduct();
            endtime = TimeTransformation.getTimeAfter(buyingDate, productBond.getLength().doubleValue(),
                    TimeTransformation.day, TimeTransformation.microSecond);
        }


        return new Date((long)endtime);
    }


    private String generateMD5(FinanceCityUser financeCityUser, List<SimpleTradeInfo> tradeInfoList) {
        StringBuffer stringBuffer = new StringBuffer();

        if (tradeInfoList == null || tradeInfoList.size() == 0) {
            return MD5Utils.generateMD5(financeCityUser.getID(), new Timestamp(System.currentTimeMillis()).toString(), "");
        }
        else {
            for (SimpleTradeInfo simpleTradeInfo : tradeInfoList) {
                stringBuffer.append(simpleTradeInfo.getProductId());
                stringBuffer.append(simpleTradeInfo.getAmount());
            }

            return MD5Utils.generateMD5(financeCityUser.getID(), new Timestamp(System.currentTimeMillis()).toString(), stringBuffer.toString());
        }
    }

    private void setTradHistory(TradeHistory tradHistory, Product product) {
        if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            tradHistory.setNav(((ProductFund)product.getProduct()).getNav());
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBank)) {
            tradHistory.setNav(((ProductBank)product.getProduct()).getNav());
        }
    }
}
