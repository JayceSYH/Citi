package edu.nju.model;

import javax.persistence.*;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "institution_category_relation", schema = "citi", catalog = "")
public class InstitutionCategoryRelation {
    private int id;
    private String category;
    private Integer institutionId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "institution_id")
    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionCategoryRelation that = (InstitutionCategoryRelation) o;

        if (id != that.id) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (institutionId != null ? !institutionId.equals(that.institutionId) : that.institutionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (institutionId != null ? institutionId.hashCode() : 0);
        return result;
    }
}
