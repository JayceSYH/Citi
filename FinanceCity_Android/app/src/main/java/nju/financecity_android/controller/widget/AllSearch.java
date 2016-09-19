package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import nju.financecity_android.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class AllSearch extends RelativeLayout {
    private Bar year;
    private Bar limit;
    private Switch close;

    private boolean close_checked=false;

    public AllSearch(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.all_search, this, true);
        onFinishInflate();
    }

    public AllSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.all_search, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        year=(Bar)findViewById(R.id.year);
        limit=(Bar)findViewById(R.id.limit);
        close=(Switch)findViewById(R.id.close_switch);
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

    public void setYear(float start,float end) {
        year.setStart(start);
        year.setEnd(end);
    }
    public void setLimit(float start,float end) {
        limit.setStart(start);
        limit.setEnd(end);
    }
    public void setClose_checked(boolean close_checked) {
        this.close_checked=close_checked;
        this.close.setChecked(close_checked);
    }
    public void setInit()
    {
        setYear(0,15);
        setLimit(0,1800);
        setClose_checked(false);
    }
}
