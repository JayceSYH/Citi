package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "unpaid_item", schema = "citi", catalog = "")
public class UnpaidItem {
    private int id;
    private int productId;
    private BigDecimal tradingVolume;
    private String checkCode;
    private Timestamp expirationTime;
    private int userId;
    private Integer amount;
    private String unit;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    @Column(name = "check_code")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Basic
    @Column(name = "expiration_time")
    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnpaidItem that = (UnpaidItem) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (userId != that.userId) return false;
        if (tradingVolume != null ? !tradingVolume.equals(that.tradingVolume) : that.tradingVolume != null)
            return false;
        if (checkCode != null ? !checkCode.equals(that.checkCode) : that.checkCode != null) return false;
        if (expirationTime != null ? !expirationTime.equals(that.expirationTime) : that.expirationTime != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (tradingVolume != null ? tradingVolume.hashCode() : 0);
        result = 31 * result + (checkCode != null ? checkCode.hashCode() : 0);
        result = 31 * result + (expirationTime != null ? expirationTime.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
