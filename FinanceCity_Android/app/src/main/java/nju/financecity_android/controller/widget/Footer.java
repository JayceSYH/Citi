package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import nju.financecity_android.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义footer导航。
 *
 * Created by alpaca. on 16-8-20.
 */
public class Footer extends LinearLayout{

    public Footer(Context context) {
        super(context);
        init(context);
    }

    public Footer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //----------------------Interfaces------------------------

    public String getText(int id) {
        if (id >= lbls.length) return null;
        return lbls[id].getText().toString();
    }

    public void setText(int id, String text) {
        if (id >= lbls.length) return;
        lbls[id].setText(text);
    }

    /**
     * 设置第id个
     * @param id
     * @param resId
     */
    public void setImage(int id, int resId) {
        if (id >= imgs.length) return;
        imgs[id].setImageResource(resId);
    }

    public void setOnClickListener(int id, OnClickListener listener) {
        items[id].setOnClickListener(listener);
    }

    public void setSelectedById(int id) {
        imgs[0].setImageResource(R.mipmap.products_unselected);
        imgs[1].setImageResource(R.mipmap.investment_unselected);
        imgs[2].setImageResource(R.mipmap.asset_unselected);
        imgs[3].setImageResource(R.mipmap.personal_unselected);
        switch (id) {
            case 0:
                imgs[0].setImageResource(R.mipmap.products_selected);
                break;
            case 1:
                imgs[1].setImageResource(R.mipmap.investment_selected);
                break;
            case 2:
                imgs[2].setImageResource(R.mipmap.asset_selected);
                break;
            case 3:
                imgs[3].setImageResource(R.mipmap.personal_selected);
                break;
            default:
        }
    }

    public void setSelectedByName(String name) {
        switch (name) {
            case "product":
                setSelectedById(0);
                return;
            case "investment":
                setSelectedById(1);
                return;
            case "asset":
                setSelectedById(2);
                return;
            case "personal":
                setSelectedById(3);
                return;
            default:
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadComponents();
    }

    private void loadComponents() {
        items = new LinearLayout[4];
        items[0] = (LinearLayout) findViewById(R.id.itmProduct);
        items[1] = (LinearLayout) findViewById(R.id.itmInvestment);
        items[2] = (LinearLayout) findViewById(R.id.itmAsset);
        items[3] = (LinearLayout) findViewById(R.id.itmPersonalInfo);

        imgs = new ImageView[4];
        imgs[0] = (ImageView) findViewById(R.id.imgProduct);
        imgs[1] = (ImageView) findViewById(R.id.imgInvestment);
        imgs[2] = (ImageView) findViewById(R.id.imgAsset);
        imgs[3] = (ImageView) findViewById(R.id.imgPersonalInfo);

        lbls = new TextView[4];
        lbls[0] = (TextView) findViewById(R.id.lblProduct);
        lbls[0].setText(getResources().getString(R.string.footer_lbl1));
        lbls[1] = (TextView) findViewById(R.id.lblInvestment);
        lbls[1].setText(getResources().getString(R.string.footer_lbl2));
        lbls[2] = (TextView) findViewById(R.id.lblAsset);
        lbls[2].setText(getResources().getString(R.string.footer_lbl3));
        lbls[3] = (TextView) findViewById(R.id.lblPersonalInfo);
        lbls[3].setText(getResources().getString(R.string.footer_lbl4));

    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.footer_bar, this, true);
        name2IdTable = new HashMap<>();
    }

    private Map<String, Integer> name2IdTable;
    private LinearLayout[] items;
    private ImageView[] imgs;
    private TextView[] lbls;

}
