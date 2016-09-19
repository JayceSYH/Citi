package edu.nju.vo;

import edu.nju.model.ProductBond;

/**
 * Created by Sun YuHao on 2016/8/31.
 */
public class BondVO {
    private String productType;
    private Integer pid;
    private String name;
    private Double year_rate;
    private Double nominal_interest_rate;
    private Integer life;
    
    public ProductBond getProductBond() {
		return productBond;
	}

	public void setProductBond(ProductBond productBond) {
		this.productBond = productBond;
	}

	private String type;
    private String code;
    private ProductBond productBond;

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

    public Double getYear_rate() {
        return year_rate;
    }

    public void setYear_rate(Double year_rate) {
        this.year_rate = year_rate;
    }

    public Double getNominal_interest_rate() {
        return nominal_interest_rate;
    }

    public void setNominal_interest_rate(Double nominal_interest_rate) {
        this.nominal_interest_rate = nominal_interest_rate;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
