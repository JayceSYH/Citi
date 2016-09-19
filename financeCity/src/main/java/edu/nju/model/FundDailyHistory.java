package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "fund_daily_history", schema = "citi", catalog = "")
public class FundDailyHistory {
    private int id;
    private Date date;
    private BigDecimal nav;
    private BigDecimal accumNav;
    private BigDecimal adjustNav;
    private Integer fundId;
    private BigDecimal dailyReturn;
    private BigDecimal stockRatio;
    private BigDecimal fundSize;
    private BigDecimal debtRatio;
    private BigDecimal institutionRatio;

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
    @Column(name = "NAV")
    public BigDecimal getNav() {
        return nav;
    }

    public void setNav(BigDecimal nav) {
        this.nav = nav;
    }

    @Basic
    @Column(name = "accum_NAV")
    public BigDecimal getAccumNav() {
        return accumNav;
    }

    public void setAccumNav(BigDecimal accumNav) {
        this.accumNav = accumNav;
    }

    @Basic
    @Column(name = "adjust_NAV")
    public BigDecimal getAdjustNav() {
        return adjustNav;
    }

    public void setAdjustNav(BigDecimal adjustNav) {
        this.adjustNav = adjustNav;
    }

    @Basic
    @Column(name = "fund_id")
    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    @Basic
    @Column(name = "daily_return")
    public BigDecimal getDailyReturn() {
        return dailyReturn;
    }

    public void setDailyReturn(BigDecimal dailyReturn) {
        this.dailyReturn = dailyReturn;
    }

    @Basic
    @Column(name = "stock_ratio")
    public BigDecimal getStockRatio() {
        return stockRatio;
    }

    public void setStockRatio(BigDecimal stockRatio) {
        this.stockRatio = stockRatio;
    }

    @Basic
    @Column(name = "fund_size")
    public BigDecimal getFundSize() {
        return fundSize;
    }

    public void setFundSize(BigDecimal fundSize) {
        this.fundSize = fundSize;
    }

    @Basic
    @Column(name = "debt_ratio")
    public BigDecimal getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(BigDecimal debtRatio) {
        this.debtRatio = debtRatio;
    }

    @Basic
    @Column(name = "institution_ratio")
    public BigDecimal getInstitutionRatio() {
        return institutionRatio;
    }

    public void setInstitutionRatio(BigDecimal institutionRatio) {
        this.institutionRatio = institutionRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundDailyHistory that = (FundDailyHistory) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (nav != null ? !nav.equals(that.nav) : that.nav != null) return false;
        if (accumNav != null ? !accumNav.equals(that.accumNav) : that.accumNav != null) return false;
        if (adjustNav != null ? !adjustNav.equals(that.adjustNav) : that.adjustNav != null) return false;
        if (fundId != null ? !fundId.equals(that.fundId) : that.fundId != null) return false;
        if (dailyReturn != null ? !dailyReturn.equals(that.dailyReturn) : that.dailyReturn != null) return false;
        if (stockRatio != null ? !stockRatio.equals(that.stockRatio) : that.stockRatio != null) return false;
        if (fundSize != null ? !fundSize.equals(that.fundSize) : that.fundSize != null) return false;
        if (debtRatio != null ? !debtRatio.equals(that.debtRatio) : that.debtRatio != null) return false;
        if (institutionRatio != null ? !institutionRatio.equals(that.institutionRatio) : that.institutionRatio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (nav != null ? nav.hashCode() : 0);
        result = 31 * result + (accumNav != null ? accumNav.hashCode() : 0);
        result = 31 * result + (adjustNav != null ? adjustNav.hashCode() : 0);
        result = 31 * result + (fundId != null ? fundId.hashCode() : 0);
        result = 31 * result + (dailyReturn != null ? dailyReturn.hashCode() : 0);
        result = 31 * result + (stockRatio != null ? stockRatio.hashCode() : 0);
        result = 31 * result + (fundSize != null ? fundSize.hashCode() : 0);
        result = 31 * result + (debtRatio != null ? debtRatio.hashCode() : 0);
        result = 31 * result + (institutionRatio != null ? institutionRatio.hashCode() : 0);
        return result;
    }
}
