package edu.nju.vo;

import edu.nju.service.POJO.NAVHistory;

/**
 * @author lsy
 * 产品详情
 */
public class ProductDetailVO extends BaseVO {
	private String type;
	private Object data;
	private NAVHistory[] history;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public NAVHistory[] getHistory() {
		return history;
	}

	public void setHistory(NAVHistory[] history) {
		this.history = history;
	}
}
