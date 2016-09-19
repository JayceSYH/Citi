package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.List;

import nju.financecity_android.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class BondSearch extends RelativeLayout {
    private Bar year;
    private Bar limit;
    private Spinner state;
    private Spinner ddl;

    private String[] ddl_content;
    private String[] state_content;

    private int ddl_item=0;
    private int state_item=0;

    private int ddl_lenth=10;
    public BondSearch(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.bond_search, this, true);
        onFinishInflate();
    }

    public BondSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bond_search, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        year=(Bar)findViewById(R.id.year);
        limit=(Bar)findViewById(R.id.limit);
        state=(Spinner)findViewById(R.id.state_spinner);
        ddl=(Spinner)findViewById(R.id.ddl_spinner);

        Calendar today= Calendar.getInstance();
        int today_year=today.get(Calendar.YEAR);
        ddl_content=new String[ddl_lenth];
        for(int i=0;i<ddl_lenth;i++)
        {
            ddl_content[i]=today_year+i+"";
        }
        ddl.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,ddl_content));
        state_content=getResources().getStringArray(R.array.bond_state);
        state.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_element,state_content));

        ddl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ddl_item=i;
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
    }
    public Float getYearMin()
    {
        return year.getMin();
    }
    public Float getYearMax()
    {
        return year.getMax();
    }
    public Float getLimitMin()
    {
        return limit.getMin();
    }
    public Float getLimitMax()
    {
        return limit.getMax();
    }
    public int getDdl()
    {
        return Integer.parseInt(ddl_content[ddl_item]);
    }
    public String getState()
    {
        return state_content[state_item];
    }

    public void setYear(float start,float end) {
        year.setStart(start);
        year.setEnd(end);
    }
    public void setLimit(float start,float end) {
        limit.setStart(start);
        limit.setEnd(end);
    }
    public void setState(int pos)
    {
        state.setSelection(pos);
        state_item=pos;
    }
    public void setDDL(int pos)
    {
        ddl_item=pos;
        ddl.setSelection(pos);
    }
    public void setInit()
    {
        setYear(0,10);
        setLimit(0,1800);
        setState(0);
        setDDL(0);
    }
}
