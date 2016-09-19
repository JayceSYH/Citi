package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
@Entity
@Table(name = "product_fund", schema = "citi", catalog = "")
public class ProductFund {
    private int id;
    private String name;
    private String productCode;
    private BigDecimal yearlyRtnRate;
    private Byte category;
    private Byte state;
    private String managerName;
    private BigDecimal rateManage;
    private BigDecimal rateSubscribe;
    private BigDecimal rateRedem;
    private BigDecimal ratePurchase;
    private String riskIncomeDescription;
    private BigDecimal fundSize;
    private Integer shareSize;
    private BigDecimal nav;
    private Byte riskLevel;
    private String institutionManage;
    private String custodian;
    private Integer purchaseThreshold;
    private Integer increasingUnit;
    private Date onPurchaseDate;
    private Date offPurchaseDate;
    private Date firstAccrDate;
    private Date onRedemptionDate;
    private Integer length;
    private Integer subscribeSpeed;
    private String perfBenchmark;
    private String targetId;
    private Integer redemptionSpeed;
    private BigDecimal adjustNav;
    private BigDecimal accumNav;
    private Byte operationMode;
    private BigDecimal fundScore;
    private BigDecimal assetLiabilityRatio;
    private BigDecimal dayRate;
    private BigDecimal institutionNetworthRatio;
    private BigDecimal stockBondProportion;
    private Timestamp firstAccrRate;

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
    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "yearly_rtn_rate")
    public BigDecimal getYearlyRtnRate() {
        return yearlyRtnRate;
    }

    public void setYearlyRtnRate(BigDecimal yearlyRtnRate) {
        this.yearlyRtnRate = yearlyRtnRate;
    }

    @Basic
    @Column(name = "category")
    public Byte getCategory() {
        return category;
    }

    public void setCategory(Byte category) {
        this.category = category;
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
    @Column(name = "manager_name")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
    @Column(name = "rate_subscribe")
    public BigDecimal getRateSubscribe() {
        return rateSubscribe;
    }

    public void setRateSubscribe(BigDecimal rateSubscribe) {
        this.rateSubscribe = rateSubscribe;
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
    @Column(name = "rate_purchase")
    public BigDecimal getRatePurchase() {
        return ratePurchase;
    }

    public void setRatePurchase(BigDecimal ratePurchase) {
        this.ratePurchase = ratePurchase;
    }

    @Basic
    @Column(name = "risk_income_description")
    public String getRiskIncomeDescription() {
        return riskIncomeDescription;
    }

    public void setRiskIncomeDescription(String riskIncomeDescription) {
        this.riskIncomeDescription = riskIncomeDescription;
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
    @Column(name = "share_size")
    public Integer getShareSize() {
        return shareSize;
    }

    public void setShareSize(Integer shareSize) {
        this.shareSize = shareSize;
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
    @Column(name = "risk_level")
    public Byte getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Byte riskLevel) {
        this.riskLevel = riskLevel;
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
    @Column(name = "custodian")
    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
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
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "subscribe_speed")
    public Integer getSubscribeSpeed() {
        return subscribeSpeed;
    }

    public void setSubscribeSpeed(Integer subscribeSpeed) {
        this.subscribeSpeed = subscribeSpeed;
    }

    @Basic
    @Column(name = "perf_benchmark")
    public String getPerfBenchmark() {
        return perfBenchmark;
    }

    public void setPerfBenchmark(String perfBenchmark) {
        this.perfBenchmark = perfBenchmark;
    }

    @Basic
    @Column(name = "target_ID")
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name = "redemption_speed")
    public Integer getRedemptionSpeed() {
        return redemptionSpeed;
    }

    public void setRedemptionSpeed(Integer redemptionSpeed) {
        this.redemptionSpeed = redemptionSpeed;
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
    @Column(name = "accum_NAV")
    public BigDecimal getAccumNav() {
        return accumNav;
    }

    public void setAccumNav(BigDecimal accumNav) {
        this.accumNav = accumNav;
    }

    @Basic
    @Column(name = "operation_mode")
    public Byte getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(Byte operationMode) {
        this.operationMode = operationMode;
    }

    @Basic
    @Column(name = "fund_score")
    public BigDecimal getFundScore() {
        return fundScore;
    }

    public void setFundScore(BigDecimal fundScore) {
        this.fundScore = fundScore;
    }

    @Basic
    @Column(name = "asset_liability_ratio")
    public BigDecimal getAssetLiabilityRatio() {
        return assetLiabilityRatio;
    }

    public void setAssetLiabilityRatio(BigDecimal assetLiabilityRatio) {
        this.assetLiabilityRatio = assetLiabilityRatio;
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
    @Column(name = "institution_networth_ratio")
    public BigDecimal getInstitutionNetworthRatio() {
        return institutionNetworthRatio;
    }

    public void setInstitutionNetworthRatio(BigDecimal institutionNetworthRatio) {
        this.institutionNetworthRatio = institutionNetworthRatio;
    }

    @Basic
    @Column(name = "stock_bond_proportion")
    public BigDecimal getStockBondProportion() {
        return stockBondProportion;
    }

    public void setStockBondProportion(BigDecimal stockBondProportion) {
        this.stockBondProportion = stockBondProportion;
    }

    @Basic
    @Column(name = "first_accr_rate")
    public Timestamp getFirstAccrRate() {
        return firstAccrRate;
    }

    public void setFirstAccrRate(Timestamp firstAccrRate) {
        this.firstAccrRate = firstAccrRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductFund that = (ProductFund) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (yearlyRtnRate != null ? !yearlyRtnRate.equals(that.yearlyRtnRate) : that.yearlyRtnRate != null)
            return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (managerName != null ? !managerName.equals(that.managerName) : that.managerName != null) return false;
        if (rateManage != null ? !rateManage.equals(that.rateManage) : that.rateManage != null) return false;
        if (rateSubscribe != null ? !rateSubscribe.equals(that.rateSubscribe) : that.rateSubscribe != null)
            return false;
        if (rateRedem != null ? !rateRedem.equals(that.rateRedem) : that.rateRedem != null) return false;
        if (ratePurchase != null ? !ratePurchase.equals(that.ratePurchase) : that.ratePurchase != null) return false;
        if (riskIncomeDescription != null ? !riskIncomeDescription.equals(that.riskIncomeDescription) : that.riskIncomeDescription != null)
            return false;
        if (fundSize != null ? !fundSize.equals(that.fundSize) : that.fundSize != null) return false;
        if (shareSize != null ? !shareSize.equals(that.shareSize) : that.shareSize != null) return false;
        if (nav != null ? !nav.equals(that.nav) : that.nav != null) return false;
        if (riskLevel != null ? !riskLevel.equals(that.riskLevel) : that.riskLevel != null) return false;
        if (institutionManage != null ? !institutionManage.equals(that.institutionManage) : that.institutionManage != null)
            return false;
        if (custodian != null ? !custodian.equals(that.custodian) : that.custodian != null) return false;
        if (purchaseThreshold != null ? !purchaseThreshold.equals(that.purchaseThreshold) : that.purchaseThreshold != null)
            return false;
        if (increasingUnit != null ? !increasingUnit.equals(that.increasingUnit) : that.increasingUnit != null)
            return false;
        if (onPurchaseDate != null ? !onPurchaseDate.equals(that.onPurchaseDate) : that.onPurchaseDate != null)
            return false;
        if (offPurchaseDate != null ? !offPurchaseDate.equals(that.offPurchaseDate) : that.offPurchaseDate != null)
            return false;
        if (firstAccrDate != null ? !firstAccrDate.equals(that.firstAccrDate) : that.firstAccrDate != null)
            return false;
        if (onRedemptionDate != null ? !onRedemptionDate.equals(that.onRedemptionDate) : that.onRedemptionDate != null)
            return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (subscribeSpeed != null ? !subscribeSpeed.equals(that.subscribeSpeed) : that.subscribeSpeed != null)
            return false;
        if (perfBenchmark != null ? !perfBenchmark.equals(that.perfBenchmark) : that.perfBenchmark != null)
            return false;
        if (targetId != null ? !targetId.equals(that.targetId) : that.targetId != null) return false;
        if (redemptionSpeed != null ? !redemptionSpeed.equals(that.redemptionSpeed) : that.redemptionSpeed != null)
            return false;
        if (adjustNav != null ? !adjustNav.equals(that.adjustNav) : that.adjustNav != null) return false;
        if (accumNav != null ? !accumNav.equals(that.accumNav) : that.accumNav != null) return false;
        if (operationMode != null ? !operationMode.equals(that.operationMode) : that.operationMode != null)
            return false;
        if (fundScore != null ? !fundScore.equals(that.fundScore) : that.fundScore != null) return false;
        if (assetLiabilityRatio != null ? !assetLiabilityRatio.equals(that.assetLiabilityRatio) : that.assetLiabilityRatio != null)
            return false;
        if (dayRate != null ? !dayRate.equals(that.dayRate) : that.dayRate != null) return false;
        if (institutionNetworthRatio != null ? !institutionNetworthRatio.equals(that.institutionNetworthRatio) : that.institutionNetworthRatio != null)
            return false;
        if (stockBondProportion != null ? !stockBondProportion.equals(that.stockBondProportion) : that.stockBondProportion != null)
            return false;
        if (firstAccrRate != null ? !firstAccrRate.equals(that.firstAccrRate) : that.firstAccrRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (yearlyRtnRate != null ? yearlyRtnRate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (managerName != null ? managerName.hashCode() : 0);
        result = 31 * result + (rateManage != null ? rateManage.hashCode() : 0);
        result = 31 * result + (rateSubscribe != null ? rateSubscribe.hashCode() : 0);
        result = 31 * result + (rateRedem != null ? rateRedem.hashCode() : 0);
        result = 31 * result + (ratePurchase != null ? ratePurchase.hashCode() : 0);
        result = 31 * result + (riskIncomeDescription != null ? riskIncomeDescription.hashCode() : 0);
        result = 31 * result + (fundSize != null ? fundSize.hashCode() : 0);
        result = 31 * result + (shareSize != null ? shareSize.hashCode() : 0);
        result = 31 * result + (nav != null ? nav.hashCode() : 0);
        result = 31 * result + (riskLevel != null ? riskLevel.hashCode() : 0);
        result = 31 * result + (institutionManage != null ? institutionManage.hashCode() : 0);
        result = 31 * result + (custodian != null ? custodian.hashCode() : 0);
        result = 31 * result + (purchaseThreshold != null ? purchaseThreshold.hashCode() : 0);
        result = 31 * result + (increasingUnit != null ? increasingUnit.hashCode() : 0);
        result = 31 * result + (onPurchaseDate != null ? onPurchaseDate.hashCode() : 0);
        result = 31 * result + (offPurchaseDate != null ? offPurchaseDate.hashCode() : 0);
        result = 31 * result + (firstAccrDate != null ? firstAccrDate.hashCode() : 0);
        result = 31 * result + (onRedemptionDate != null ? onRedemptionDate.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (subscribeSpeed != null ? subscribeSpeed.hashCode() : 0);
        result = 31 * result + (perfBenchmark != null ? perfBenchmark.hashCode() : 0);
        result = 31 * result + (targetId != null ? targetId.hashCode() : 0);
        result = 31 * result + (redemptionSpeed != null ? redemptionSpeed.hashCode() : 0);
        result = 31 * result + (adjustNav != null ? adjustNav.hashCode() : 0);
        result = 31 * result + (accumNav != null ? accumNav.hashCode() : 0);
        result = 31 * result + (operationMode != null ? operationMode.hashCode() : 0);
        result = 31 * result + (fundScore != null ? fundScore.hashCode() : 0);
        result = 31 * result + (assetLiabilityRatio != null ? assetLiabilityRatio.hashCode() : 0);
        result = 31 * result + (dayRate != null ? dayRate.hashCode() : 0);
        result = 31 * result + (institutionNetworthRatio != null ? institutionNetworthRatio.hashCode() : 0);
        result = 31 * result + (stockBondProportion != null ? stockBondProportion.hashCode() : 0);
        result = 31 * result + (firstAccrRate != null ? firstAccrRate.hashCode() : 0);
        return result;
    }
}
