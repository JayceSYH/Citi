package edu.nju.vo;

/**
 * Created by Sun YuHao on 2016/8/28.
 */
public class TradeHistoryVO extends BaseVO {
    /**交易额*/
    private double TradingVolume;
    /**交易单位*/
    private String Unit;
    /**产品名*/
    private String ProductName;
    /**产品ID*/
    private int ProductId;
    /**交易类型(buy redeem)*/
    private String TradingType;
    /**日期*/
    private String Date;

    public double getTradingVolume() {
        return TradingVolume;
    }

    public void setTradingVolume(double tradingVolume) {
        TradingVolume = tradingVolume;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getTradingType() {
        return TradingType;
    }

    public void setTradingType(String tradingType) {
        TradingType = tradingType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
