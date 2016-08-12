package edu.nju.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dell on 2016/8/11.
 */
@Entity
@Table(name = "user_family_speeding", schema = "citi", catalog = "")
public class UserFamilySpeeding {
    private Integer id;
    private Byte isPrepare;
    private Byte ifNeed;

    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_prepare")
    public Byte getIsPrepare() {
        return isPrepare;
    }

    public void setIsPrepare(Byte isPrepare) {
        this.isPrepare = isPrepare;
    }

    @Basic
    @Column(name = "if_need")
    public Byte getIfNeed() {
        return ifNeed;
    }

    public void setIfNeed(Byte ifNeed) {
        this.ifNeed = ifNeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFamilySpeeding that = (UserFamilySpeeding) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isPrepare != null ? !isPrepare.equals(that.isPrepare) : that.isPrepare != null) return false;
        if (ifNeed != null ? !ifNeed.equals(that.ifNeed) : that.ifNeed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isPrepare != null ? isPrepare.hashCode() : 0);
        result = 31 * result + (ifNeed != null ? ifNeed.hashCode() : 0);
        return result;
    }
}