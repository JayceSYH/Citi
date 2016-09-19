package edu.nju.service.POJO;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/15.
 */
public class CommonPortfolio {
    private List<CommonProductInfo> products;
    private int yield_score;
    private int risk_score;
    private int flow_score;
    private int length_score;
    private double total_amount;
    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public int getYield_score() {
        return yield_score;
    }

    public void setYield_score(int yield_score) {
        this.yield_score = yield_score;
    }

    public int getRisk_score() {
        return risk_score;
    }

    public void setRisk_score(int risk_score) {
        this.risk_score = risk_score;
    }

    public int getFlow_score() {
        return flow_score;
    }

    public void setFlow_score(int flow_score) {
        this.flow_score = flow_score;
    }

    public int getLength_score() {
        return length_score;
    }

    public void setLength_score(int length_score) {
        this.length_score = length_score;
    }

    public List<CommonProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<CommonProductInfo> products) {
        this.products = products;
    }
}
