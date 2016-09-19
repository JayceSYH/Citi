package nju.financecity_android.controller.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.Banner;
import nju.financecity_android.controller.widget.item.adapter.PropertyListAdapter;
import nju.financecity_android.model.ProductBond;
import nju.financecity_android.vo.GoodsInfo;
import nju.financecity_android.vo.PropertyVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BondDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bond_detail);
        initComponents();

        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        new Thread(new Runnable() {
            @Override
            public void run() {
                mData = (new ProductBond(productId)).getProperties();
                mainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        processData(mData);
                    }
                });
            }
        });
    }

    protected void setHeaderInfo(String productName, String state, String length, String interestRate) {
        txtPrdtName.setText(productName);
        txtState.setText(state);
        txtLength.setText(length);
        txtInterestRate.setText(interestRate);
    }

    protected void processData(Map data) {
        String productName = data.get("债券名称").toString();
        String state = data.get("状态").toString();
        String interestRate = data.get("票面利率").toString();
        String length = data.get("期限").toString();
        setHeaderInfo(productName, state, length, interestRate);

        List<PropertyVO> properties = new ArrayList<>();
        if (data.get("债券分类").toString().equals("储蓄式")) {
            Collections.addAll(properties,
                    new PropertyVO("基本信息", "", R.color.validBlue, 0),
                    new PropertyVO("债券名称", productName),
                    new PropertyVO("债券简称", data.get("债券简称")),
                    new PropertyVO("债券代码", data.get("债券代码")),
                    new PropertyVO("债券分类", data.get("债券分类")),
                    new PropertyVO("发型单位", data.get("发型单位")),
                    new PropertyVO("", ""),

                    new PropertyVO("风险收益", "", R.color.validBlue, 0),
                    new PropertyVO("票面利率", data.get("票面利率")),
                    new PropertyVO("调整后年利率", data.get("调整后年利率")),
                    new PropertyVO("计息、付息方式", data.get("计息、付息方式")),
                    new PropertyVO("付息频率", data.get("付息频率")),
                    new PropertyVO("", ""),

                    new PropertyVO("申购赎回", "", R.color.validBlue, 0),
                    new PropertyVO("期限", data.get("期限")),
                    new PropertyVO("发行起始日", data.get("发行起始日")),
                    new PropertyVO("发行截止日", data.get("发行截止日")),
                    new PropertyVO("状态", data.get("状态")),
                    new PropertyVO("到期日", data.get("到期日")),
                    new PropertyVO("起息日", data.get("起息日")),
                    new PropertyVO("提前兑取开始日期", data.get("提前兑取开始日期")),
                    new PropertyVO("提前兑取计息开始日期", data.get("提前兑取计息开始日期")),
                    new PropertyVO("", ""),

                    new PropertyVO("其他", "", R.color.validBlue, 0),
                    new PropertyVO("面值", data.get("面值")),
                    new PropertyVO("发行价", data.get("发行价")));
            setProperties(properties);
        }
        else {
            Collections.addAll(properties,
                    new PropertyVO("基本信息", "", R.color.validBlue, 0),
                    new PropertyVO("债券名称", productName),
                    new PropertyVO("债券简称", data.get("债券简称")),
                    new PropertyVO("债券代码", data.get("债券代码")),
                    new PropertyVO("债券分类", data.get("债券分类")),
                    new PropertyVO("发型单位", data.get("发型单位")),
                    new PropertyVO("", ""),

                    new PropertyVO("风险收益", "", R.color.validBlue, 0),
                    new PropertyVO("票面利率", data.get("票面利率")),
                    new PropertyVO("调整后年利率", data.get("调整后年利率")),
                    new PropertyVO("计息、付息方式", data.get("计息、付息方式")),
                    new PropertyVO("付息频率", data.get("付息频率")),
                    new PropertyVO("", ""),

                    new PropertyVO("申购赎回", "", R.color.validBlue, 0),
                    new PropertyVO("期限", data.get("期限")),
                    new PropertyVO("发行起始日", data.get("发行起始日")),
                    new PropertyVO("发行截止日", data.get("发行截止日")),
                    new PropertyVO("状态", data.get("状态")),
                    new PropertyVO("到期日", data.get("到期日")),
                    new PropertyVO("起息日", data.get("起息日")),
                    new PropertyVO("", ""),

                    new PropertyVO("其他", "", R.color.validBlue, 0),
                    new PropertyVO("面值", data.get("面值")),
                    new PropertyVO("发行价", data.get("发行价")),
                    new PropertyVO("上市地", data.get("上市地")),
                    new PropertyVO("发布时间", data.get("发布时间")),
                    new PropertyVO("上市流通日", data.get("上市流通日")),
                    new PropertyVO("发行额", data.get("发行额")),
                    new PropertyVO("兑讨价", data.get("兑讨价")),
                    new PropertyVO("认购对象", data.get("认购对象")),
                    new PropertyVO("债券价值", data.get("债券价值")),
                    new PropertyVO("信用级别", data.get("信用级别")),
                    new PropertyVO("发行方式", data.get("发行方式")),
                    new PropertyVO("发行对象", data.get("发行对象")),
                    new PropertyVO("主承销机构", data.get("主承销机构")),
                    new PropertyVO("税收状况", data.get("税收状况")));
            setProperties(properties);
        }
    }

    protected void setProperties(List<PropertyVO> properties) {
        PropertyListAdapter adapter = new PropertyListAdapter(this, properties);
        listProperties.setAdapter(adapter);
    }

    private void initComponents() {
        mInflater = LayoutInflater.from(this);

        listProperties = (ListView) findViewById(R.id.listProperties);
        txtInterestRate = (TextView) findViewById(R.id.txtInterestRate);
        txtPrdtName = (TextView) findViewById(R.id.txtPdtName);
        txtState = (TextView) findViewById(R.id.txtState);
        txtLength = (TextView) findViewById(R.id.txtLength);
        banner = (Banner) findViewById(R.id.banner);
        banner.setDisplayText("产品详情");
        btPurchase = (Button) findViewById(R.id.btPruchase);
        btPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInfo info = new GoodsInfo();
                info.goodsId = productId;
                info.goodsName = mData.get("债券名称").toString();
                info.price = Integer.parseInt(mData.get("发行价").toString()) * 10;
                info.amount = 1;
                info.type = "债券";
                Intent intent = new Intent(BondDetailActivity.this, OrderConfirmActivity.class);
                intent.putExtra("0", info);
                startActivity(intent);
                BondDetailActivity.this.finish();
            }
        });
    }

    private Handler mainThreadHandler;
    private Map mData;
    private Button btPurchase;
    private String productId;
    private Banner banner;
    private ListView listProperties;
    private TextView txtInterestRate, txtPrdtName, txtState, txtLength;
    private LayoutInflater mInflater;
}
