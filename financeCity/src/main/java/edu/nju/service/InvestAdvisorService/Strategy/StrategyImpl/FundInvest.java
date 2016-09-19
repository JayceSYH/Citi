package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.POJO.AmountAndLeft;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.TradeService.TradeItem;
import edu.nju.service.Utils.UnitTransformation;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/21.
 */
public class FundInvest implements CategoryInvest {
    static final String categoryName = "Fund";
    private static final int historyNum = 30;

    @Override
    public InvestResult invest(UserTemperPrefer userInfo, SearchService searchService, AssetCategoryAllocation allocation) {
        double capital;

        InvestResult investResult = new InvestResult();

        capital = allocation.getFlowCapital() + allocation.getFreeCapital();

        List<Product> productList = searchService.getProductListByOrder(ProductCategoryManager.categoryFund, "p.fundScore");
        if (productList == null || productList.size() == 0) {
            investResult.addUnusedCapital(capital, categoryName);
            return investResult;
        }

        AmountAndLeft amountAndLeft = UnitTransformation.getAmountAndLeft(capital, productList.get(0));
        investResult.addTradItem(new TradeItem(amountAndLeft.getTradingVolume(), productList.get(0), null));
        investResult.addUnusedCapital(amountAndLeft.getLeft(), categoryName);

        return investResult;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}
