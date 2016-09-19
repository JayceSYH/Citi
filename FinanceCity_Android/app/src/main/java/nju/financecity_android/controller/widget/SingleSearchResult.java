package nju.financecity_android.controller.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.*;
import nju.financecity_android.vo.ProductInfo;
import nju.financecity_android.vo.ProductVO;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SingleSearchResult extends RelativeLayout {
    private RelativeLayout background;
    private TextView year_rate;
    private TextView line;
    private TextView type;
    private TextView product;
    private TextView introduction;
//    private TextView top;
//    private TextView middle;
//    private TextView bottom;
    private ProductVO info;
    private RelativeLayout mainPane;
    private Context mContext;

    public SingleSearchResult(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.product_search_result_element, this, true);
        onFinishInflate();
    }

    public SingleSearchResult(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.product_search_result_element, this, true);
        onFinishInflate();
    }

    public SingleSearchResult(Context context, ProductVO info) {
        super(context);
        this.info=info;
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.product_search_result_element, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        background=(RelativeLayout)findViewById(R.id.background);
        year_rate=(TextView)findViewById(R.id.year_rate);
        line=(TextView)findViewById(R.id.line);
        type=(TextView)findViewById(R.id.type);
        product=(TextView)findViewById(R.id.product);
        introduction=(TextView)findViewById(R.id.introduction);
//        top=(TextView)findViewById(R.id.top);
//        middle=(TextView)findViewById(R.id.middle);
//        bottom=(TextView)findViewById(R.id.bottom);
        mainPane = (RelativeLayout) findViewById(R.id.search_result_element_layout);

        int[] colors={R.color.bank,R.color.bond,R.color.fund,R.color.insurance};
        int[] backgrounds={R.drawable.bank_background,R.drawable.bond_background,R.drawable.fund_background,R.drawable.insurance_background};
        int choose=1;
        switch(info.getType())
        {
            case "bank":
                choose=1;
                break;
            case "bond":
                choose=2;
                break;
            case "fund":
                choose=3;
                break;
            case "insurance":
                choose=4;
                break;
        }
        background.setBackgroundResource(backgrounds[choose-1]);
        if (info.getType().equals("bank")) {
            type.setText("理");
        } else if (info.getType().equals("bond")) {
            type.setText("债");
        } else if (info.getType().equals("insurance")) {
            type.setText("险");
        } else if (info.getType().equals("fund")) {
            type.setText("基");
        }

        String strYear = info.getYear() + "";
        if (strYear.length() > 4) strYear = strYear.substring(0, 4);
        year_rate.setText(strYear+"%");
        product.setText(info.getName());
        introduction.setText(info.getIntroduction());
//        top.setText(info.getTop());
//        middle.setText(info.getMiddle());
//        bottom.setText(info.getBottom());
        mainPane.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getType().equals("bank")) {
                    Intent intent = new Intent(mContext, BankDetailActivity.class);
                    intent.putExtra("productId", info.getPid());
                    mContext.startActivity(intent);
                } else if (info.getType().equals("bond")) {
                    Intent intent = new Intent(mContext, BondDetailActivity.class);
                    intent.putExtra("productId", info.getPid());
                    mContext.startActivity(intent);
                } else if (info.getType().equals("insurance")) {
                    Intent intent = new Intent(mContext, InsuranceDetailActivity.class);
                    intent.putExtra("productId", info.getPid());
                    mContext.startActivity(intent);
                } else if (info.getType().equals("fund")) {
                    Intent intent = new Intent(mContext, FundDetailActivity.class);
                    intent.putExtra("productId", info.getPid());
                    mContext.startActivity(intent);
                }
            }
        });
    }
}
