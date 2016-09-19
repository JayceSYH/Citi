package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import edu.nju.model.ProductInsurance;
import edu.nju.model.UserTemperPrefer;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.TradeService.TradeItem;
import edu.nju.service.Utils.TimeTransformation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sun YuHao on 2016/8/20.
 */
public class InsuranceInvest implements CategoryInvest {
    public static final String categoryName = "Insurance";

    @Override
    public InvestResult invest(UserTemperPrefer userInfo, SearchService searchService, AssetCategoryAllocation allocation)  {
        double timeLimit;
        double capital;
        InvestResult investResult = new InvestResult();

        capital = allocation.getFreeCapital() + allocation.getFlowCapital();
        timeLimit = TimeTransformation.getTimeFromNow(userInfo.getEndTime(), TimeTransformation.year);
        //TODO:ask if need insurance amount(number)
        int insurance_amount_;

        List<Product> productList = searchService.getProductListByOrder(categoryName, "p.yearRate DESC");
        if (productList == null) {
            InvestResult investResult1 = new InvestResult();
            investResult1.addUnusedCapital(capital, categoryName);
            return investResult1;
        }

        if (timeLimit == 5 || timeLimit == 10 || timeLimit < 5) {
            ExpectationSortor expectationSortor = new ExpectationSortor();
            expectationSortor.setProductListWithYearRateOrder(productList);

            productList = expectationSortor.sortByShortTermExp();
            if (timeLimit == 5) {
                productList = productList.stream().
                        filter(product -> (((ProductInsurance)product.getProduct()).getWarrantyPeriod() == 5)).
                        collect(Collectors.toCollection(ArrayList::new));
            }
            else if(timeLimit == 10) {
                productList = productList.stream().
                        filter(product -> (((ProductInsurance)product.getProduct()).getWarrantyPeriod() == 10)).
                        collect(Collectors.toCollection(ArrayList::new));
            }
        }
        else if (timeLimit == 15 || timeLimit == 20 || timeLimit > 20) {
            ExpectationSortor expectationSortor = new ExpectationSortor();
            expectationSortor.setProductListWithYearRateOrder(productList);

            productList = expectationSortor.sortByLongTermExp();
            if (timeLimit == 15) {
                productList = productList.stream().
                        filter(product -> (((ProductInsurance)product.getProduct()).getWarrantyPeriod() == 15)).
                        collect(Collectors.toCollection(ArrayList::new));
            }
            else if(timeLimit == 20) {
                productList = productList.stream().
                        filter(product -> (((ProductInsurance)product.getProduct()).getWarrantyPeriod() == 20)).
                        collect(Collectors.toCollection(ArrayList::new));
            }
        }

        investResult.addUnusedCapital(capital, categoryName);
        for (int i = 0; i < 5 && i < productList.size(); ++i) {
            investResult.addTradItem(new TradeItem(0, productList.get(i), null));
        }

        return investResult;
    }

    private class ExpectationSortor {
        List<Product> productList;
        List<Integer> unitCompensationOrder;
        List<Integer> yearRateOrder;
        List<Double> shortTermExp;
        List<Double> longTermExp;

        private void setProductListWithYearRateOrder(List<Product> productList) {
            this.productList = productList;
            yearRateOrder = new ArrayList<>();

            for (int i = 0; i < productList.size(); ++i) {
                yearRateOrder.add(i);
            }
        }

        private List<Product> sortByShortTermExp() {
            calcuUnitCompensationOrder();

            shortTermExp = new ArrayList<>(productList.size());
            for (int i = 0; i < productList.size(); ++i) {
                shortTermExp.set(i, yearRateOrder.get(i) * 0.7 +
                        unitCompensationOrder.get(i) * 0.3);
            }

            List<SimpleStruct> list = new ArrayList<>();
            for (int i = 0; i < productList.size(); ++i) {
                list.add(new SimpleStruct(productList.get(i), shortTermExp.get(i)));
            }

            list.sort(((o1, o2) -> o2.score.compareTo(o1.score)));

            return list.stream().map(simpleStruct -> simpleStruct.product).collect(Collectors.toCollection(ArrayList::new));
        }

        private List<Product> sortByLongTermExp() {
            calcuUnitCompensationOrder();

            longTermExp = new ArrayList<>(productList.size());
            for (int i = 0; i < productList.size(); ++i) {
                longTermExp.set(i, yearRateOrder.get(i) * 0.3 +
                        unitCompensationOrder.get(i) * 0.7);
            }

            List<SimpleStruct> list = new ArrayList<>();
            for (int i = 0; i < productList.size(); ++i) {
                list.add(new SimpleStruct(productList.get(i), longTermExp.get(i)));
            }

            list.sort((o1, o2) -> o2.score.compareTo(o1.score));

            return list.stream().map(simpleStruct -> simpleStruct.product).collect(Collectors.toCollection(ArrayList::new));
        }



        private void calcuUnitCompensationOrder() {
            List<Integer> noList = new ArrayList<>();
            for (int i = 0; i < productList.size(); ++i) {
                noList.add(i);
            }

            Comparator<Integer> comparator = new Comparator<Integer>() {
                List<Product> productList;

                @Override
                public int compare(Integer o1, Integer o2) {
                    ProductInsurance productInsurance1 = (ProductInsurance)productList.get(o1).getProduct();
                    ProductInsurance productInsurance2 = (ProductInsurance)productList.get(o2).getProduct();
                    Double unitCompensation1 = productInsurance1.getIndemnity().doubleValue();
                    Double unitCompensation2 = productInsurance2.getIndemnity().doubleValue();
                    return unitCompensation2.compareTo(unitCompensation1);
                }

                private Comparator<Integer> setProductList(List<Product> productList) {
                    this.productList = productList;
                    return this;
                }
            }.setProductList(productList);

            noList.sort(comparator);

            unitCompensationOrder = new ArrayList<>(noList.size());
            for (int i = 0; i < noList.size(); ++i) {
                unitCompensationOrder.set(noList.get(i), i);
            }
        }

        private class SimpleStruct {
            private Product product;
            private Double score;

            private SimpleStruct(Product product, double score) {
                this.product = product;
                this.score = score;
            }
        }
    }

    @Override
    public String getCategoryName() {
        return categoryName;
    }
}
