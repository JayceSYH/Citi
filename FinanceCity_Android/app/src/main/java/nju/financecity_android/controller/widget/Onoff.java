package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import nju.financecity_android.R;

/**
 * Created by Administrator on 2016/9/4.
 */
public class Onoff extends LinearLayout {
    private String onoff_checked;

    private TextView onoff_text;
    private Switch onoff_switch;
    private boolean checked;

    public Onoff(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.onoff, this, true);
        onFinishInflate();
    }

    public Onoff(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.onoff, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        onoff_text = (TextView) findViewById(R.id.onoff_text);
        onoff_switch= (Switch) findViewById(R.id.onoff_switch);
    }

    public void setOnoff_text(String text) {
        onoff_text.setText(text);
    }

    public void setOnoff_switch(boolean checked)
    {
        this.checked=checked;
        onoff_switch.setChecked(checked);
    }

    public boolean getOnoff_switch()
    {
        return checked;
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listner)
    {
        onoff_switch.setOnCheckedChangeListener(listner);
    }
}
