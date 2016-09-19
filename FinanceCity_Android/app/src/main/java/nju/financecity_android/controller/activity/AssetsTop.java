package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.item.adapter.InvstPrdtAdapter;
import nju.financecity_android.dao.AssetValueDao;
import nju.financecity_android.dao.InvestmentDao;
import nju.financecity_android.model.UserAssets;
import nju.financecity_android.model.UserInvestment;
import nju.financecity_android.model.UserSession;
import nju.financecity_android.util.ColorBoard;
import nju.financecity_android.util.Loading;
import nju.financecity_android.vo.ProductInfo;

/**
 * Created by Administrator on 2016/9/12.
 */
public class AssetsTop extends Fragment
{
    private LinearLayout assets_top_layout;
    private PieChartView pieChart;
    private LineChartView lineChart;
    private LineChartData data = new LineChartData();
    private PieChartData piedata = new PieChartData();
    private Loading loading=new Loading();
    private int counter=0;
    Handler myHandler = new Handler() {
        //接收到消息后处理
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    counter++;
                    lineChart.setLineChartData(data);
                    if(counter%2==0)
                        loading.closeLoadingDialog();
                    Log.i("search","get message");
                    break;
                case 2:
                    counter++;
                    pieChart.setPieChartData(piedata);
                    if(counter%2==0)
                        loading.closeLoadingDialog();
                    Log.i("search","get message2");
                    break;
                case 3:
                    Log.i("search","get message3");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assets_top, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                loading.showLoadingDialog(getActivity(),"loading",false);
                Looper.loop();
            }
        }).start();
        setPieChart();
        setLineChart();
    }

    private void setPieChart()
    {
        pieChart=(PieChartView)getView().findViewById(R.id.pie);

        setPieData();

        List<SliceValue> values = new ArrayList<>();

        List<ProductInfo> data = new ArrayList<>();
        ProductInfo info = new ProductInfo();
        info = new ProductInfo();
        info.productName = "";
        info.buyPrice = 0;
        info.buy = "";
        info.currPrice = 0;
        data.add(info);
        for (int i = 0; i < data.size(); ++i) {
            SliceValue slice = new SliceValue(data.get(i).currPrice);
            slice.setLabel(data.get(i).productName);
            slice.setColor(ColorBoard.nextColor());
            values.add(slice);
        }

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

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test","click pie button");
                ((MainActivity) getActivity()).setDisplayPage(MainActivity.PAGE_INVESTMENT);
            }
        });
    }

    private void setLineChart()
    {
        lineChart=(LineChartView)getView().findViewById(R.id.line);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setLineData();

                Message message = new Message();
                message.what = 1;
                //发送消息
                myHandler.sendMessage(message);
                Log.i("search","send message");

            }
        });
        thread.start();

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setScrollEnabled(true);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 6;
        lineChart.setCurrentViewport(v);
        lineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test","click line button");
                ((MainActivity) getActivity()).setDisplayPage(MainActivity.PAGE_ASSET);
            }
        });
    }

    private void setPieData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ProductInfo> list = UserInvestment.getCurrUserInvestment().mData;

                // 饼图
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
                piedata.setValues(values);

                Message message = new Message();
                message.what = 2;
                //发送消息
                myHandler.sendMessage(message);
                Log.i("search","send message2");
            }
        }).start();
    }

    private void setLineData()
    {
        List<PointValue> mPointValues=new ArrayList<PointValue>();
        List<AxisValue> mAxisValues=new ArrayList<AxisValue>();

        final JSONObject[] jsonObjects=new JSONObject[1];
        jsonObjects[0]=new JSONObject();//value
//        UserSession user=UserSession.getCurrUser();
//        Log.i("test","user "+user.getSessionId());
        try {
//            jsonObjects[0].put("id", Integer.parseInt(user.getUserId()));//TODO 传递当前用户
//            jsonObjects[0].put("sessionId",user.getSessionId());
            jsonObjects[0].put("id", 6);
            jsonObjects[0].put("sessionId","745acb2a229043089cf1d04114847705");
//            jsonObjects[0].put("days",20);//TODO
        }catch(Exception e)
        {
            Log.i("test","user session or json exception");
            e.printStackTrace();
        }
        Log.i("test","jObject of parameters "+jsonObjects[0]);

        final String[] result={""};
        result[0]=new AssetValueDao().sendPost(jsonObjects[0]);
        JSONArray jValue=null;
        try{
            jValue=new JSONObject(result[0]).getJSONArray("assetValues");
        }catch(Exception e)
        {
            Log.i("test","asset value result exception");
            e.printStackTrace();
        }

        List<String> dates=new ArrayList<String>();
        try {
            for (int i = 0; i < jValue.length(); i++) {
                dates.add(jValue.getJSONObject(i).getString("date"));
            }
        }catch(Exception e)
        {
            Log.i("test","analyse jsonArray");
            e.printStackTrace();
        }
        try{
//            for (int i = 0; i < dates.size() ; i++) {
            for (int i = 0; i < 20 ; i++) {//TODO
                mPointValues.add(new PointValue(i,Float.parseFloat(jValue.getJSONObject(0).get("value")+"")-new Random().nextInt(10)*10000+50000));
//                test.add(new PointValue(i,new Random().nextInt(10)-5));////////////////////////////////////////////
                mAxisValues.add(new AxisValue(i).setLabel(dates.get(0))); //为每个对应的i设置相应的label(显示在X轴)
            }

        }catch(Exception e)
        {
            Log.i("test","analyse jsonArray");
            e.printStackTrace();
        }
        Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.lightBlue)).setCubic(false);
        line.setHasLabelsOnlyForSelected(true);
        line.setPointColor(getResources().getColor(R.color.validBlue));
        line.setPointRadius(4);//座标点大小
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLUE);
        axisX.setName("时间");
        axisX.setMaxLabelChars(14);
        axisX.setValues(mAxisValues);
        axisX.setHasSeparationLine(false);
        axisX.setHasLines(true);
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis();  //Y轴
        axisY.setMaxLabelChars(7); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);

    }
}
