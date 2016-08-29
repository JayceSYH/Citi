package edu.nju.service.CategoryAndProduct;

import edu.nju.model.ProductBank;
import edu.nju.model.ProductBond;
import edu.nju.model.ProductFund;
import edu.nju.model.ProductInsurance;

import java.lang.reflect.Field;

/**
 * Created by Sun YuHao on 2016/8/26.
 */
public class Product {
    private Integer ID;
    private Category category;
    private Object product;

    public Product(Integer ID, Category category, Object product) {
        this.ID = ID;
        this.category = category;
        this.product = product;
    }

    public Integer getID() {
        return ID;
    }

    public Category getCategory() {
        return category;
    }

    public Object getProduct() {
        return product;
    }

    public String getUnit() {
        return category.getUnit();
    }

    public String getName() {
        if (category.equals(ProductCategoryManager.categoryBank)) {
            return ((ProductBank)product).getName();
        }
        else if (category.equals(ProductCategoryManager.categoryBond)) {
            return ((ProductBond)product).getTitle();
        }
        else if (category.equals(ProductCategoryManager.categoryInsurance)) {
            return ((ProductInsurance)product).getName();
        }
        else if (category.equals((ProductCategoryManager.categoryFund))) {
            return ((ProductFund)product).getName();
        }

        return null;
    }
}
