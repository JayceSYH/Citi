package edu.nju.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "deleted_product", schema = "citi", catalog = "")
public class DeletedProduct {
    private int id;
    private int institution;
    private byte category;
    private byte type;
    private Timestamp createdAt;
    private Integer amountPeriod;
    private Timestamp updateAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "institution")
    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    @Basic
    @Column(name = "category")
    public byte getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    @Basic
    @Column(name = "type")
    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "amount_period")
    public Integer getAmountPeriod() {
        return amountPeriod;
    }

    public void setAmountPeriod(Integer amountPeriod) {
        this.amountPeriod = amountPeriod;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeletedProduct that = (DeletedProduct) o;

        if (id != that.id) return false;
        if (institution != that.institution) return false;
        if (category != that.category) return false;
        if (type != that.type) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (amountPeriod != null ? !amountPeriod.equals(that.amountPeriod) : that.amountPeriod != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + institution;
        result = 31 * result + (int) category;
        result = 31 * result + (int) type;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (amountPeriod != null ? amountPeriod.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
