package nju.financecity_android.controller.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.LineChartView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.Banner;
import nju.financecity_android.controller.widget.item.adapter.PropertyListAdapter;
import nju.financecity_android.model.ProductFund;
import nju.financecity_android.vo.GoodsInfo;
import nju.financecity_android.vo.PropertyVO;

import java.util.*;

public class FundDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_detail);
        initComponents();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                productId = intent.getStringExtra("productId");
                mData = (new ProductFund("40000177")).getProperties();
                mainThreadHander.post(new Runnable() {
                    @Override
                    public void run() {
                        processData(mData);
                    }
                });
            }
        }).start();
    }

    protected void setHeaderInfo(String productName, String interestRate, String netValue, String state) {
        txtInterestRate.setText(interestRate);
        txtPrdtName.setText(productName);
        txtNewestNetValue.setText(netValue);
        txtState.setText(state);
    }

    private void processData(Map data) {
        String productName = data.get("法定名称").toString();
        String interestRate = data.get("近一年收益率").toString();
        String netValue = data.get("最新净值").toString();
        String state = data.get("状态").toString();
        setHeaderInfo(productName, interestRate, netValue, state);

        List<PropertyVO> properties = new ArrayList<>();
        Collections.addAll(properties,
                new PropertyVO("基本信息", "", R.color.validBlue, 0),
                new PropertyVO("法定名称", productName),
                new PropertyVO("基金编号", data.get("基金编号")),
                new PropertyVO("管理机构", data.get("管理机构")),
                new PropertyVO("托管机构", data.get("托管机构")),
                new PropertyVO("", data.get("")),

                new PropertyVO("风险收益", "", R.color.validBlue, 0),
                new PropertyVO("近一年收益率", data.get("近一年收益率")),
                new PropertyVO("最新净值", data.get("最新净值")),
                new PropertyVO("基金投资目标类型", data.get("基金投资目标类型")),
                new PropertyVO("业绩比较基准", data.get("业绩比较基准")),
                new PropertyVO("跟踪标的", data.get("跟踪标的")),
                new PropertyVO("风险等级", data.get("风险等级")),
                new PropertyVO("", data.get("")),

                new PropertyVO("申购赎回", "", R.color.validBlue, 0),
                new PropertyVO("状态", data.get("状态")),
                new PropertyVO("是否封闭", data.get("是否封闭")),
                new PropertyVO("申购费率", data.get("申购费率")),
                new PropertyVO("认购费率", data.get("认购费率")),
                new PropertyVO("赎回费率", data.get("赎回费率")),
                new PropertyVO("广义管理费率", data.get("广义管理费率")),
                new PropertyVO("起购金额", data.get("起购金额")),
                new PropertyVO("递增购买最小单位", data.get("递增购买最小单位")),
                new PropertyVO("募集开始日", data.get("募集开始日")),
                new PropertyVO("募集截止日", data.get("募集截止日")),
                new PropertyVO("起息日", data.get("起息日")),
                new PropertyVO("开发日", data.get("开发日")),
                new PropertyVO("期限", data.get("期限")),
                new PropertyVO("认购速度", data.get("认购速度")),
                new PropertyVO("赎回速度", data.get("赎回速度")),
                new PropertyVO("", data.get("")),


                new PropertyVO("其他", "", R.color.validBlue, 0),
                new PropertyVO("基金经理", data.get("基金经理")),
                new PropertyVO("基金规模", data.get("基金规模")),
                new PropertyVO("份额规模", data.get("份额规模")));
        setProperties(properties);

        // charts
        List chartPoints = (List) data.get("历史净值");
        List<AxisValue> mAxisValues = new ArrayList<>();
        List<PointValue> mPointValues = new ArrayList<>();
        if (chartPoints == null) {
            chartPoints = new ArrayList();
        }

        for (int i = 0; i < chartPoints.size(); i++) {
            mPointValues.add(new PointValue(i, Float.valueOf(((Map) chartPoints.get(i)).get("NAV").toString())));
            mAxisValues.add(new AxisValue(i).setLabel(((Map) chartPoints.get(i)).get("date").toString().substring(5))); //为每个对应的i设置相应的label(显示在X轴)
        }
        Line line = new Line(mPointValues).setColor(COLOR_LIGHT_BLUE).setCubic(true);
        line.setPointRadius(3);
        line.setStrokeWidth(2);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        LineChartData lineChartData = new LineChartData();
        lineChartData.setLines(lines);


        Axis ax = new Axis();
        ax.setHasTiltedLabels(false);
        ax.setTextColor(COLOR_LIGHT_BLUE);
        ax.setValues(mAxisValues);
        ax.setHasTiltedLabels(true);
        ax.setMaxLabelChars(5);
        lineChartData.setAxisXBottom(ax);

        Axis ay = new Axis();
        ay.setMaxLabelChars(5);
        lineChartData.setLines(lines);
        lineChartData.setAxisYLeft(ay);

        linechart.setInteractive(true);
        linechart.setZoomType(ZoomType.HORIZONTAL);
        linechart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        linechart.setLineChartData(lineChartData);
        linechart.setVisibility(View.VISIBLE);
    }

    protected void setProperties(List<PropertyVO> properties) {
        PropertyListAdapter adapter = new PropertyListAdapter(this, properties);
        listProperties.setAdapter(adapter);
    }


    private void initComponents() {
        txtInterestRate = (TextView) findViewById(R.id.txtInterestRate);
        txtPrdtName = (TextView) findViewById(R.id.txtPdtName);
        txtNewestNetValue = (TextView) findViewById(R.id.txtGAge);
        txtState = (TextView) findViewById(R.id.txtLength);
        banner = (Banner) findViewById(R.id.banner);
        banner.setDisplayText("产品详情");
        listProperties = (ListView) findViewById(R.id.listProperties);
        linechart = (LineChartView) findViewById(R.id.linechart);
        btPurchase = (Button) findViewById(R.id.btPruchase);
        btPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfo info = new GoodsInfo();
                info.goodsId = productId;
                info.goodsName = mData.get("法定名称") + "";
                info.initialAmount = Integer.parseInt(mData.get("起购金额").toString().substring(1));
                info.amount = info.initialAmount;
                info.increasingUnit = info.price = Integer.parseInt(mData.get("递增购买最小单位").toString().substring(1));
                info.type = "银行理财";
                Intent intent = new Intent(FundDetailActivity.this, OrderConfirmActivity.class);
                intent.putExtra("0", info);
                startActivity(intent);
            }
        });
    }

    public static final int COLOR_LIGHT_BLUE = Color.argb(255, 17, 183, 243);


    private Button btPurchase;
    private Map mData;
    private String productId;
    private TextView txtInterestRate,
            txtPrdtName,
            txtNewestNetValue,
            txtState;
    private Banner banner;
    private ListView listProperties;
    private LineChartView linechart;
    private Handler mainThreadHander = new Handler();
}
