package nju.financecity_android.controller.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.item.adapter.ListGoodsAdapter;
import nju.financecity_android.vo.GoodsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 *
 */
public class OrderConfirmActivity extends AppCompatActivity implements Observer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        initComponents();
        processData();
    }

    protected void processData() {
        goodsList = new ArrayList<>();
        Intent intent = getIntent();
        Bundle dataSet = intent.getExtras();
        for (String key: dataSet.keySet()) {
            GoodsInfo goodsInfo = (GoodsInfo) dataSet.getSerializable(key);
            goodsList.add(goodsInfo);
        }
        Log.i("order","goodsList "+goodsList.toString());
        setListContent(goodsList);
    }

    protected void setListContent(List<GoodsInfo> content) {
        ListGoodsAdapter adapter = new ListGoodsAdapter(this, content);
        listGoods.setAdapter(adapter);
        update(null, null);
    }

    private void initComponents() {
        listGoods = (ListView) findViewById(R.id.listGoods);
        btCancel = (Button) findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderConfirmActivity.this.finish();
            }
        });
        btSubmit = (Button) findViewById(R.id.btSubmit);
        txtSummary = (TextView) findViewById(R.id.txtSummary);
    }

    private List<GoodsInfo> goodsList;
    private ListView listGoods;
    private Button btCancel, btSubmit;
    private TextView txtSummary;

    @Override
    public void update(Observable observable, Object data) {
        int sum = 0;
        for (GoodsInfo info: goodsList) {
            sum += info.getSum();
        }
        txtSummary.setText(String.format("%d", sum));
    }
}
