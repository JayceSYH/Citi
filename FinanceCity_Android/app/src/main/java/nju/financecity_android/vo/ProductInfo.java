package nju.financecity_android.vo;

import java.util.Date;

/**
 * Created by coral on 16-8-27.
 */
public class ProductInfo {

    public ProductInfo() { }
    public ProductInfo(String id, String productName, String buy, String expiration, String available, float buyPrice, float currPrice) {
        this.productId = id;
        this.productName = productName;
        this.buy = buy;
        this.expiration = expiration;
        this.available = available;
        this.buyPrice = buyPrice;
        this.currPrice = currPrice;
    }

    public String type;
    public String productId;
    public String productName;
    public String buy, expiration, available;
    public float buyPrice, currPrice;
}
