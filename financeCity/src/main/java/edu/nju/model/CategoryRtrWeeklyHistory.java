package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/16.
 */
@Entity
@Table(name = "categoryRTR_weekly_history", schema = "citi", catalog = "")
public class CategoryRtrWeeklyHistory {
    private int id;
    private Date date;
    private BigDecimal bank;
    private BigDecimal bond;
    private BigDecimal stockFund;
    private BigDecimal bondFund;
    private BigDecimal currencyFund;
    private BigDecimal blendFund;
    private BigDecimal etfFund;
    private BigDecimal lofFund;
    private BigDecimal qDllFund;
    private BigDecimal indexFund;

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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "bank")
    public BigDecimal getBank() {
        return bank;
    }

    public void setBank(BigDecimal bank) {
        this.bank = bank;
    }

    @Basic
    @Column(name = "bond")
    public BigDecimal getBond() {
        return bond;
    }

    public void setBond(BigDecimal bond) {
        this.bond = bond;
    }

    @Basic
    @Column(name = "stock_fund")
    public BigDecimal getStockFund() {
        return stockFund;
    }

    public void setStockFund(BigDecimal stockFund) {
        this.stockFund = stockFund;
    }

    @Basic
    @Column(name = "bond_fund")
    public BigDecimal getBondFund() {
        return bondFund;
    }

    public void setBondFund(BigDecimal bondFund) {
        this.bondFund = bondFund;
    }

    @Basic
    @Column(name = "currency_fund")
    public BigDecimal getCurrencyFund() {
        return currencyFund;
    }

    public void setCurrencyFund(BigDecimal currencyFund) {
        this.currencyFund = currencyFund;
    }

    @Basic
    @Column(name = "blend_fund")
    public BigDecimal getBlendFund() {
        return blendFund;
    }

    public void setBlendFund(BigDecimal blendFund) {
        this.blendFund = blendFund;
    }

    @Basic
    @Column(name = "etf_fund")
    public BigDecimal getEtfFund() {
        return etfFund;
    }

    public void setEtfFund(BigDecimal etfFund) {
        this.etfFund = etfFund;
    }

    @Basic
    @Column(name = "lof_fund")
    public BigDecimal getLofFund() {
        return lofFund;
    }

    public void setLofFund(BigDecimal lofFund) {
        this.lofFund = lofFund;
    }

    @Basic
    @Column(name = "QDll_fund")
    public BigDecimal getqDllFund() {
        return qDllFund;
    }

    public void setqDllFund(BigDecimal qDllFund) {
        this.qDllFund = qDllFund;
    }

    @Basic
    @Column(name = "index_fund")
    public BigDecimal getIndexFund() {
        return indexFund;
    }

    public void setIndexFund(BigDecimal indexFund) {
        this.indexFund = indexFund;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryRtrWeeklyHistory that = (CategoryRtrWeeklyHistory) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (bank != null ? !bank.equals(that.bank) : that.bank != null) return false;
        if (bond != null ? !bond.equals(that.bond) : that.bond != null) return false;
        if (stockFund != null ? !stockFund.equals(that.stockFund) : that.stockFund != null) return false;
        if (bondFund != null ? !bondFund.equals(that.bondFund) : that.bondFund != null) return false;
        if (currencyFund != null ? !currencyFund.equals(that.currencyFund) : that.currencyFund != null) return false;
        if (blendFund != null ? !blendFund.equals(that.blendFund) : that.blendFund != null) return false;
        if (etfFund != null ? !etfFund.equals(that.etfFund) : that.etfFund != null) return false;
        if (lofFund != null ? !lofFund.equals(that.lofFund) : that.lofFund != null) return false;
        if (qDllFund != null ? !qDllFund.equals(that.qDllFund) : that.qDllFund != null) return false;
        if (indexFund != null ? !indexFund.equals(that.indexFund) : that.indexFund != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (bond != null ? bond.hashCode() : 0);
        result = 31 * result + (stockFund != null ? stockFund.hashCode() : 0);
        result = 31 * result + (bondFund != null ? bondFund.hashCode() : 0);
        result = 31 * result + (currencyFund != null ? currencyFund.hashCode() : 0);
        result = 31 * result + (blendFund != null ? blendFund.hashCode() : 0);
        result = 31 * result + (etfFund != null ? etfFund.hashCode() : 0);
        result = 31 * result + (lofFund != null ? lofFund.hashCode() : 0);
        result = 31 * result + (qDllFund != null ? qDllFund.hashCode() : 0);
        result = 31 * result + (indexFund != null ? indexFund.hashCode() : 0);
        return result;
    }
}
