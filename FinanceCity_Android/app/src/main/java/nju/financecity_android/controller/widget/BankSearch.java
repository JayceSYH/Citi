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

import java.util.ArrayList;
import java.util.List;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.ProductSearch;
import nju.financecity_android.model.SearchAgent;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BankSearch extends RelativeLayout {
    private Bar year;
    private Bar start;
    private Bar limit;
    private Spinner agent;
    private Spinner incometype;
    private Switch close;

    private String[] agent_content;
    private String[] incometype_content;

    private int agent_item=0;
    private int incometype_item=0;
    private boolean close_checked=false;

    public BankSearch(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.bank_search, this, true);
        onFinishInflate();
    }

    public BankSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bank_search, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        year=(Bar)findViewById(R.id.year);
        start=(Bar)findViewById(R.id.start);
        limit=(Bar)findViewById(R.id.limit);
        agent=(Spinner)findViewById(R.id.agent_spinner);
        incometype=(Spinner)findViewById(R.id.incometype_spinner);
        close=(Switch)findViewById(R.id.close_switch);

        agent_content=new String[]{"所有","bank1","bank2","bank3"};//TODO 从API获得
        Thread agentThread=new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> bankAgent=new SearchAgent().getAgent("Bank");
                bankAgent.add(0,"所有");
                try {
                    if(bankAgent.size()!=0)
                        agent_content=bankAgent.toArray(new String[bankAgent.size()]);
                }catch(Exception e)
                {
                    Log.i("test","bank agent exception");
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
        incometype_content=getResources().getStringArray(R.array.bank_incometype);
        incometype.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,incometype_content));
        incometype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                incometype_item=i;
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
    public Float getYearMin()
    {
        return year.getMin();
    }
    public Float getYearMax()
    {
        return year.getMax();
    }
    public Float getStartMin()
    {
        return start.getMin();
    }
    public Float getStartMax()
    {
        return start.getMax();
    }
    public Float getLimitMin()
    {
        return limit.getMin();
    }
    public Float getLimitMax()
    {
        return limit.getMax();
    }
    public boolean getCloseChecked()
    {
        return close_checked;
    }
    public String getAgent()
    {
        return agent_content[agent_item];
    }
    public String getIncometype()
    {
        return incometype_content[incometype_item];
    }

    public void setYear(float start,float end) {
        year.setStart(start);
        year.setEnd(end);
    }
    public void setLimit(float start,float end) {
        limit.setStart(start);
        limit.setEnd(end);
    }
    public void setStart(float start,float end) {
        this.start.setStart(start);
        this.start.setEnd(end);
    }
    public void setClose_checked(boolean close_checked) {
        this.close_checked=close_checked;
        this.close.setChecked(close_checked);
    }
    public void setAgent(int pos)
    {
        agent.setSelection(pos);
        agent_item=pos;
    }
    public void setIncometype(int pos)
    {
        incometype_item=pos;
        incometype.setSelection(pos);
    }
    public void setInit()
    {
        setYear(0,15);
        setLimit(0,1800);
        setStart(0,1000000);
        setClose_checked(false);
        setAgent(0);
        setIncometype(0);
    }
}
