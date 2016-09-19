package edu.nju.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "bond_index", schema = "citi", catalog = "")
public class BondIndex {
    private int id;
    private BigDecimal bondIndex;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bond_index")
    public BigDecimal getBondIndex() {
        return bondIndex;
    }

    public void setBondIndex(BigDecimal bondIndex) {
        this.bondIndex = bondIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BondIndex bondIndex1 = (BondIndex) o;

        if (id != bondIndex1.id) return false;
        if (bondIndex != null ? !bondIndex.equals(bondIndex1.bondIndex) : bondIndex1.bondIndex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bondIndex != null ? bondIndex.hashCode() : 0);
        return result;
    }
}
