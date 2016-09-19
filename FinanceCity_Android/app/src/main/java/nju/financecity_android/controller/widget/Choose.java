package nju.financecity_android.controller.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.MainActivity;

/**
 * Created by st0001 on 2016/9/3.
 */
public class Choose extends LinearLayout{
    private String choose_name;
    private boolean spinner2_visible;

    private String[] spinner1_content={"——"};
    private String[] spinner2_content={"——"};
    private String spinner1_choosed;
    private String spinner2_choosed;
    private TextView choose_text;
    private Spinner choose_spinner1;
    private ArrayAdapter spinner1_adapter;
    private Spinner choose_spinner2;
    private ArrayAdapter spinner2_adapter;

    public void setSpinner2Visible(int visible)
    {
        choose_spinner2.setVisibility(visible);
    }

    public Choose(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.choose, this, true);
        onFinishInflate();
    }

    public Choose(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.choose, this, true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        choose_text = (TextView) findViewById(R.id.choose_text);
        choose_spinner1= (Spinner) findViewById(R.id.choose_spinner1);
        choose_spinner2= (Spinner) findViewById(R.id.choose_spinner2);
        spinner1_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner1_content);
        spinner1_adapter.notifyDataSetChanged();
        spinner2_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner2_content);
        spinner2_adapter.notifyDataSetChanged();
        choose_spinner1.setAdapter(spinner1_adapter);
        choose_spinner2.setAdapter(spinner2_adapter);

    }


    public void setChoose_text(String text) {
        choose_text.setText(text);
    }


    public void setSpinner1_content(int master_resource)
    {
        this.spinner1_content=getResources().getStringArray(master_resource);
        spinner1_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner1_content);
        choose_spinner1.setAdapter(spinner1_adapter);
    }
    public void setSpinner1_content(String[] master_content)
    {
        this.spinner1_content=master_content;
        spinner1_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner1_content);
        choose_spinner1.setAdapter(spinner1_adapter);
    }

    public void setSpinner2_content(int slavery_resource)
    {
        spinner2_content=getResources().getStringArray(slavery_resource);
        spinner2_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner2_content);
        choose_spinner2.setAdapter(spinner2_adapter);
    }

    public void setSpinner2_content(String[] master_content)
    {
        this.spinner2_content=master_content;
        spinner2_adapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_element,spinner2_content);
        choose_spinner2.setAdapter(spinner2_adapter);
    }

    public void setSpinner1_choosed(int i)
    {
        spinner1_choosed=spinner1_content[i];
        choose_spinner1.setSelection(i,true);
    }

    public void setSpinner2_choosed(int i)
    {
        spinner2_choosed=spinner2_content[i];
        choose_spinner2.setSelection(i,true);
    }

    public String getSpinner1_choosed()
    {
        return spinner1_choosed;
    }

    public String getSpinner2_choosed()
    {
        return spinner2_choosed;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener1,AdapterView.OnItemSelectedListener listener2)
    {
        choose_spinner1.setOnItemSelectedListener(listener1);

        choose_spinner2.setOnItemSelectedListener(listener2);

    }

}
