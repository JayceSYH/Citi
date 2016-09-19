package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import nju.financecity_android.R;

public class Range extends LinearLayout{
    private TextView range_text;
    private EditText range_putin1;
    private EditText range_putin2;

    public Range(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.range, this, true);
        onFinishInflate();
    }

    public Range(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.range, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        range_text=(TextView)findViewById(R.id.range_text);
        range_putin1=(EditText)findViewById(R.id.range_putin1);
        range_putin2=(EditText)findViewById(R.id.range_putin2);
    }

    public void setRange_text(String text)
    {
        range_text.setText(text);
    }

    public String getRange_putin1()
    {
        return range_putin1.getText().toString();
    }

    public String getRange_putin2()
    {
        return range_putin2.getText().toString();
    }

    }
