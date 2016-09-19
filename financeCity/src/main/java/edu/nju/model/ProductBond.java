package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/16.
 */
@Entity
@Table(name = "product_bond", schema = "citi", catalog = "")
public class ProductBond {
    private int id;
    private String name;
    private String institutionIssue;
    private String productCode;
    private String abbrName;
    private Date onIssueDate;
    private Date offIssueDate;
    private Date firstAccrDate;
    private Integer length;
    private BigDecimal releaseAmount;
    private Date publishDate;
    private String exchange;
    private Byte creditGrade;
    private String institutionUnderwriter;
    private Byte type;
    private BigDecimal par;
    private BigDecimal issuePrice;
    private Byte releaseObject;
    private Byte couponType;
    private Byte couponFreq;
    private Date maturityDate;
    private Date listDate;
    private String objectApplyBuy;
    private Byte riskGrade;
    private String releaseWay;
    private String objectOriented;
    private Byte taxState;
    private BigDecimal coupon;
    private Date firstRedeemDate;
    private Date firstRedeemAccrDate;
    private Byte state;
    private BigDecimal adjustYearlyRate;

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
    @Column(name = "institution_issue")
    public String getInstitutionIssue() {
        return institutionIssue;
    }

    public void setInstitutionIssue(String institutionIssue) {
        this.institutionIssue = institutionIssue;
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
    @Column(name = "abbr_name")
    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    @Basic
    @Column(name = "on_issue_date")
    public Date getOnIssueDate() {
        return onIssueDate;
    }

    public void setOnIssueDate(Date onIssueDate) {
        this.onIssueDate = onIssueDate;
    }

    @Basic
    @Column(name = "off_issue_date")
    public Date getOffIssueDate() {
        return offIssueDate;
    }

    public void setOffIssueDate(Date offIssueDate) {
        this.offIssueDate = offIssueDate;
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
    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "release_amount")
    public BigDecimal getReleaseAmount() {
        return releaseAmount;
    }

    public void setReleaseAmount(BigDecimal releaseAmount) {
        this.releaseAmount = releaseAmount;
    }

    @Basic
    @Column(name = "publish_date")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "exchange")
    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Basic
    @Column(name = "credit_grade")
    public Byte getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(Byte creditGrade) {
        this.creditGrade = creditGrade;
    }

    @Basic
    @Column(name = "institution_underwriter")
    public String getInstitutionUnderwriter() {
        return institutionUnderwriter;
    }

    public void setInstitutionUnderwriter(String institutionUnderwriter) {
        this.institutionUnderwriter = institutionUnderwriter;
    }

    @Basic
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "par")
    public BigDecimal getPar() {
        return par;
    }

    public void setPar(BigDecimal par) {
        this.par = par;
    }

    @Basic
    @Column(name = "issue_price")
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    @Basic
    @Column(name = "release_object")
    public Byte getReleaseObject() {
        return releaseObject;
    }

    public void setReleaseObject(Byte releaseObject) {
        this.releaseObject = releaseObject;
    }

    @Basic
    @Column(name = "coupon_type")
    public Byte getCouponType() {
        return couponType;
    }

    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    @Basic
    @Column(name = "coupon_freq")
    public Byte getCouponFreq() {
        return couponFreq;
    }

    public void setCouponFreq(Byte couponFreq) {
        this.couponFreq = couponFreq;
    }

    @Basic
    @Column(name = "maturity_date")
    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    @Basic
    @Column(name = "list_date")
    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    @Basic
    @Column(name = "object_apply_buy")
    public String getObjectApplyBuy() {
        return objectApplyBuy;
    }

    public void setObjectApplyBuy(String objectApplyBuy) {
        this.objectApplyBuy = objectApplyBuy;
    }

    @Basic
    @Column(name = "risk_grade")
    public Byte getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(Byte riskGrade) {
        this.riskGrade = riskGrade;
    }

    @Basic
    @Column(name = "release_way")
    public String getReleaseWay() {
        return releaseWay;
    }

    public void setReleaseWay(String releaseWay) {
        this.releaseWay = releaseWay;
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
    @Column(name = "tax_state")
    public Byte getTaxState() {
        return taxState;
    }

    public void setTaxState(Byte taxState) {
        this.taxState = taxState;
    }

    @Basic
    @Column(name = "coupon")
    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    @Basic
    @Column(name = "first_redeem_date")
    public Date getFirstRedeemDate() {
        return firstRedeemDate;
    }

    public void setFirstRedeemDate(Date firstRedeemDate) {
        this.firstRedeemDate = firstRedeemDate;
    }

    @Basic
    @Column(name = "first_redeem_accr_date")
    public Date getFirstRedeemAccrDate() {
        return firstRedeemAccrDate;
    }

    public void setFirstRedeemAccrDate(Date firstRedeemAccrDate) {
        this.firstRedeemAccrDate = firstRedeemAccrDate;
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
    @Column(name = "adjust_yearly_rate")
    public BigDecimal getAdjustYearlyRate() {
        return adjustYearlyRate;
    }

    public void setAdjustYearlyRate(BigDecimal adjustYearlyRate) {
        this.adjustYearlyRate = adjustYearlyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductBond that = (ProductBond) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (institutionIssue != null ? !institutionIssue.equals(that.institutionIssue) : that.institutionIssue != null)
            return false;
        if (productCode != null ? !productCode.equals(that.productCode) : that.productCode != null) return false;
        if (abbrName != null ? !abbrName.equals(that.abbrName) : that.abbrName != null) return false;
        if (onIssueDate != null ? !onIssueDate.equals(that.onIssueDate) : that.onIssueDate != null) return false;
        if (offIssueDate != null ? !offIssueDate.equals(that.offIssueDate) : that.offIssueDate != null) return false;
        if (firstAccrDate != null ? !firstAccrDate.equals(that.firstAccrDate) : that.firstAccrDate != null)
            return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (releaseAmount != null ? !releaseAmount.equals(that.releaseAmount) : that.releaseAmount != null)
            return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        if (exchange != null ? !exchange.equals(that.exchange) : that.exchange != null) return false;
        if (creditGrade != null ? !creditGrade.equals(that.creditGrade) : that.creditGrade != null) return false;
        if (institutionUnderwriter != null ? !institutionUnderwriter.equals(that.institutionUnderwriter) : that.institutionUnderwriter != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (par != null ? !par.equals(that.par) : that.par != null) return false;
        if (issuePrice != null ? !issuePrice.equals(that.issuePrice) : that.issuePrice != null) return false;
        if (releaseObject != null ? !releaseObject.equals(that.releaseObject) : that.releaseObject != null)
            return false;
        if (couponType != null ? !couponType.equals(that.couponType) : that.couponType != null) return false;
        if (couponFreq != null ? !couponFreq.equals(that.couponFreq) : that.couponFreq != null) return false;
        if (maturityDate != null ? !maturityDate.equals(that.maturityDate) : that.maturityDate != null) return false;
        if (listDate != null ? !listDate.equals(that.listDate) : that.listDate != null) return false;
        if (objectApplyBuy != null ? !objectApplyBuy.equals(that.objectApplyBuy) : that.objectApplyBuy != null)
            return false;
        if (riskGrade != null ? !riskGrade.equals(that.riskGrade) : that.riskGrade != null) return false;
        if (releaseWay != null ? !releaseWay.equals(that.releaseWay) : that.releaseWay != null) return false;
        if (objectOriented != null ? !objectOriented.equals(that.objectOriented) : that.objectOriented != null)
            return false;
        if (taxState != null ? !taxState.equals(that.taxState) : that.taxState != null) return false;
        if (coupon != null ? !coupon.equals(that.coupon) : that.coupon != null) return false;
        if (firstRedeemDate != null ? !firstRedeemDate.equals(that.firstRedeemDate) : that.firstRedeemDate != null)
            return false;
        if (firstRedeemAccrDate != null ? !firstRedeemAccrDate.equals(that.firstRedeemAccrDate) : that.firstRedeemAccrDate != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (adjustYearlyRate != null ? !adjustYearlyRate.equals(that.adjustYearlyRate) : that.adjustYearlyRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (institutionIssue != null ? institutionIssue.hashCode() : 0);
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (abbrName != null ? abbrName.hashCode() : 0);
        result = 31 * result + (onIssueDate != null ? onIssueDate.hashCode() : 0);
        result = 31 * result + (offIssueDate != null ? offIssueDate.hashCode() : 0);
        result = 31 * result + (firstAccrDate != null ? firstAccrDate.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (releaseAmount != null ? releaseAmount.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
        result = 31 * result + (creditGrade != null ? creditGrade.hashCode() : 0);
        result = 31 * result + (institutionUnderwriter != null ? institutionUnderwriter.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (par != null ? par.hashCode() : 0);
        result = 31 * result + (issuePrice != null ? issuePrice.hashCode() : 0);
        result = 31 * result + (releaseObject != null ? releaseObject.hashCode() : 0);
        result = 31 * result + (couponType != null ? couponType.hashCode() : 0);
        result = 31 * result + (couponFreq != null ? couponFreq.hashCode() : 0);
        result = 31 * result + (maturityDate != null ? maturityDate.hashCode() : 0);
        result = 31 * result + (listDate != null ? listDate.hashCode() : 0);
        result = 31 * result + (objectApplyBuy != null ? objectApplyBuy.hashCode() : 0);
        result = 31 * result + (riskGrade != null ? riskGrade.hashCode() : 0);
        result = 31 * result + (releaseWay != null ? releaseWay.hashCode() : 0);
        result = 31 * result + (objectOriented != null ? objectOriented.hashCode() : 0);
        result = 31 * result + (taxState != null ? taxState.hashCode() : 0);
        result = 31 * result + (coupon != null ? coupon.hashCode() : 0);
        result = 31 * result + (firstRedeemDate != null ? firstRedeemDate.hashCode() : 0);
        result = 31 * result + (firstRedeemAccrDate != null ? firstRedeemAccrDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (adjustYearlyRate != null ? adjustYearlyRate.hashCode() : 0);
        return result;
    }
}
