package edu.nju.vo;

import edu.nju.service.POJO.Investment_portfolio;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/29.
         */
public class CurrentInvestmentVO extends BaseVO{
    private List<Investment_portfolio> investmentPortfolioList;

    public List<Investment_portfolio> getInvestmentPortfolioList() {
        return investmentPortfolioList;
    }

    public void setInvestmentPortfolioList(List<Investment_portfolio> investmentPortfolioList) {
        this.investmentPortfolioList = investmentPortfolioList;
    }
}
