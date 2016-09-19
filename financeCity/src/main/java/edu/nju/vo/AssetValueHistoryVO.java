package edu.nju.vo;

import edu.nju.service.POJO.AssetValue;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/10.
 */
public class AssetValueHistoryVO extends BaseVO {
    private List<AssetValue> assetValues;

    public List<AssetValue> getAssetValues() {
        return assetValues;
    }

    public void setAssetValues(List<AssetValue> assetValues) {
        this.assetValues = assetValues;
    }
}
