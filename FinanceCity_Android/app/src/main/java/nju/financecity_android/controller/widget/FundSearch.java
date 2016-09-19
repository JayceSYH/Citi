package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.ProductSearch;
import nju.financecity_android.model.SearchAgent;

/**
 * Created by Administrator on 2016/9/6.
 */
public class FundSearch extends RelativeLayout {
    private Spinner agent;
    private Spinner state;
    private Bar latest;
//    private EditText sort;
    private Spinner sort_type;
    private Switch close;

    private String[] agent_content;
    private String[] state_content;
    private String[] sort_type_content;

    private int agent_item;
    private int state_item;
    private int sort_type_item;

    private boolean close_checked=false;


    public FundSearch(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.fund_search, this, true);
        onFinishInflate();
    }

    public FundSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.fund_search, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        agent=(Spinner)findViewById(R.id.agent_spinner);
        state=(Spinner)findViewById(R.id.state_spinner);
        latest=(Bar)findViewById(R.id.latest);
//        sort=(EditText)findViewById(R.id.sort_text);
        close=(Switch)findViewById(R.id.close_switch);
        sort_type=(Spinner)findViewById(R.id.sort_type);


        agent_content=new String[]{"所有","agent1","agent2","agnet3"};//TODO 从API获取
        Thread agentThread=new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> fundAgent=new SearchAgent().getAgent("Fund");
                fundAgent.add(0,"所有");
                try {
                    if(fundAgent.size()!=0)
                    agent_content=fundAgent.toArray(new String[fundAgent.size()]);
                }catch(Exception e)
                {
                    Log.i("test","fund agent exception");
                }
                agent.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,agent_content));
            }
        });
        agentThread.start();
        agent.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,agent_content));
        state_content=getResources().getStringArray(R.array.fund_state);
        state.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,state_content));
        sort_type_content=getResources().getStringArray(R.array.sort_type);
        sort_type.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,sort_type_content));

        agent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                agent_item=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                state_item=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sort_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sort_type_item=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                close_checked=!close_checked;
            }
        });
    }

    public String getAgent()
    {
        return agent_content[agent_item];
    }
    public String getState()
    {
        return state_content[state_item];
    }
    public Float getLatestMin()
    {
        return latest.getMin();
    }
    public Float getLatestMax()
    {
        return latest.getMax();
    }
//    public int getSortYear()
//    {
//        if(sort.getText().toString().equals(""))
//            return -1;
//        return Integer.parseInt(sort.getText().toString());
//    }
    public boolean getCloseChecked()
    {
        return close_checked;
    }
    public int getSort_type_item(){return sort_type_item;}
    public String getSort_type(){return sort_type_content[sort_type_item];}

    public void setLatest(float start,float end) {
        latest.setStart(start);
        latest.setEnd(end);
    }

    public void setAgent(int pos)
    {
        agent.setSelection(pos);
        agent_item=pos;
    }
    public void setState(int pos)
    {
        state_item=pos;
        state.setSelection(pos);
    }
    public void setClose_checked(boolean close_checked) {
        this.close_checked=close_checked;
        this.close.setChecked(close_checked);
    }
//    public void setSort(int year) {
//        sort.setText(year+"");
//    }
    public void setSort_type(int pos)
    {
        sort_type.setSelection(pos);
    }

    public void setInit()
    {
        setLatest(0,10);
        setState(0);
        setClose_checked(false);
        setAgent(0);
//        sort.setText("");
        setSort_type(0);
    }
}
