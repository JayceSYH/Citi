package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import edu.nju.model.ProductBank;
import edu.nju.model.UserTemperPrefer;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.POJO.AmountAndLeft;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.TradeService.TradeItem;
import edu.nju.service.Utils.TimeTransformation;
import edu.nju.service.Utils.UnitTransformation;

import java.sql.Date;
import java.util.List;


/**
 * Created by Sun YuHao on 2016/8/18.
 */
@SuppressWarnings("unchecked")
public class BankInvest implements CategoryInvest {
    public static final String categoryName = "Bank";
    public static final String unusedFreeAmount = "BankFree";
    public static final String unusedFlowAmount = "BankFlow";

    private int[] thresholdList = {
            50000, 100000, 200000,
            300000, 500000, 1000000
    };

    private int[] durationList = {
        31, 93, 185, 365
    };

    @Override
    public InvestResult invest(UserTemperPrefer userInfo, SearchService searchService, AssetCategoryAllocation allocation) {
        InvestResult investResult = new InvestResult();

        int threshold = findMinThreshold(searchService);
        InvestResult flowInvestResult;
        InvestResult freeInvestResult;

        //invest
        if (allocation.getFlowCapital() >= threshold) {
            flowInvestResult = investFlowAmount(searchService, allocation.getFlowCapital(), userInfo.getRedeemTime());
        }
        else {
            flowInvestResult = new InvestResult();
            investResult.addUnusedCapital(allocation.getFlowCapital(), unusedFlowAmount);
        }
        if (allocation.getFreeCapital() >= threshold) {
            freeInvestResult = investFreeAmount(searchService, allocation.getFreeCapital());
        }
        else {
            freeInvestResult = new InvestResult();
            freeInvestResult.addUnusedCapital(allocation.getFreeCapital(), unusedFreeAmount);
        }

        investResult.addInvestResult(flowInvestResult);
        investResult.addInvestResult(freeInvestResult);

        return investResult;
    }

    private Product findMaxYieldProduct(List<Product> products) {
        Product wanted = products.get(0);
        for (Product product : products) {
            if (((ProductBank)product.getProduct()).getExpectedRate().compareTo(((ProductBank)wanted.getProduct()).getExpectedRate()) > 0) {
                wanted = product;
            }
        }

        return  wanted;
    }

    private int findMinThreshold(SearchService searchService) {
        return  (int)searchService.searchMin(categoryName, "purchase_threshold");
    }

    private int findMaxThresholdIndex(double flowAmount) {
        return findFloorIndex(flowAmount, thresholdList);
    }

    private int findMaxDurationIndex(double timeLimit) {
        return findFloorIndex(timeLimit, durationList);
    }

    private int findFloorIndex(double target, int[] list) {
        int index = 0;
        for (int i = 0; i < list.length; ++i) {
            if (list[i] > target) {
                break;
            }

            index = i;
        }

        return index;
    }

    private InvestResult investFreeAmount(SearchService searchService, double capital) {
        double leftAmount;
        InvestResult investResult = new InvestResult();

        leftAmount = capital;

        //find max floor threshold
        int max_threshold = findMaxThresholdIndex(leftAmount);

        //find candidate products by threshold and time limit
        List<Product> candidateList = null;
        while (max_threshold >= 0) {
            candidateList = searchService.searchProductsByConditionWithOrder(
                    ProductCategoryManager.categoryBank,
                    "p.purchase_threshold BETWEEN " + thresholdList[max_threshold] + " AND " + leftAmount,
                    "p.expectedRate DESC");

            if (candidateList != null && candidateList.size() != 0) {
                break;
            }

            max_threshold--;
        }

        if (max_threshold < 0) {
            investResult.addUnusedCapital(capital, unusedFreeAmount);
            return investResult;
        }

        Product product = candidateList.get(0);

        AmountAndLeft amountAndLeft = UnitTransformation.getAmountAndLeft(leftAmount, product);

        TradeItem tradeItem = new TradeItem(amountAndLeft.getTradingVolume(), product, null);

        investResult.addTradItem(tradeItem);
        investResult.addUnusedCapital(amountAndLeft.getLeft(), unusedFreeAmount);
        return investResult;
    }

    private InvestResult investFlowAmount(SearchService searchService, double capital, Date backDate) {
        double flowAmount;
        double timeLimit;
        InvestResult investResult = new InvestResult();

        //get meta info
        flowAmount = capital;
        timeLimit = TimeTransformation.getTimeFromNow(backDate, TimeTransformation.year);

        //find max threshold and duration for such capital
        int max_threshold = findMaxThresholdIndex(flowAmount);
        int floorDuration = findMaxDurationIndex(timeLimit);

        //find candidate products by threshold and time limit
        List<Product> candidateList = null;

        while (floorDuration >= 0) {
            candidateList = searchService.searchProductsByConditionWithOrder(
                    ProductCategoryManager.categoryBank,
                    "p.threshold BETWEEN " + thresholdList[max_threshold] + " AND " + flowAmount +
                            " AND p.dateLimit BETWEEN " + durationList[floorDuration] + " AND " + timeLimit,
                    "p.expectedRate DESC");

            if (candidateList != null && candidateList.size() != 0) {
                break;
            }

            if (max_threshold > 0) {
                max_threshold--;
            }
            else {
                floorDuration--;
            }
        }

        if (floorDuration < 0) {
            investResult.addUnusedCapital(capital, unusedFlowAmount);
            return investResult;
        }

        Product product = findMaxYieldProduct(candidateList);

        AmountAndLeft amountAndLeft = UnitTransformation.getAmountAndLeft(flowAmount, product);

        TradeItem tradeItem = new TradeItem(amountAndLeft.getTradingVolume(), product, null);

        investResult.addTradItem(tradeItem);
        investResult.addUnusedCapital(amountAndLeft.getLeft(), unusedFlowAmount);

        return investResult;
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}
