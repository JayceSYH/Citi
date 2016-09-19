package nju.financecity_android.controller.widget.item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import nju.financecity_android.R;
import nju.financecity_android.controller.activity.BankDetailActivity;
import nju.financecity_android.controller.activity.BondDetailActivity;
import nju.financecity_android.controller.activity.FundDetailActivity;
import nju.financecity_android.controller.activity.InsuranceDetailActivity;
import nju.financecity_android.util.ColorBoard;
import nju.financecity_android.vo.GoodsInfo;
import nju.financecity_android.vo.RecommendSingleVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coral on 16-9-15.
 */
public class RecommendItem {

    public RecommendItem(Context context, View convertView, RecommendSingleVO data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = (convertView == null) ? mInflater.inflate(R.layout.recommend_item, null) : convertView;
        this.mData = data;
        this.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //生成一个Intent对象
                Intent intent = new Intent();
                //在Intent对象当中添加一个键值对
                intent.putExtra("productId", mData.id);
                //设置Intent对象要启动的Activity
                switch(mData.productType)
                {
                    case "Bank":
                        intent.setClass(mContext,BankDetailActivity.class);
                        break;
                    case "Fund":
                        intent.setClass(mContext,FundDetailActivity.class);
                        break;
                    case "Bond":
                        intent.setClass(mContext,BondDetailActivity.class);
                        break;
                    case "Insurance":
                        intent.setClass(mContext,InsuranceDetailActivity.class);
                        break;
                }
                //通过Intent对象启动另外一个Activity
                mContext.startActivity(intent);
            }
        });
        initComponents();
    }

    public View getView() {
        return mView;
    }

    public View findViewById(int resId) {
        return mView.findViewById(resId);
    }

    private void initComponents() {
        txtSimpleType = (TextView) findViewById(R.id.txtSimpleType);
        if (mData.productType.equals("Bank")) {
            txtSimpleType.setText("理");
            txtSimpleType.setBackgroundResource(R.drawable.bank_background);
        } else if (mData.productType.equals("Fund")) {
            txtSimpleType.setText("基");
            txtSimpleType.setBackgroundResource(R.drawable.fund_background);
        } else if (mData.productType.equals("Bond")) {
            txtSimpleType.setText("债");
            txtSimpleType.setBackgroundResource(R.drawable.bond_background);
        } else if (mData.productType.equals("Insurance")) {
            txtSimpleType.setText("保");
            txtSimpleType.setBackgroundResource(R.drawable.insurance_background);
        }

        txtProductName = (TextView) findViewById(R.id.txtProductName);
        txtProductName.setText(mData.name + "");

        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtAmount.setText("￥" + mData.amount);

        piechart = (PieChartView) findViewById(R.id.piechart);
    }

    /**
     * 显示饼图数据
     * @param amount 当前产品的购买总额
//     * @param sum 当前投资组合总额
     */
    public void setChartInfo(float amount, float percentage) {
        List<SliceValue> values = new ArrayList<>();
        values.add(new SliceValue(amount).setColor(getView().getResources().getColor(R.color.lightBlue)));
        values.add(new SliceValue(amount/percentage-amount).setColor(getView().getResources().getColor(R.color.lightGrey)));
        PieChartData data = new PieChartData(values);
        data.setHasLabels(false);
        data.setHasLabelsOutside(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasCenterCircle(true);
        data.setCenterCircleScale(0.8f);
        String strPercent = percentage * 100 + "";
        if (strPercent.length() > 3) strPercent = strPercent.substring(0, 3) + "%";
        data.setCenterText1(strPercent);
        data.setCenterText1FontSize(14);
        data.setCenterText1Color(Color.parseColor("#7a7a7a"));
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);
        data.setValueLabelBackgroundEnabled(false);
        data.setValueLabelsTextColor(Color.GRAY);
        data.setSlicesSpacing(1);
        piechart.setPieChartData(data);
    }

    private LayoutInflater mInflater;
    private Context mContext;
    private View mView;
    private RecommendSingleVO mData;
    private TextView txtSimpleType, txtProductName, txtAmount;
    private PieChartView piechart;
}
