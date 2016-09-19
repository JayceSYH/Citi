package edu.nju.service.POJO;

/**
 * Created by Sun YuHao on 2016/9/15.
 */
public class PortfolioScores {
    private int yield_score;
    private int risk_score;
    private int flow_score;
    private int length_score;

    public int getRisk_score() {
        return risk_score;
    }

    public void setRisk_score(int risk_score) {
        this.risk_score = risk_score;
    }

    public int getFlow_score() {
        return flow_score;
    }

    public void setFlow_score(int flow_score) {
        this.flow_score = flow_score;
    }

    public int getLength_score() {
        return length_score;
    }

    public void setLength_score(int length_score) {
        this.length_score = length_score;
    }

    public int getYield_score() {
        return yield_score;
    }

    public void setYield_score(int yield_score) {
        this.yield_score = yield_score;
    }
}
