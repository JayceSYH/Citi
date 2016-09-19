package nju.financecity_android.vo;

/**
 * Created by coral on 16-9-15.
 */
public class RecommendSingleVO {
    public int id;
    public double amount;
    public String name;
    public String productType;
    public double percentage;

    public RecommendSingleVO(){}
    public RecommendSingleVO(int id, double amount, String name, String productType, double percentage) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.productType = productType;
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "RecommendSingleVO{" +
                "id=" + id +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                ", productType='" + productType + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
