package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;

import nju.financecity_android.R;

public class Key extends LinearLayout{
    private TextView key_text;
    private EditText key_putin;

    public Key(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.key, this, true);
    }

    public Key(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.key, this, true);
        key_text = (TextView) findViewById(R.id.key_text);
        key_putin= (EditText) findViewById(R.id.key_putin);
    }

    public void setKey_text(String text) {
        key_text.setText(text);
    }

    public String getkey_putin_text()
    {
        return key_putin.getText().toString();
    }
}
