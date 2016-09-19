package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
@Entity
@Table(name = "product_insurance", schema = "citi", catalog = "")
public class ProductInsurance {
    private int id;
    private String name;
    private String institutionManage;
    private BigDecimal indemnity;
    private String riskDesctiption;
    private BigDecimal yearRate;
    private BigDecimal guaranteedRate;
    private BigDecimal historyRate;
    private BigDecimal expectedRate;
    private Integer denomination;
    private Byte payType;
    private Integer warrantyPeriod;
    private BigDecimal dayRate;
    private Integer productPeriod;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "institution_manage")
    public String getInstitutionManage() {
        return institutionManage;
    }

    public void setInstitutionManage(String institutionManage) {
        this.institutionManage = institutionManage;
    }

    @Basic
    @Column(name = "indemnity")
    public BigDecimal getIndemnity() {
        return indemnity;
    }

    public void setIndemnity(BigDecimal indemnity) {
        this.indemnity = indemnity;
    }

    @Basic
    @Column(name = "risk_desctiption")
    public String getRiskDesctiption() {
        return riskDesctiption;
    }

    public void setRiskDesctiption(String riskDesctiption) {
        this.riskDesctiption = riskDesctiption;
    }

    @Basic
    @Column(name = "year_rate")
    public BigDecimal getYearRate() {
        return yearRate;
    }

    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    @Basic
    @Column(name = "guaranteed_rate")
    public BigDecimal getGuaranteedRate() {
        return guaranteedRate;
    }

    public void setGuaranteedRate(BigDecimal guaranteedRate) {
        this.guaranteedRate = guaranteedRate;
    }

    @Basic
    @Column(name = "history_rate")
    public BigDecimal getHistoryRate() {
        return historyRate;
    }

    public void setHistoryRate(BigDecimal historyRate) {
        this.historyRate = historyRate;
    }

    @Basic
    @Column(name = "expected_rate")
    public BigDecimal getExpectedRate() {
        return expectedRate;
    }

    public void setExpectedRate(BigDecimal expectedRate) {
        this.expectedRate = expectedRate;
    }

    @Basic
    @Column(name = "denomination")
    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    @Basic
    @Column(name = "pay_type")
    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "warranty_period")
    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Basic
    @Column(name = "day_rate")
    public BigDecimal getDayRate() {
        return dayRate;
    }

    public void setDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }

    @Basic
    @Column(name = "product_period")
    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInsurance that = (ProductInsurance) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (institutionManage != null ? !institutionManage.equals(that.institutionManage) : that.institutionManage != null)
            return false;
        if (indemnity != null ? !indemnity.equals(that.indemnity) : that.indemnity != null) return false;
        if (riskDesctiption != null ? !riskDesctiption.equals(that.riskDesctiption) : that.riskDesctiption != null)
            return false;
        if (yearRate != null ? !yearRate.equals(that.yearRate) : that.yearRate != null) return false;
        if (guaranteedRate != null ? !guaranteedRate.equals(that.guaranteedRate) : that.guaranteedRate != null)
            return false;
        if (historyRate != null ? !historyRate.equals(that.historyRate) : that.historyRate != null) return false;
        if (expectedRate != null ? !expectedRate.equals(that.expectedRate) : that.expectedRate != null) return false;
        if (denomination != null ? !denomination.equals(that.denomination) : that.denomination != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (warrantyPeriod != null ? !warrantyPeriod.equals(that.warrantyPeriod) : that.warrantyPeriod != null)
            return false;
        if (dayRate != null ? !dayRate.equals(that.dayRate) : that.dayRate != null) return false;
        if (productPeriod != null ? !productPeriod.equals(that.productPeriod) : that.productPeriod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institutionManage != null ? institutionManage.hashCode() : 0);
        result = 31 * result + (indemnity != null ? indemnity.hashCode() : 0);
        result = 31 * result + (riskDesctiption != null ? riskDesctiption.hashCode() : 0);
        result = 31 * result + (yearRate != null ? yearRate.hashCode() : 0);
        result = 31 * result + (guaranteedRate != null ? guaranteedRate.hashCode() : 0);
        result = 31 * result + (historyRate != null ? historyRate.hashCode() : 0);
        result = 31 * result + (expectedRate != null ? expectedRate.hashCode() : 0);
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (warrantyPeriod != null ? warrantyPeriod.hashCode() : 0);
        result = 31 * result + (dayRate != null ? dayRate.hashCode() : 0);
        result = 31 * result + (productPeriod != null ? productPeriod.hashCode() : 0);
        return result;
    }
}
