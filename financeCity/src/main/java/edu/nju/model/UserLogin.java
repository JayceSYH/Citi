package edu.nju.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "user_login", schema = "citi", catalog = "")
public class UserLogin {
    private int userId;
    private String session;
    private Timestamp date;
    private String loginId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "session")
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (userId != userLogin.userId) return false;
        if (session != null ? !session.equals(userLogin.session) : userLogin.session != null) return false;
        if (date != null ? !date.equals(userLogin.date) : userLogin.date != null) return false;
        if (loginId != null ? !loginId.equals(userLogin.loginId) : userLogin.loginId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (session != null ? session.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        return result;
    }
}
