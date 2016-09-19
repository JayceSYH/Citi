package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.item.adapter.InvstPrdtAdapter;
import nju.financecity_android.dao.InvestmentDao;
import nju.financecity_android.model.PurchaseBasket;
import nju.financecity_android.model.User;
import nju.financecity_android.model.UserInvestment;
import nju.financecity_android.util.ColorBoard;
import nju.financecity_android.util.Loading;
import nju.financecity_android.vo.ProductInfo;

import java.util.*;

/**
 * Created by coral on 16-8-26.
 */
public class Investment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_investment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        thisView = getView();
        initComponents();
        processData();
    }

    public static PieChartView setPieChart(PieChartView chart) {
        return chart;
    }

    private void initComponents() {
        // 初始化投资产品列表
        listInvstProducts = (ListView) findViewById(R.id.listInvstProducts);
        pieChart = (PieChartView) findViewById(R.id.pieChart);
    }

    private void processData() {
        setData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                loading.showLoadingDialog(getActivity(),"loading",false);
                Looper.loop();
            }
        }).start();
        setPieChart();
        setTable();
    }


    private void setPieChart()
    {
        List<SliceValue> values=getPieValue();

        piedata.setValues(values);
        piedata.setHasLabels(true);
        piedata.setHasLabelsOutside(true);
        piedata.setHasLabelsOnlyForSelected(false);
        piedata.setHasCenterCircle(true);
        piedata.setCenterCircleScale(0.5f);
        piedata.setValueLabelBackgroundColor(Color.TRANSPARENT);
        piedata.setValueLabelBackgroundEnabled(false);
        piedata.setValueLabelsTextColor(Color.GRAY);
        piedata.setSlicesSpacing(1);
        pieChart.setPieChartData(piedata);
    }

    private void setTable()
    {
        ipAdapter=new InvstPrdtAdapter(this.getActivity(),list);
        listInvstProducts.setAdapter(ipAdapter);
        listInvstProducts.setVisibility(View.VISIBLE);
    }

    private void setData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = UserInvestment.getCurrUserInvestment().mData;

                // 饼图
                List<SliceValue> values = getPieValue();
                piedata.setValues(values);

                //表格
                ipAdapter=new InvstPrdtAdapter(Investment.this.getActivity(),list);

                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        pieChart.setPieChartData(piedata);
                        listInvstProducts.setAdapter(ipAdapter);
                        loading.closeLoadingDialog();
                    }
                });
            }
        }).start();
    }

    private List<SliceValue> getPieValue()
    {
        List<SliceValue> values = new ArrayList<>();
        values.add(new SliceValue(0));//bank
        values.get(0).setLabel("");
        values.get(0).setColor(ColorBoard.nextColor());
        values.add(new SliceValue(0));//bond
        values.get(1).setLabel("");
        values.get(1).setColor(ColorBoard.nextColor());
        values.add(new SliceValue(0));//fund
        values.get(2).setLabel("");
        values.get(2).setColor(ColorBoard.nextColor());
        values.add(new SliceValue(0));//insurance
        values.get(3).setLabel("");
        values.get(3).setColor(ColorBoard.nextColor());
        for (int i = 0; i < list.size(); ++i) {
            ProductInfo info=list.get(i);
            if(info.type.contains("理财")) {
                values.get(0).setValue(values.get(0).getValue() + info.currPrice);
                values.get(0).setLabel("理财产品");
            }
            else if(info.type.contains("债券")) {
                values.get(1).setValue(values.get(1).getValue() + info.currPrice);
                values.get(1).setLabel("债券");
            }
            else if(info.type.contains("基金")) {
                values.get(2).setValue(values.get(2).getValue() + info.currPrice);
                values.get(2).setLabel("基金");
            }
            else if(info.type.contains("保险")) {
                values.get(3).setValue(values.get(3).getValue() + info.currPrice);
                values.get(3).setLabel("保险");
            }
        }
        return values;
    }

    private View findViewById(int resId) {
        return thisView.findViewById(resId);
    }
    private Handler mainThreadHandler = new Handler();
    private View thisView;
    private PieChartView pieChart;
    private PieChartData piedata=new PieChartData();
    private ListView listInvstProducts;
    private List<ProductInfo> list=new ArrayList<ProductInfo>();
    private InvstPrdtAdapter ipAdapter;
    private Loading loading=new Loading();
    private Handler myHandler=new Handler();
}
