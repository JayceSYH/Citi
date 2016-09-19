package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.vo.PropertyVO;

import java.util.List;

/**
 * Created by coral on 16-9-2.
 */
public class SimpleProperty extends LinearLayout {

    public SimpleProperty(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.simple_property, this);
    }

    public SimpleProperty(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.simple_property, this);
    }

    // --------interfaces---------------
    public void setPropertyName(String name) {
        if (isInflatingFinished) {
            txtPropertyName.setText(name);
        }
        else {
            this.propertyName = name;
        }
    }

    public String getPropertyName() {
        return txtPropertyName.getText().toString();
    }

    public void setValue(Object value) {
        if (isInflatingFinished) {
            txtValue.setText(String.valueOf(value));
        }
        else {
            this.value = String.valueOf(value);
        }
    }

    public String getValue() {
        return txtValue.getText().toString();
    }

    public void setPropertyColor(int colorId) {
        if (isInflatingFinished) {
            txtPropertyName.setTextColor(getResources().getColor(colorId));
        }
        else {
            this.clrPropertyName = colorId;
        }
    }

    public void setValueColor(int colorId) {
        if (isInflatingFinished) {
            txtValue.setTextColor(getResources().getColor(colorId));
        }
        else {
            this.clrValue = colorId;
        }
    }

    public void set(String propertyName, Object value) {
        setPropertyName(propertyName);
        setValue(value);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadComponents();
        isInflatingFinished = true;
        txtValue.setText(value);
        txtValue.setTextColor(getResources().getColor(clrValue));
        txtPropertyName.setText(propertyName);
        txtPropertyName.setTextColor(getResources().getColor(clrPropertyName));
    }


    private void loadComponents() {
        txtPropertyName = (TextView) findViewById(R.id.txtPropertyName);
        txtValue = (TextView) findViewById(R.id.txtValue);
    }

    private boolean isInflatingFinished = false;
    private TextView txtPropertyName, txtValue;
    private String propertyName, value;
    private int clrPropertyName, clrValue;
}
