package edu.nju.service.InvestAdvisorService.Strategy;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl.*;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.POJO.SimpleTradeInfo;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.TradeService.TradeItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/20.
 */
public class InvestStrategyImpl implements InvestStrategy {
    private AssetCategoryAllocator assetCategoryAllocator;

    private CategoryInvest[] categoryInvest;

    public InvestStrategyImpl() {
        categoryInvest = new CategoryInvest[] {
                new BankInvest(), new BondInvest(),
                new FundInvest(), new InsuranceInvest()
        };
    }

    @Override
    public List<List<SimpleTradeInfo>> createInvestmentPortfolio(UserTemperPrefer preference, SearchService searchService) {
        //assetCategoryAllocator = new AssetCategoryAllocatorImpl();
        //assetCategoryAllocator.createAllocation(preference, searchService);

        InvestResult investResult = new InvestResult();

        List<Category> categoryList = ProductCategoryManager.getCategoryList();
        InvestResult insuranceList = null;
        for (Category category : categoryList) {
            //AssetCategoryAllocation allocation = assetCategoryAllocator.getCategoryAllocation(category.getCategoryName());
            AssetCategoryAllocation allocation = new AssetCategoryAllocation();
            allocation.setFreeCapital(5000);
            allocation.setFlowCapital(5000);

            for (CategoryInvest invest : categoryInvest) {
                if (category.belongTo(invest.getCategoryName())) {
                    try {
                        if (!category.belongTo(ProductCategoryManager.categoryInsurance)) {
                            investResult.addInvestResult(invest.invest(preference, searchService, allocation));
                        } else {
                            insuranceList = invest.invest(preference, searchService, allocation);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        List<List<SimpleTradeInfo>> lists = new ArrayList<>();
        List<TradeItem> insurance_item = insuranceList.getTradeItemList();

        for (int i = 0; i < insurance_item.size(); ++i) {
            List<SimpleTradeInfo> simpleTradeInfoList = new ArrayList<>();

            for (TradeItem tradeItem : investResult.getTradeItemList()) {
                SimpleTradeInfo simpleTradeInfo = new SimpleTradeInfo();
                simpleTradeInfo.setProductId(tradeItem.getProduct().getID());
                simpleTradeInfo.setAmount(tradeItem.getTradingVolume());

                simpleTradeInfoList.add(simpleTradeInfo);
            }

            SimpleTradeInfo simpleTradeInfo = new SimpleTradeInfo();
            simpleTradeInfo.setAmount(insuranceList.getUnusedAmount());
            simpleTradeInfo.setProductId(insurance_item.get(i).getProduct().getID());

            lists.add(simpleTradeInfoList);
        }

        return lists;
    }
}
