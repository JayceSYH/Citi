package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.ProductSearch;
import nju.financecity_android.model.SearchAgent;

/**
 * Created by Administrator on 2016/9/6.
 */
public class InsuranceSearch extends RelativeLayout{
    private Bar year;
    private Spinner limit;
    private Spinner agent;
    private Bar value;

    private String[] limit_content;
    private String[] agent_content;

    private int limit_item=0;
    private int agent_item=0;

    public InsuranceSearch(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.insurance_search, this, true);
        onFinishInflate();
    }

    public InsuranceSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.insurance_search, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        year=(Bar)findViewById(R.id.year);
        limit=(Spinner)findViewById(R.id.limit_spinner);
        agent=(Spinner)findViewById(R.id.agent_spinner);
        value=(Bar)findViewById(R.id.value);

        limit_content=getResources().getStringArray(R.array.insurance_year);
        limit.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,limit_content));
        limit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                limit_item=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        agent_content=new String[]{"所有","bank1","bank2","bank3"};//TODO 从API获得
        Thread agentThread=new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> insuranceAgent=new SearchAgent().getAgent("Insurance");
                insuranceAgent.add(0,"所有");
                try {
                    if(insuranceAgent.size()!=0)
                    agent_content=insuranceAgent.toArray(new String[insuranceAgent.size()]);
                }catch(Exception e)
                {
                    Log.i("test","insurance agent exception");
                }
                agent.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,agent_content));
            }
        });
        agentThread.start();
        agent.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,agent_content));
        agent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                agent_item=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public Float getYearMin()
    {
        return year.getMin();
    }
    public Float getYearMax()
    {
        return year.getMax();
    }
    public String getLimit()
    {
        return limit_content[limit_item];
    }
    public String getAgent()
    {
        return agent_content[agent_item];
    }
    public Float getValueMin()
    {
        return year.getMin();
    }
    public Float getValueMax()
    {
        return year.getMax();
    }

    public void setYear(float start,float end) {
        year.setStart(start);
        year.setEnd(end);
    }
    public void setLimit(int pos) {
        limit.setSelection(pos);
    }
    public void setAgent(int pos)
    {
        agent.setSelection(pos);
        agent_item=pos;
    }
    public void setValue(float start,float end) {
        value.setStart(start);
        value.setEnd(end);
    }
    public void setInit()
    {
        setYear(0,10);
        setLimit(0);
        setAgent(0);
        setValue(0,50000);
    }
}
