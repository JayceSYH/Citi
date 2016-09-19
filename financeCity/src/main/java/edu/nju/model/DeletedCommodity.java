package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "deleted_commodity", schema = "citi", catalog = "")
public class DeletedCommodity {
    private int id;
    private Timestamp createdAt;
    private BigDecimal currentPrice;
    private BigDecimal expectedPrice;
    private Timestamp intervalBuyBuy;
    private Timestamp intervalBuySell;
    private Timestamp intervalFirstbuySell;
    private Integer productId;
    private BigDecimal setPrice;
    private Integer startingPrice;
    private Byte state;
    private Integer tradingUnit;
    private Timestamp updateAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "current_price")
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Basic
    @Column(name = "expected_price")
    public BigDecimal getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(BigDecimal expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    @Basic
    @Column(name = "interval_buy_buy")
    public Timestamp getIntervalBuyBuy() {
        return intervalBuyBuy;
    }

    public void setIntervalBuyBuy(Timestamp intervalBuyBuy) {
        this.intervalBuyBuy = intervalBuyBuy;
    }

    @Basic
    @Column(name = "interval_buy_sell")
    public Timestamp getIntervalBuySell() {
        return intervalBuySell;
    }

    public void setIntervalBuySell(Timestamp intervalBuySell) {
        this.intervalBuySell = intervalBuySell;
    }

    @Basic
    @Column(name = "interval_firstbuy_sell")
    public Timestamp getIntervalFirstbuySell() {
        return intervalFirstbuySell;
    }

    public void setIntervalFirstbuySell(Timestamp intervalFirstbuySell) {
        this.intervalFirstbuySell = intervalFirstbuySell;
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "set_price")
    public BigDecimal getSetPrice() {
        return setPrice;
    }

    public void setSetPrice(BigDecimal setPrice) {
        this.setPrice = setPrice;
    }

    @Basic
    @Column(name = "starting_price")
    public Integer getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Integer startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "trading_unit")
    public Integer getTradingUnit() {
        return tradingUnit;
    }

    public void setTradingUnit(Integer tradingUnit) {
        this.tradingUnit = tradingUnit;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeletedCommodity that = (DeletedCommodity) o;

        if (id != that.id) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (currentPrice != null ? !currentPrice.equals(that.currentPrice) : that.currentPrice != null) return false;
        if (expectedPrice != null ? !expectedPrice.equals(that.expectedPrice) : that.expectedPrice != null)
            return false;
        if (intervalBuyBuy != null ? !intervalBuyBuy.equals(that.intervalBuyBuy) : that.intervalBuyBuy != null)
            return false;
        if (intervalBuySell != null ? !intervalBuySell.equals(that.intervalBuySell) : that.intervalBuySell != null)
            return false;
        if (intervalFirstbuySell != null ? !intervalFirstbuySell.equals(that.intervalFirstbuySell) : that.intervalFirstbuySell != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (setPrice != null ? !setPrice.equals(that.setPrice) : that.setPrice != null) return false;
        if (startingPrice != null ? !startingPrice.equals(that.startingPrice) : that.startingPrice != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (tradingUnit != null ? !tradingUnit.equals(that.tradingUnit) : that.tradingUnit != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        result = 31 * result + (expectedPrice != null ? expectedPrice.hashCode() : 0);
        result = 31 * result + (intervalBuyBuy != null ? intervalBuyBuy.hashCode() : 0);
        result = 31 * result + (intervalBuySell != null ? intervalBuySell.hashCode() : 0);
        result = 31 * result + (intervalFirstbuySell != null ? intervalFirstbuySell.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (setPrice != null ? setPrice.hashCode() : 0);
        result = 31 * result + (startingPrice != null ? startingPrice.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (tradingUnit != null ? tradingUnit.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
