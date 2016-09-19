package edu.nju.service.TradeService;

import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.CategoryAndProduct.Product;
import org.springframework.util.DigestUtils;

/**
 * Created by Sun YuHao on 2016/8/17.
 */
public class TradeItem {
    private int amount;
    private double tradingVolume;
    private Product product;
    private String metaInfo;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(double tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public TradeItem(double tradingVolume, Product product, String metaInfo) {
        this.tradingVolume = tradingVolume;
        this.product = product;
        this.metaInfo = metaInfo;
    }
}
