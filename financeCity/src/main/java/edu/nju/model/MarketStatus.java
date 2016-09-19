package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "market_status", schema = "citi", catalog = "")
public class MarketStatus {
    private long id;
    private BigDecimal depositInterestRate;
    private BigDecimal exchangeRate;
    private BigDecimal indexes;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "deposit_interest_rate")
    public BigDecimal getDepositInterestRate() {
        return depositInterestRate;
    }

    public void setDepositInterestRate(BigDecimal depositInterestRate) {
        this.depositInterestRate = depositInterestRate;
    }

    @Basic
    @Column(name = "exchange_rate")
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Basic
    @Column(name = "indexes")
    public BigDecimal getIndexes() {
        return indexes;
    }

    public void setIndexes(BigDecimal indexes) {
        this.indexes = indexes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketStatus that = (MarketStatus) o;

        if (id != that.id) return false;
        if (depositInterestRate != null ? !depositInterestRate.equals(that.depositInterestRate) : that.depositInterestRate != null)
            return false;
        if (exchangeRate != null ? !exchangeRate.equals(that.exchangeRate) : that.exchangeRate != null) return false;
        if (indexes != null ? !indexes.equals(that.indexes) : that.indexes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (depositInterestRate != null ? depositInterestRate.hashCode() : 0);
        result = 31 * result + (exchangeRate != null ? exchangeRate.hashCode() : 0);
        result = 31 * result + (indexes != null ? indexes.hashCode() : 0);
        return result;
    }
}
