package edu.nju.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
public class User {
    private int id;
    private String account;
    private Integer choosedQuestions;
    private Timestamp createdAt;
    private String email;
    private String password;
    private String phone;
    private String secureAnswer;
    private Integer state;
    private Timestamp updateAt;
    private String username;
    private String name;
    private Byte ifCity;
    private String city;
    private Date birthday;
    private Integer monthlyExpense;
    private Integer income;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "choosed_questions")
    public Integer getChoosedQuestions() {
        return choosedQuestions;
    }

    public void setChoosedQuestions(Integer choosedQuestions) {
        this.choosedQuestions = choosedQuestions;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "secure_answer")
    public String getSecureAnswer() {
        return secureAnswer;
    }

    public void setSecureAnswer(String secureAnswer) {
        this.secureAnswer = secureAnswer;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "if_city")
    public Byte getIfCity() {
        return ifCity;
    }

    public void setIfCity(Byte ifCity) {
        this.ifCity = ifCity;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "monthly_expense")
    public Integer getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(Integer monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    @Basic
    @Column(name = "income")
    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (choosedQuestions != null ? !choosedQuestions.equals(user.choosedQuestions) : user.choosedQuestions != null)
            return false;
        if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (secureAnswer != null ? !secureAnswer.equals(user.secureAnswer) : user.secureAnswer != null) return false;
        if (state != null ? !state.equals(user.state) : user.state != null) return false;
        if (updateAt != null ? !updateAt.equals(user.updateAt) : user.updateAt != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (ifCity != null ? !ifCity.equals(user.ifCity) : user.ifCity != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (monthlyExpense != null ? !monthlyExpense.equals(user.monthlyExpense) : user.monthlyExpense != null)
            return false;
        if (income != null ? !income.equals(user.income) : user.income != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (choosedQuestions != null ? choosedQuestions.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (secureAnswer != null ? secureAnswer.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ifCity != null ? ifCity.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (monthlyExpense != null ? monthlyExpense.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        return result;
    }
}
