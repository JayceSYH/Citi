package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "bank_daily_history", schema = "citi", catalog = "")
public class BankDailyHistory {
    private int id;
    private Date date;
    private BigDecimal nav;
    private Integer bankId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    @Column(name = "bank_id")
    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankDailyHistory that = (BankDailyHistory) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (nav != null ? !nav.equals(that.nav) : that.nav != null) return false;
        if (bankId != null ? !bankId.equals(that.bankId) : that.bankId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (nav != null ? nav.hashCode() : 0);
        result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
        return result;
    }
}
