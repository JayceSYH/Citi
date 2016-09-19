package edu.nju.model;

import javax.persistence.*;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "name_to_id", schema = "citi", catalog = "")
public class NameToId {
    private String name;
    private int id;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameToId nameToId = (NameToId) o;

        if (id != nameToId.id) return false;
        if (name != null ? !name.equals(nameToId.name) : nameToId.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
