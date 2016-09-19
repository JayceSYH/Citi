package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "bond_index_return", schema = "citi", catalog = "")
public class BondIndexReturn {
    private int id;
    private BigDecimal bondIndexReturn;
    private Date date;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bond_index_return")
    public BigDecimal getBondIndexReturn() {
        return bondIndexReturn;
    }

    public void setBondIndexReturn(BigDecimal bondIndexReturn) {
        this.bondIndexReturn = bondIndexReturn;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondIndexReturn that = (BondIndexReturn) o;

        if (id != that.id) return false;
        if (bondIndexReturn != null ? !bondIndexReturn.equals(that.bondIndexReturn) : that.bondIndexReturn != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bondIndexReturn != null ? bondIndexReturn.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
