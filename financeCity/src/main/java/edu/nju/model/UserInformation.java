package edu.nju.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "user_information", schema = "citi", catalog = "")
public class UserInformation {
    private int id;
    private Timestamp createdAt;
    private Byte experience;
    private Byte intention;
    private Byte job;
    private Byte marriageStatus;
    private Integer salary;
    private Byte state;
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
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "experience")
    public Byte getExperience() {
        return experience;
    }

    public void setExperience(Byte experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "intention")
    public Byte getIntention() {
        return intention;
    }

    public void setIntention(Byte intention) {
        this.intention = intention;
    }

    @Basic
    @Column(name = "job")
    public Byte getJob() {
        return job;
    }

    public void setJob(Byte job) {
        this.job = job;
    }

    @Basic
    @Column(name = "marriage_status")
    public Byte getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(Byte marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    @Basic
    @Column(name = "salary")
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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

        UserInformation that = (UserInformation) o;

        if (id != that.id) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;
        if (intention != null ? !intention.equals(that.intention) : that.intention != null) return false;
        if (job != null ? !job.equals(that.job) : that.job != null) return false;
        if (marriageStatus != null ? !marriageStatus.equals(that.marriageStatus) : that.marriageStatus != null)
            return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (intention != null ? intention.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (marriageStatus != null ? marriageStatus.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
