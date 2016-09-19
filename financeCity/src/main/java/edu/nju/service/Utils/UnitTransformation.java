package edu.nju.service.Utils;

import edu.nju.model.ProductBank;
import edu.nju.model.ProductBond;
import edu.nju.model.ProductFund;
import edu.nju.model.ProductInsurance;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.POJO.AmountAndLeft;
import edu.nju.service.CategoryAndProduct.Category;

/**
 * Created by Sun YuHao on 2016/8/19.
 */
public class UnitTransformation {
    static public AmountAndLeft getAmountAndLeft(double capital, Product product) {
        double amount = 0;
        double left = capital;
        double tradingVolume;
        AmountAndLeft amountAndLeft = new AmountAndLeft();

        if (product.getCategory().equals(ProductCategoryManager.categoryBank)) {
            ProductBank productBank = (ProductBank)product.getProduct();
            int increasingAmount = productBank.getIncreasingUnit();
            int threshold = productBank.getPurchaseThreshold();
            left = (capital - threshold) % increasingAmount;
            amount = capital - left;
        }//ProductBank
        if (product.getCategory().equals(ProductCategoryManager.categoryBond)) {
            ProductBond productBond = (ProductBond)product.getProduct();
            double price = productBond.getPar().doubleValue();
            amount = (int)(capital / price);
            left = capital - amount * price;
        }//productBond
        if(product.getCategory().equals(ProductCategoryManager.categoryFund)) {
            ProductFund productFund = (ProductFund)product.getProduct();
            int increasingAmount = productFund.getIncreasingUnit();
            int threshold = productFund.getPurchaseThreshold();
            left = (capital - threshold) % increasingAmount;
            amount = capital - left;
        }//productFund

        tradingVolume = capital - left;

        amountAndLeft.setAmount(amount);
        amountAndLeft.setLeft(left);
        amountAndLeft.setUnit(product.getUnit());
        amountAndLeft.setTradingVolume(tradingVolume);
        return amountAndLeft;
    }

    static public double calcuCost(Product product, int amount) {
        if (product.getCategory().belongTo(ProductCategoryManager.categoryInsurance)) {
            return ((ProductInsurance)product.getProduct()).getDenomination() * amount;
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBank)) {
            return amount;
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBond)) {
            return ((ProductBond)product.getProduct()).getIssuePrice().doubleValue() * amount;
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            return amount;
        }
        else {
            return -1;
        }
    }
}
