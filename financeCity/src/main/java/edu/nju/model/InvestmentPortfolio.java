package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "investment_portfolio", schema = "citi", catalog = "")
public class InvestmentPortfolio {
    private int id;
    private Timestamp date;
    private BigDecimal tradingVolume;
    private String checkCode;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
    @Column(name = "checkCode")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvestmentPortfolio that = (InvestmentPortfolio) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (tradingVolume != null ? !tradingVolume.equals(that.tradingVolume) : that.tradingVolume != null)
            return false;
        if (checkCode != null ? !checkCode.equals(that.checkCode) : that.checkCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tradingVolume != null ? tradingVolume.hashCode() : 0);
        result = 31 * result + (checkCode != null ? checkCode.hashCode() : 0);
        return result;
    }
}
