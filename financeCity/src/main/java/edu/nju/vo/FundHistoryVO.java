package edu.nju.vo;

import edu.nju.service.POJO.NAVHistory;

/**
 * Created by Sun YuHao on 2016/9/10.
 */
public class FundHistoryVO extends BaseVO {
    private NAVHistory[] data;

    public NAVHistory[] getData() {
        return data;
    }

    public void setData(NAVHistory[] data) {
        this.data = data;
    }
}
