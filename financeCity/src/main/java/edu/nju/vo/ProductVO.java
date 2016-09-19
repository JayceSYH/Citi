package edu.nju.vo;

import edu.nju.service.POJO.NAVHistory;

/**
 * @author lsy
 * 投资产品
 */
public class ProductVO extends BaseVO{
	/**
	 * 产品id
	 */
	private Integer Id;
	/**
	 * 产品名称
	 */
	private String Name;
	/**
	 * 产品类型
	 */
	private String Type;
	/**
	 * 投资时间
	 */
	private String BuyingDate;
	/**
	 * 初始金额
	 */
	private Double BuyingValue;
	/**
	 * 当前价值
	 */
	private Double CurrentValue;
	/**
	 * 到期时间
	 */
	private String EndDate;
	/**
	 * 可取时间
	 */
	private String CanRedeemDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCanRedeemDate() {
		return CanRedeemDate;
	}

	public void setCanRedeemDate(String canRedeemDate) {
		CanRedeemDate = canRedeemDate;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getBuyingDate() {
		return BuyingDate;
	}

	public void setBuyingDate(String buyingDate) {
		BuyingDate = buyingDate;
	}

	public Double getBuyingValue() {
		return BuyingValue;
	}

	public void setBuyingValue(Double buyingValue) {
		BuyingValue = buyingValue;
	}

	public Double getCurrentValue() {
		return CurrentValue;
	}

	public void setCurrentValue(Double currentValue) {
		CurrentValue = currentValue;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
}
