package edu.nju.vo;

/**
 * Created by Sun YuHao on 2016/9/11.
 */
public class OrderResultVO extends BaseVO {
    private String amount;
    private String checkCode;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
