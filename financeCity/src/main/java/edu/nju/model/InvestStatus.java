package edu.nju.model;

import javax.persistence.*;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
@Entity
@Table(name = "invest_status", schema = "citi", catalog = "")
public class InvestStatus {
    private int userId;
    private int portfolioId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "portfolio_id")
    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvestStatus that = (InvestStatus) o;

        if (userId != that.userId) return false;
        if (portfolioId != that.portfolioId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + portfolioId;
        return result;
    }
}
