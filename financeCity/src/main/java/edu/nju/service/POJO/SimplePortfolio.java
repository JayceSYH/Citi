package edu.nju.service.POJO;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
public class SimplePortfolio {
    private List<SimpleTradeInfo> portfolio;

    public List<SimpleTradeInfo> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<SimpleTradeInfo> portfolio) {
        this.portfolio = portfolio;
    }
}
