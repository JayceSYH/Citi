package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import nju.financecity_android.controller.widget.Banner;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import nju.financecity_android.R;
import nju.financecity_android.dao.AssetHistoryDao;
import nju.financecity_android.dao.AssetValueDao;
import nju.financecity_android.model.UserAssets;
import nju.financecity_android.model.UserSession;
import nju.financecity_android.util.Loading;

/**
 * Created by Administrator on 2016/8/25.
 */
public class Assets extends Fragment {
    private int counter=0;
    public LineChartView chart;
    public ListView timeline;
    private LineChartData data = new LineChartData();
    private ArrayList<HashMap<String, Object>> listItem=new ArrayList<>();
    private SimpleAdapter mSimpleAdapter;
    private JSONArray jHistory=new JSONArray();
    private Loading loading=new Loading();
    Handler myHandler = new Handler() {
        //接收到消息后处理
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    counter++;
                    chart.setLineChartData(data);
                    if(counter%2==0)
                        loading.closeLoadingDialog();
                    Log.i("search","get message");
                    break;
                case 2:
                    counter++;
                    mSimpleAdapter = new SimpleAdapter(Assets.this.getActivity(),listItem,R.layout.assets_history,new String[]{"time", "history"},new int[] {R.id.time,R.id.history});
                    //需要绑定的数据//每一行的布局//动态数组中的数据源的键对应到定义布局的View中new String[] {"ItemImage"
                    timeline.setAdapter(mSimpleAdapter);//为ListView绑定适配器
                    timeline.setVisibility(View.VISIBLE);
                    if(counter%2==0)
                        loading.closeLoadingDialog();
                    Log.i("search","get message");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assets, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setChart();
        setTimeline();
    }

    public void setChart()
    {
        chart=(LineChartView)getView().findViewById(R.id.chart);

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                loading.showLoadingDialog(getActivity(),"loading",false);
                Looper.loop();
            }
        }).start();
//        try {
//            thread.join();//很奇怪，用join就会自动拉长x轴
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        //设置行为属性，支持缩放、滑动以及平移
        chart.setInteractive(true);
        chart.setZoomType(ZoomType.HORIZONTAL);
        chart.setScrollEnabled(true);
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        chart.setLineChartData(data);
        chart.setVisibility(View.VISIBLE);
        Viewport v = new Viewport(chart.getMaximumViewport());
        v.left = 0;
        v.right= 6;
        chart.setCurrentViewport(v);//设置当前的窗口显示多少个坐标数据，必须将折线的可以缩放的开关打开
    }

    private void setLineData()
    {

        List<PointValue> mPointValues=new ArrayList<PointValue>();
        List<AxisValue> mAxisValues=new ArrayList<AxisValue>();

        JSONArray jValue= UserAssets.getValue();
        Log.i("search","jValue "+jValue);

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
        /*==============================================================================*/
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

        data=new LineChartData(lines);/////
//        data.setLines(lines);

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

    public void setTimeline()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                jHistory=UserAssets.getHistory();
                Log.i("search","jHistory "+jHistory);
                try {
                    for (int i = 0; i < jHistory.length(); i++) {
                        JSONObject jDiscription=jHistory.getJSONObject(i);
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("time", jDiscription.getString("date"));
                        String tradingType="";
                        switch(jDiscription.getString("tradingType"))
                        {
                            case "buy":
                                tradingType="购买";
                                break;
                        }//TODO
                        map.put("history", tradingType+jDiscription.getString("productName")+jDiscription.getString("tradingVolume")+jDiscription.getString("unit"));
                        listItem.add(map);
                    }
                } catch(Exception e)
                {
                    Log.i("test","set timeline");
                    e.printStackTrace();
                }

                Message message = new Message();
                message.what = 2;
                //发送消息
                myHandler.sendMessage(message);
                Log.i("search","send message");
            }
        });
        thread.start();

        //资产变化事迹
        timeline = (ListView) getView().findViewById(R.id.timeline);
        listItem = new ArrayList<HashMap<String,Object>>();/*在数组中存放数据*/

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(),listItem,R.layout.assets_history,new String[]{"time", "history"},new int[] {R.id.time,R.id.history});
        //需要绑定的数据//每一行的布局//动态数组中的数据源的键对应到定义布局的View中new String[] {"ItemImage"
        timeline.setAdapter(mSimpleAdapter);//为ListView绑定适配器
        timeline.setVisibility(View.VISIBLE);
    }
}
