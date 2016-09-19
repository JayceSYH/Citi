package edu.nju.model;

import javax.persistence.*;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "pay_way", schema = "citi", catalog = "")
public class PayWay {
    private int id;
    private int userId;
    private String payWay;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "pay_way")
    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayWay payWay1 = (PayWay) o;

        if (id != payWay1.id) return false;
        if (userId != payWay1.userId) return false;
        if (payWay != null ? !payWay.equals(payWay1.payWay) : payWay1.payWay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (payWay != null ? payWay.hashCode() : 0);
        return result;
    }
}
