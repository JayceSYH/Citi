package edu.nju.vo;

import edu.nju.model.ProductFund;
import edu.nju.service.POJO.NAVHistory;

/**
 * Created by Sun YuHao on 2016/8/31.
 */
public class FundVO {
    private String productType;//产品类型（小类）
    private Integer pid;//产品id
    private String name;//产品名
    private Double expected_income_rate;//预期收益率
    private String state;//状态
    private Integer net_value;//单位净值
    private String type;//类型字段（小类）
    private String sid;//
    private Double mng_charge_rate;
    private String est_date;//
    private ProductFund productFund;
    private NAVHistory[] history;
    private Double year_rate;

    public Double getYear_rate() {
        return year_rate;
    }

    public void setYear_rate(Double year_rate) {
        this.year_rate = year_rate;
    }

    public NAVHistory[] getHistory() {
		return history;
	}

	public void setHistory(NAVHistory[] history) {
		this.history = history;
	}

	public ProductFund getProductFund() {
		return productFund;
	}

	public void setProductFund(ProductFund productFund) {
		this.productFund = productFund;
	}

	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExpected_income_rate() {
        return expected_income_rate;
    }

    public void setExpected_income_rate(Double expected_income_rate) {
        this.expected_income_rate = expected_income_rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNet_value() {
        return net_value;
    }

    public void setNet_value(Integer net_value) {
        this.net_value = net_value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Double getMng_charge_rate() {
        return mng_charge_rate;
    }

    public void setMng_charge_rate(Double mng_charge_rate) {
        this.mng_charge_rate = mng_charge_rate;
    }

    public String getEst_date() {
        return est_date;
    }

    public void setEst_date(String est_date) {
        this.est_date = est_date;
    }
}
