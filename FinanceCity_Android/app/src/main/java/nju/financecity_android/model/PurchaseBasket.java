package nju.financecity_android.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by coral on 16-9-7.
 */
public class PurchaseBasket {

    public PurchaseBasket() {
        productList = new HashMap<>();
    }

    public int getSum() {
        int sum = 0;
        for (Product product: productList.values()) {
            sum += product.getAmount() * product.getPrice();
        }
        return sum;
    }

    public void clear() {
        productList.clear();
    }

    public void setProductAmount(String productId, int amount) {
        productList.get(productId).setAmount(amount);
    }

    public void removeProduct(String productId) {
        productList.remove(productId);
    }

    public void addProduct(String productId, String productName, int price) {
        addProduct(productId, productName, price, 1);
    }

    public void addProduct(String productId, String productName, int price, int amount) {
        Product product = new Product(productId, productName, price, amount);
        productList.put(productId, product);
    }

    public static class Product {
        String productId;
        String productName;
        int price;
        int amount;

        public Product(String productId, String productName, int price, int amount) {
            setProductId(productId);
            setAmount(amount);
            setPrice(price);
            setAmount(amount);
        }

        public String getProductId() {
            return productId;
        }

        public int getAmount() {
            return amount;
        }

        public int getPrice() {
            return price;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }

    private Map<String, Product> productList;
}
