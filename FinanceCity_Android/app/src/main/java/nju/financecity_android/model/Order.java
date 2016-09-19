package nju.financecity_android.model;

import nju.financecity_android.vo.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coral on 16-9-13.
 */
public class Order {

    public Order() {
        goodsList = new ArrayList<>();
    }

    public void addGoods(GoodsInfo info) {
        goodsList.add(info);
    }

    public void submit() {

    }

    private List<GoodsInfo> goodsList;
}
