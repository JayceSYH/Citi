package nju.financecity_android.util;

import android.app.Activity;
import android.graphics.Color;
import nju.financecity_android.R;
import nju.financecity_android.controller.activity.BankDetailActivity;
import nju.financecity_android.controller.activity.Investment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coral on 16-9-14.
 */
public class ColorBoard {

    private static List<Integer> colorList = null;

    private static int p = 0;
    static {
        colorList = new ArrayList<>();
        colorList.add(Color.parseColor("#c601f8"));
        colorList.add(Color.parseColor("#fd3278"));
        colorList.add(Color.parseColor("#11b7f3"));
        colorList.add(Color.parseColor("#56b354"));
        colorList.add(Color.parseColor("#f9d540"));
        colorList.add(Color.parseColor("#ffb01d"));
        colorList.add(Color.parseColor("#3d70fc"));
    }

    public static int nextColor() {
        return colorList.get(p++ % 7);
    }
}
