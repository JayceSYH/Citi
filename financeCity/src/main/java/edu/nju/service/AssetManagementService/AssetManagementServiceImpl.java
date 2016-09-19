package edu.nju.service.AssetManagementService;

import edu.nju.model.*;
import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.ExceptionsAndError.DataNotFoundException;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.ExceptionsAndError.NoSuchProductException;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.POJO.AssetValue;
import edu.nju.service.POJO.Investment_portfolio;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.UserService.UserService;
import edu.nju.service.Utils.TimeTransformation;
import edu.nju.vo.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public class AssetManagementServiceImpl implements AssetManagementService {
    @Autowired
    private SearchService searchService;
    @Autowired
    private UserService userService;

    @SuppressWarnings("unchecked")
    @Override
    public CurrentInvestmentVO getInvestProductVOList(FinanceCityUser financeCityUser) {
        CurrentInvestmentVO currentInvestmentVO = new CurrentInvestmentVO();

        try {
            List<InvestStatus> list = userService.getUserDao(financeCityUser).
                    find("FROM InvestStatus investStatus WHERE investStatus.userId=" + financeCityUser.getID());
            if (list == null || list.size() == 0) {
                ErrorManager.setError(currentInvestmentVO, ErrorManager.errorNormal);
                return currentInvestmentVO;
            }
            else {
                List<Investment_portfolio> investment_portfolioList = new ArrayList<>();
                for (InvestStatus investStatus : list) {
                    InvestmentPortfolio investmentPortfolio = (InvestmentPortfolio)userService.getUserDao(financeCityUser).
                            find("FROM InvestmentPortfolio i WHERE i.id=" + investStatus.getPortfolioId()).get(0);

                    List<InvestedProducts> investedProductList = userService.getUserDao(financeCityUser).
                            find("FROM InvestedProducts t WHERE t.portfolioId=" + investmentPortfolio.getId() + " AND t.state=1");

                    if (investedProductList == null || investedProductList.size() == 0) {
                        continue;
                    }

                    double total_amount = 0;
                    List<ProductVO> productList = new ArrayList<>();
                    for (InvestedProducts investedProducts : investedProductList) {
                        try {
                            Product product = searchService.getProductByID(investedProducts.getProductId());
                            ProductVO productVO = new ProductVO();
                            productVO.setId(product.getID());
                            productVO.setType(product.getCategory().getChineseName());
                            productVO.setBuyingDate(investedProducts.getBuyingDate().toString());
                            productVO.setCurrentValue(getCurrentValue(product, investedProducts));
                            productVO.setBuyingValue(investedProducts.getTotalAmount().doubleValue());
                            productVO.setName(product.getName());
                            productVO.setEndDate(investedProducts.getEndDate().toString());
                            productVO.setCanRedeemDate(ProductCategoryManager.getProductRedeemDate(product));
                            total_amount += investedProducts.getTotalAmount().doubleValue();

                            productList.add(productVO);
                        }
                        catch (NoSuchProductException n) {
                            n.printStackTrace();
                        }
                    }
                    Investment_portfolio investment_portfolio = new Investment_portfolio();
                    investment_portfolio.setProductVOs(productList);
                    investment_portfolio.setCheckCode(investmentPortfolio.getCheckCode());
                    investment_portfolio.setTotal_amount(total_amount);
                    investment_portfolioList.add(investment_portfolio);
                }

                currentInvestmentVO.setInvestmentPortfolioList(investment_portfolioList);
                ErrorManager.setError(currentInvestmentVO, ErrorManager.errorNormal);
            }
        }
        catch (NotLoginException e) {
            e.printStackTrace();
            ErrorManager.setError(currentInvestmentVO, ErrorManager.errorNotLogin);
        }

        return currentInvestmentVO;
    }

    private double getCurrentValue(Product product, InvestedProducts investedProducts) {
        /*
        double current_value;
        if (product.getCategory().belongTo(ProductCategoryManager.categoryBond) ||
                product.getCategory().belongTo(ProductCategoryManager.categoryBank) ||
                product.getCategory().belongTo(ProductCategoryManager.categoryInsurance)) {
            double ret_rate = -product.getRTR() * TimeTransformation.getTimeFromNow(investedProducts.getBuyingDate(), TimeTransformation.day) / 365;
            current_value = (ret_rate + 1) * investedProducts.getTotalAmount().doubleValue();
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            try {
                double buy_nav = getNavOnDate(product.getID(), investedProducts.getBuyingDate());
                double now_nav = getNavOnDate(product.getID(), new Date(System.currentTimeMillis()));
                current_value = (now_nav / buy_nav) * investedProducts.getTotalAmount().doubleValue();
            }
            catch (DataNotFoundException d) {
                d.printStackTrace();
                return investedProducts.getTotalAmount().doubleValue();
            }
        }
        else {
            //exception
            return investedProducts.getTotalAmount().doubleValue();
        }

        return current_value;
        */

        return investedProducts.getTotalAmount().doubleValue();
    }

    private double getNavOnDate(int product_id, Date date) throws DataNotFoundException {
        try {
            BigDecimal bigDecimal = (BigDecimal)userService.getCommonDao().find("SELECT p.nav FROM FundDailyHistory p WHERE p.fundId=" + product_id).get(0);
            return bigDecimal.doubleValue();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new DataNotFoundException("NAV");
        }
    }

    private double getProductValueOnDate(double tradingVolume, Product product, InvestedProducts investedProducts, Date date) {
        double value;
        if (product.getCategory().belongTo(ProductCategoryManager.categoryBond) ||
                product.getCategory().belongTo(ProductCategoryManager.categoryBank) ||
                product.getCategory().belongTo(ProductCategoryManager.categoryInsurance)) {
            double ret_rate = TimeTransformation.getTimeFromDate(investedProducts.getBuyingDate(), date, TimeTransformation.year);
            value = (ret_rate + 1) * tradingVolume;
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            try {
                value = (getNavOnDate(product.getID(), date) / getNavOnDate(product.getID(), date)) * tradingVolume;
            }
            catch (DataNotFoundException d) {
                d.printStackTrace();
                return tradingVolume;
            }
        }
        else {
            //exception
            return tradingVolume;
        }

        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public TradeHistoryListVO getTradeHistory(FinanceCityUser financeCityUser){
        TradeHistoryListVO tradeHistoryListVO = new TradeHistoryListVO();

        try {
            List<TradeHistoryVO> tradeHistoryVOList = new ArrayList<>();
            List<TradeHistory> tradeHistoryList = userService.getUserDao(financeCityUser).
                    find("FROM TradeHistory t WHERE t.userId=" + financeCityUser.getID());

            if (tradeHistoryList == null || tradeHistoryList.size() == 0) {
                throw new DataNotFoundException("Trade History");
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (TradeHistory tradeHistory : tradeHistoryList) {
                try {
                    TradeHistoryVO tradeHistoryVO = new TradeHistoryVO();
                    Product product = searchService.getProductByID(tradeHistory.getProductId());

                    tradeHistoryVO.setDate(dateFormat.format(tradeHistory.getTradeAt()));
                    tradeHistoryVO.setProductId(product.getID());
                    tradeHistoryVO.setProductName(product.getName());
                    tradeHistoryVO.setTradingType(tradeHistory.getTradeType());
                    tradeHistoryVO.setUnit(product.getCategory().getUnit());
                    tradeHistoryVO.setTradingVolume(tradeHistory.getTradingVolume().doubleValue());

                    tradeHistoryVOList.add(tradeHistoryVO);
                } catch (NoSuchProductException n) {
                    n.printStackTrace();
                }
            }

            tradeHistoryListVO.setTradeHistoryVOList(tradeHistoryVOList);
            ErrorManager.setError(tradeHistoryListVO, ErrorManager.errorNormal);
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            ErrorManager.setError(tradeHistoryListVO, ErrorManager.errorNotLogin);
        }
        catch (DataNotFoundException d){
            d.printStackTrace();
            ErrorManager.setError(tradeHistoryListVO, ErrorManager.errorDataNotFound);
        }

        return tradeHistoryListVO;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateAssetValue(FinanceCityUser financeCityUser) throws NotLoginException {
        try {
            double current_value = 0;
            List<InvestStatus> list = userService.getUserDao(financeCityUser).
                    find("FROM InvestStatus investStatus WHERE investStatus.userId=" + financeCityUser.getID());
            if (list == null || list.size() == 0) {
                return;
            }
            else {
                for (InvestStatus investStatus : list) {
                    InvestmentPortfolio investmentPortfolio = (InvestmentPortfolio)userService.getUserDao(financeCityUser).
                            find("FROM InvestmentPortfolio i WHERE i.id=" + investStatus.getPortfolioId()).get(0);

                    List<InvestedProducts> investedProductses = userService.getUserDao(financeCityUser).
                            find("FROM InvestedProducts t WHERE t.portfolioId=" + investmentPortfolio.getId());

                    for (InvestedProducts investedProducts : investedProductses) {
                        Product product = searchService.getProductByID(investedProducts.getProductId());
                        current_value += getCurrentValue(product, investedProducts);
                    }
                }
            }

            UserAsset userAsset = new UserAsset();
            userAsset.setUserId(financeCityUser.getID());
            userAsset.setDate(new Date(System.currentTimeMillis()));
            userAsset.setCurrentPrice(new BigDecimal(current_value));

            userService.getUserDao(financeCityUser).saveOrUpdate(userAsset);
        }
        catch (NoSuchProductException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AssetValue> getAssetValueHistory(FinanceCityUser financeCityUser, int days) throws NotLoginException {

        List<UserAsset> list = userService.getUserDao(financeCityUser).find("FROM UserAsset u WHERE u.userId=" + financeCityUser.getID()
                + " ORDER BY u.date DESC");

        if (list == null || list.size() == 0) {
            return null;
        }

        List<AssetValue> assetValues = new ArrayList<>();
        for (int i = 0; i < list.size() && i < days; ++i) {
            AssetValue assetValue = new AssetValue();
            assetValue.setDate(list.get(i).getDate().toString());
            assetValue.setValue(list.get(i).getCurrentPrice().doubleValue());

            assetValues.add(assetValue);
        }

        return assetValues;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductVO> getInvestedProduct(String checkCode, FinanceCityUser financeCityUser) throws NotLoginException {
        List<InvestedProducts> investedProductsList = new ArrayList<>();
        List<InvestStatus> list = userService.getUserDao(financeCityUser).
                find("FROM InvestStatus investStatus WHERE investStatus.userId=" + financeCityUser.getID());
        if (list == null || list.size() == 0) {
            return null;
        } else {
            for (InvestStatus investStatus : list) {
                InvestmentPortfolio investmentPortfolio = (InvestmentPortfolio) userService.getUserDao(financeCityUser).
                        find("FROM InvestmentPortfolio i WHERE i.id=" + investStatus.getPortfolioId()).get(0);

                List<InvestedProducts> investedProductList = userService.getUserDao(financeCityUser).
                        find("FROM InvestedProducts t WHERE t.portfolioId=" + investmentPortfolio.getId() + " AND t.state=1");

                if (investedProductList == null || investedProductList.size() == 0) {
                    continue;
                }

                investedProductsList.addAll(investedProductList);
            }
        }

        List<ProductVO> productList = new ArrayList<>();
        for (InvestedProducts investedProducts : investedProductsList) {
            try {
                Product product = searchService.getProductByID(investedProducts.getProductId());
                ProductVO productVO = new ProductVO();
                productVO.setId(product.getID());
                productVO.setType(product.getCategory().getChineseName());
                productVO.setBuyingDate(investedProducts.getBuyingDate().toString());
                productVO.setCurrentValue(getCurrentValue(product, investedProducts));
                productVO.setBuyingValue(investedProducts.getTotalAmount().doubleValue());
                productVO.setName(product.getName());
                productVO.setEndDate(investedProducts.getEndDate().toString());
                productVO.setCanRedeemDate(ProductCategoryManager.getProductRedeemDate(product));

                productList.add(productVO);
            }
            catch (NoSuchProductException n) {
                n.printStackTrace();
            }
        }

        return productList;
    }
}
