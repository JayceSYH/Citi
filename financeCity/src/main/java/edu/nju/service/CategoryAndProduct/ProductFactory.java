package edu.nju.service.CategoryAndProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/26.
 */
public class ProductFactory {
    static public Product createProduct(Object product) {
        return new Product(ProductCategoryManager.generateProductID(product),
                ProductCategoryManager.getProductCategory(product), product);
    }

    static public List<Product> createProduct(Object[] product) {
        List<Product> list = new ArrayList<>();

        for (Object object : product) {
            list.add(createProduct(object));
        }

        return list;
    }
}
