package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/16.
 */
@Entity
@Table(name = "trade_history", schema = "citi", catalog = "")
public class TradeHistory {
    private int id;
    private Integer userId;
    private Integer productId;
    private String checkCode;
    private Timestamp tradeAt;
    private String tradeType;
    private BigDecimal tradingVolume;
    private BigDecimal buyingPrice;
    private BigDecimal nav;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @Column(name = "check_code")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Basic
    @Column(name = "trade_at")
    public Timestamp getTradeAt() {
        return tradeAt;
    }

    public void setTradeAt(Timestamp tradeAt) {
        this.tradeAt = tradeAt;
    }

    @Basic
    @Column(name = "trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Basic
    @Column(name = "trading_volume")
    public BigDecimal getTradingVolume() {
        return tradingVolume;
    }

    public void setTradingVolume(BigDecimal tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    @Basic
    @Column(name = "buying_price")
    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    @Basic
    @Column(name = "NAV")
    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeHistory that = (TradeHistory) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (checkCode != null ? !checkCode.equals(that.checkCode) : that.checkCode != null) return false;
        if (tradeAt != null ? !tradeAt.equals(that.tradeAt) : that.tradeAt != null) return false;
        if (tradeType != null ? !tradeType.equals(that.tradeType) : that.tradeType != null) return false;
        if (tradingVolume != null ? !tradingVolume.equals(that.tradingVolume) : that.tradingVolume != null)
            return false;
        if (buyingPrice != null ? !buyingPrice.equals(that.buyingPrice) : that.buyingPrice != null) return false;
        if (nav != null ? !nav.equals(that.nav) : that.nav != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (checkCode != null ? checkCode.hashCode() : 0);
        result = 31 * result + (tradeAt != null ? tradeAt.hashCode() : 0);
        result = 31 * result + (tradeType != null ? tradeType.hashCode() : 0);
        result = 31 * result + (tradingVolume != null ? tradingVolume.hashCode() : 0);
        result = 31 * result + (buyingPrice != null ? buyingPrice.hashCode() : 0);
        result = 31 * result + (nav != null ? nav.hashCode() : 0);
        return result;
    }
}
