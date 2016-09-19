package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "product_bank", schema = "citi", catalog = "")
public class ProductBank {
    private int id;
    private String name;
    private String institutionManage;
    private Integer purchaseThreshold;
    private Integer increasingUnit;
    private Integer length;
    private BigDecimal nav;
    private Date onPurchaseDate;
    private Date offPurchaseDate;
    private String salesRegion;
    private String productCode;
    private String registerCode;
    private String currency;
    private String investField;
    private String investRatio;
    private Byte riskLevel;
    private String session;
    private String custodian;
    private BigDecimal expectedRate;
    private BigDecimal ratePurchase;
    private BigDecimal rateRedem;
    private BigDecimal rateManage;
    private Date firstAccrDate;
    private Date onRedemptionDate;
    private Integer redemSpeed;
    private String payType;
    private String objectOriented;
    private Integer sizeUpperLimit;
    private Byte incomeType;
    private Byte ifNavType;
    private Byte ifClose;
    private Byte state;
    private Byte type;

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
    @Column(name = "purchase_threshold")
    public Integer getPurchaseThreshold() {
        return purchaseThreshold;
    }

    public void setPurchaseThreshold(Integer purchaseThreshold) {
        this.purchaseThreshold = purchaseThreshold;
    }

    @Basic
    @Column(name = "increasing_unit")
    public Integer getIncreasingUnit() {
        return increasingUnit;
    }

    public void setIncreasingUnit(Integer increasingUnit) {
        this.increasingUnit = increasingUnit;
    }

    @Basic
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
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
    @Column(name = "on_purchase_date")
    public Date getOnPurchaseDate() {
        return onPurchaseDate;
    }

    public void setOnPurchaseDate(Date onPurchaseDate) {
        this.onPurchaseDate = onPurchaseDate;
    }

    @Basic
    @Column(name = "off_purchase_date")
    public Date getOffPurchaseDate() {
        return offPurchaseDate;
    }

    public void setOffPurchaseDate(Date offPurchaseDate) {
        this.offPurchaseDate = offPurchaseDate;
    }

    @Basic
    @Column(name = "sales_region")
    public String getSalesRegion() {
        return salesRegion;
    }

    public void setSalesRegion(String salesRegion) {
        this.salesRegion = salesRegion;
    }

    @Basic
    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "register_code")
    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "invest_field")
    public String getInvestField() {
        return investField;
    }

    public void setInvestField(String investField) {
        this.investField = investField;
    }

    @Basic
    @Column(name = "invest_ratio")
    public String getInvestRatio() {
        return investRatio;
    }

    public void setInvestRatio(String investRatio) {
        this.investRatio = investRatio;
    }

    @Basic
    @Column(name = "risk_level")
    public Byte getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Byte riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Basic
    @Column(name = "session")
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Basic
    @Column(name = "custodian")
    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
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
    @Column(name = "rate_purchase")
    public BigDecimal getRatePurchase() {
        return ratePurchase;
    }

    public void setRatePurchase(BigDecimal ratePurchase) {
        this.ratePurchase = ratePurchase;
    }

    @Basic
    @Column(name = "rate_redem")
    public BigDecimal getRateRedem() {
        return rateRedem;
    }

    public void setRateRedem(BigDecimal rateRedem) {
        this.rateRedem = rateRedem;
    }

    @Basic
    @Column(name = "rate_manage")
    public BigDecimal getRateManage() {
        return rateManage;
    }

    public void setRateManage(BigDecimal rateManage) {
        this.rateManage = rateManage;
    }

    @Basic
    @Column(name = "first_accr_date")
    public Date getFirstAccrDate() {
        return firstAccrDate;
    }

    public void setFirstAccrDate(Date firstAccrDate) {
        this.firstAccrDate = firstAccrDate;
    }

    @Basic
    @Column(name = "on_redemption_date")
    public Date getOnRedemptionDate() {
        return onRedemptionDate;
    }

    public void setOnRedemptionDate(Date onRedemptionDate) {
        this.onRedemptionDate = onRedemptionDate;
    }

    @Basic
    @Column(name = "redem_speed")
    public Integer getRedemSpeed() {
        return redemSpeed;
    }

    public void setRedemSpeed(Integer redemSpeed) {
        this.redemSpeed = redemSpeed;
    }

    @Basic
    @Column(name = "pay_type")
    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Basic
    @Column(name = "object_oriented")
    public String getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(String objectOriented) {
        this.objectOriented = objectOriented;
    }

    @Basic
    @Column(name = "size_upper_limit")
    public Integer getSizeUpperLimit() {
        return sizeUpperLimit;
    }

    public void setSizeUpperLimit(Integer sizeUpperLimit) {
        this.sizeUpperLimit = sizeUpperLimit;
    }

    @Basic
    @Column(name = "income_type")
    public Byte getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Byte incomeType) {
        this.incomeType = incomeType;
    }

    @Basic
    @Column(name = "if_NAV_type")
    public Byte getIfNavType() {
        return ifNavType;
    }

    public void setIfNavType(Byte ifNavType) {
        this.ifNavType = ifNavType;
    }

    @Basic
    @Column(name = "if_close")
    public Byte getIfClose() {
        return ifClose;
    }

    public void setIfClose(Byte ifClose) {
        this.ifClose = ifClose;
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
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBank that = (ProductBank) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (institutionManage != null ? !institutionManage.equals(that.institutionManage) : that.institutionManage != null)
            return false;
        if (purchaseThreshold != null ? !purchaseThreshold.equals(that.purchaseThreshold) : that.purchaseThreshold != null)
            return false;
        if (increasingUnit != null ? !increasingUnit.equals(that.increasingUnit) : that.increasingUnit != null)
            return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (nav != null ? !nav.equals(that.nav) : that.nav != null) return false;
        if (onPurchaseDate != null ? !onPurchaseDate.equals(that.onPurchaseDate) : that.onPurchaseDate != null)
            return false;
        if (offPurchaseDate != null ? !offPurchaseDate.equals(that.offPurchaseDate) : that.offPurchaseDate != null)
            return false;
        if (salesRegion != null ? !salesRegion.equals(that.salesRegion) : that.salesRegion != null) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (registerCode != null ? !registerCode.equals(that.registerCode) : that.registerCode != null) return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (investField != null ? !investField.equals(that.investField) : that.investField != null) return false;
        if (investRatio != null ? !investRatio.equals(that.investRatio) : that.investRatio != null) return false;
        if (riskLevel != null ? !riskLevel.equals(that.riskLevel) : that.riskLevel != null) return false;
        if (session != null ? !session.equals(that.session) : that.session != null) return false;
        if (custodian != null ? !custodian.equals(that.custodian) : that.custodian != null) return false;
        if (expectedRate != null ? !expectedRate.equals(that.expectedRate) : that.expectedRate != null) return false;
        if (ratePurchase != null ? !ratePurchase.equals(that.ratePurchase) : that.ratePurchase != null) return false;
        if (rateRedem != null ? !rateRedem.equals(that.rateRedem) : that.rateRedem != null) return false;
        if (rateManage != null ? !rateManage.equals(that.rateManage) : that.rateManage != null) return false;
        if (firstAccrDate != null ? !firstAccrDate.equals(that.firstAccrDate) : that.firstAccrDate != null)
            return false;
        if (onRedemptionDate != null ? !onRedemptionDate.equals(that.onRedemptionDate) : that.onRedemptionDate != null)
            return false;
        if (redemSpeed != null ? !redemSpeed.equals(that.redemSpeed) : that.redemSpeed != null) return false;
        if (payType != null ? !payType.equals(that.payType) : that.payType != null) return false;
        if (objectOriented != null ? !objectOriented.equals(that.objectOriented) : that.objectOriented != null)
            return false;
        if (sizeUpperLimit != null ? !sizeUpperLimit.equals(that.sizeUpperLimit) : that.sizeUpperLimit != null)
            return false;
        if (incomeType != null ? !incomeType.equals(that.incomeType) : that.incomeType != null) return false;
        if (ifNavType != null ? !ifNavType.equals(that.ifNavType) : that.ifNavType != null) return false;
        if (ifClose != null ? !ifClose.equals(that.ifClose) : that.ifClose != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institutionManage != null ? institutionManage.hashCode() : 0);
        result = 31 * result + (purchaseThreshold != null ? purchaseThreshold.hashCode() : 0);
        result = 31 * result + (increasingUnit != null ? increasingUnit.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (nav != null ? nav.hashCode() : 0);
        result = 31 * result + (onPurchaseDate != null ? onPurchaseDate.hashCode() : 0);
        result = 31 * result + (offPurchaseDate != null ? offPurchaseDate.hashCode() : 0);
        result = 31 * result + (salesRegion != null ? salesRegion.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (registerCode != null ? registerCode.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (investField != null ? investField.hashCode() : 0);
        result = 31 * result + (investRatio != null ? investRatio.hashCode() : 0);
        result = 31 * result + (riskLevel != null ? riskLevel.hashCode() : 0);
        result = 31 * result + (session != null ? session.hashCode() : 0);
        result = 31 * result + (custodian != null ? custodian.hashCode() : 0);
        result = 31 * result + (expectedRate != null ? expectedRate.hashCode() : 0);
        result = 31 * result + (ratePurchase != null ? ratePurchase.hashCode() : 0);
        result = 31 * result + (rateRedem != null ? rateRedem.hashCode() : 0);
        result = 31 * result + (rateManage != null ? rateManage.hashCode() : 0);
        result = 31 * result + (firstAccrDate != null ? firstAccrDate.hashCode() : 0);
        result = 31 * result + (onRedemptionDate != null ? onRedemptionDate.hashCode() : 0);
        result = 31 * result + (redemSpeed != null ? redemSpeed.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (objectOriented != null ? objectOriented.hashCode() : 0);
        result = 31 * result + (sizeUpperLimit != null ? sizeUpperLimit.hashCode() : 0);
        result = 31 * result + (incomeType != null ? incomeType.hashCode() : 0);
        result = 31 * result + (ifNavType != null ? ifNavType.hashCode() : 0);
        result = 31 * result + (ifClose != null ? ifClose.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
