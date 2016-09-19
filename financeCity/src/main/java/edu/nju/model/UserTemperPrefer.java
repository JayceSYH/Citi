package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/15.
 */
@Entity
@Table(name = "user_temper_prefer", schema = "citi", catalog = "")
public class UserTemperPrefer {
    private int userId;
    private BigDecimal expectedCapital;
    private BigDecimal expectedProfitMin;
    private Byte ifPrepedBigExpense;
    private BigDecimal mayRedeemAmount;
    private Date redeemTime;
    private BigDecimal insuranceAmount;
    private Byte expenseType;
    private BigDecimal riskToleranceMin;
    private BigDecimal expectedRetrunRate;
    private Byte ifConfigBigExpense;
    private Date beginTime;
    private Date endTime;
    private String chosenProducts;
    private Integer id;
    private BigDecimal riskToleranceMax;
    private BigDecimal expectedProfitMax;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "expected_capital")
    public BigDecimal getExpectedCapital() {
        return expectedCapital;
    }

    public void setExpectedCapital(BigDecimal expectedCapital) {
        this.expectedCapital = expectedCapital;
    }

    @Basic
    @Column(name = "expected_profit_min")
    public BigDecimal getExpectedProfitMin() {
        return expectedProfitMin;
    }

    public void setExpectedProfitMin(BigDecimal expectedProfitMin) {
        this.expectedProfitMin = expectedProfitMin;
    }

    @Basic
    @Column(name = "if_preped_big_expense")
    public Byte getIfPrepedBigExpense() {
        return ifPrepedBigExpense;
    }

    public void setIfPrepedBigExpense(Byte ifPrepedBigExpense) {
        this.ifPrepedBigExpense = ifPrepedBigExpense;
    }

    @Basic
    @Column(name = "may_redeem_amount")
    public BigDecimal getMayRedeemAmount() {
        return mayRedeemAmount;
    }

    public void setMayRedeemAmount(BigDecimal mayRedeemAmount) {
        this.mayRedeemAmount = mayRedeemAmount;
    }

    @Basic
    @Column(name = "redeem_time")
    public Date getRedeemTime() {
        return redeemTime;
    }

    public void setRedeemTime(Date redeemTime) {
        this.redeemTime = redeemTime;
    }

    @Basic
    @Column(name = "insurance_amount")
    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    @Basic
    @Column(name = "expense_type")
    public Byte getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(Byte expenseType) {
        this.expenseType = expenseType;
    }

    @Basic
    @Column(name = "risk_tolerance_min")
    public BigDecimal getRiskToleranceMin() {
        return riskToleranceMin;
    }

    public void setRiskToleranceMin(BigDecimal riskToleranceMin) {
        this.riskToleranceMin = riskToleranceMin;
    }

    @Basic
    @Column(name = "expected_retrun_rate")
    public BigDecimal getExpectedRetrunRate() {
        return expectedRetrunRate;
    }

    public void setExpectedRetrunRate(BigDecimal expectedRetrunRate) {
        this.expectedRetrunRate = expectedRetrunRate;
    }

    @Basic
    @Column(name = "if_config_big_expense")
    public Byte getIfConfigBigExpense() {
        return ifConfigBigExpense;
    }

    public void setIfConfigBigExpense(Byte ifConfigBigExpense) {
        this.ifConfigBigExpense = ifConfigBigExpense;
    }

    @Basic
    @Column(name = "begin_time")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "chosen_products")
    public String getChosenProducts() {
        return chosenProducts;
    }

    public void setChosenProducts(String chosenProducts) {
        this.chosenProducts = chosenProducts;
    }

    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "risk_tolerance_max")
    public BigDecimal getRiskToleranceMax() {
        return riskToleranceMax;
    }

    public void setRiskToleranceMax(BigDecimal riskToleranceMax) {
        this.riskToleranceMax = riskToleranceMax;
    }

    @Basic
    @Column(name = "expected_profit_max")
    public BigDecimal getExpectedProfitMax() {
        return expectedProfitMax;
    }

    public void setExpectedProfitMax(BigDecimal expectedProfitMax) {
        this.expectedProfitMax = expectedProfitMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTemperPrefer that = (UserTemperPrefer) o;

        if (userId != that.userId) return false;
        if (expectedCapital != null ? !expectedCapital.equals(that.expectedCapital) : that.expectedCapital != null)
            return false;
        if (expectedProfitMin != null ? !expectedProfitMin.equals(that.expectedProfitMin) : that.expectedProfitMin != null)
            return false;
        if (ifPrepedBigExpense != null ? !ifPrepedBigExpense.equals(that.ifPrepedBigExpense) : that.ifPrepedBigExpense != null)
            return false;
        if (mayRedeemAmount != null ? !mayRedeemAmount.equals(that.mayRedeemAmount) : that.mayRedeemAmount != null)
            return false;
        if (redeemTime != null ? !redeemTime.equals(that.redeemTime) : that.redeemTime != null) return false;
        if (insuranceAmount != null ? !insuranceAmount.equals(that.insuranceAmount) : that.insuranceAmount != null)
            return false;
        if (expenseType != null ? !expenseType.equals(that.expenseType) : that.expenseType != null) return false;
        if (riskToleranceMin != null ? !riskToleranceMin.equals(that.riskToleranceMin) : that.riskToleranceMin != null)
            return false;
        if (expectedRetrunRate != null ? !expectedRetrunRate.equals(that.expectedRetrunRate) : that.expectedRetrunRate != null)
            return false;
        if (ifConfigBigExpense != null ? !ifConfigBigExpense.equals(that.ifConfigBigExpense) : that.ifConfigBigExpense != null)
            return false;
        if (beginTime != null ? !beginTime.equals(that.beginTime) : that.beginTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (chosenProducts != null ? !chosenProducts.equals(that.chosenProducts) : that.chosenProducts != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (riskToleranceMax != null ? !riskToleranceMax.equals(that.riskToleranceMax) : that.riskToleranceMax != null)
            return false;
        if (expectedProfitMax != null ? !expectedProfitMax.equals(that.expectedProfitMax) : that.expectedProfitMax != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (expectedCapital != null ? expectedCapital.hashCode() : 0);
        result = 31 * result + (expectedProfitMin != null ? expectedProfitMin.hashCode() : 0);
        result = 31 * result + (ifPrepedBigExpense != null ? ifPrepedBigExpense.hashCode() : 0);
        result = 31 * result + (mayRedeemAmount != null ? mayRedeemAmount.hashCode() : 0);
        result = 31 * result + (redeemTime != null ? redeemTime.hashCode() : 0);
        result = 31 * result + (insuranceAmount != null ? insuranceAmount.hashCode() : 0);
        result = 31 * result + (expenseType != null ? expenseType.hashCode() : 0);
        result = 31 * result + (riskToleranceMin != null ? riskToleranceMin.hashCode() : 0);
        result = 31 * result + (expectedRetrunRate != null ? expectedRetrunRate.hashCode() : 0);
        result = 31 * result + (ifConfigBigExpense != null ? ifConfigBigExpense.hashCode() : 0);
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (chosenProducts != null ? chosenProducts.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (riskToleranceMax != null ? riskToleranceMax.hashCode() : 0);
        result = 31 * result + (expectedProfitMax != null ? expectedProfitMax.hashCode() : 0);
        return result;
    }
}
