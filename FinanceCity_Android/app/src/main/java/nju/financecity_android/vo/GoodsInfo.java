package nju.financecity_android.vo;

import java.io.Serializable;

/**
 * Created by coral on 16-9-8.
 */
public class GoodsInfo implements Serializable {
    public String goodsName = "";
    public String goodsId = "";
    public int price = 0;
    public int amount = 0;
    public String type = "", subType = "";    // "基金" "保险" "债券" "银行理财"
    public int initialAmount = 0;       // 起购金额
    public int increasingUnit = 0;

    public GoodsInfo() {}

    public GoodsInfo(String goodsId, String goodsName, int price, int amount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.amount = amount;
    }

    public int getSum() {
        if (type.equals("Bank") || type.equals("Fund")) {
            return amount;
        }
        else if (type.equals("Insurance") || type.equals("Bond")) {
            return price * amount;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", initialAmount=" + initialAmount +
                ", increasingUnit=" + increasingUnit +
                '}';
    }
}
