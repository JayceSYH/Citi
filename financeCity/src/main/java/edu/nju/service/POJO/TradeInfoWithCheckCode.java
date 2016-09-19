package edu.nju.service.POJO;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
public class TradeInfoWithCheckCode {
    private List<SimpleTradeInfo> tradeInfos;
    private String checkCode;

    public List<SimpleTradeInfo> getTradeInfos() {
        return tradeInfos;
    }

    public void setTradeInfos(List<SimpleTradeInfo> tradeInfos) {
        this.tradeInfos = tradeInfos;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
