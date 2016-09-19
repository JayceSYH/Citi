package nju.financecity_android.controller.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import nju.financecity_android.R;

/**
 * Created by sam on 16/9/10.
 */
public class PersonEditTitlt extends RelativeLayout {

    private TextView textName;
    private TextView textBack;
    private TextView textOk;
    private String display_text = "";

    public PersonEditTitlt(Context context){
        super(context);
        LayoutInflater.from(context).inflate(R.layout.person_edit_banner,this,true);
        onFinishInflate();
    }
    public PersonEditTitlt(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.person_edit_banner,this,true);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadComponent();
        addListener();
    }

    private void loadComponent(){
        textBack = (TextView) findViewById(R.id.back);
        textName = (TextView) findViewById(R.id.lblText);
        textOk = (TextView) findViewById(R.id.complete);
    }

    public String getDisplayString() {
        return textName.getText().toString();
    }

    public void setDisplayText(String displayText) {
        textName.setText(displayText);
    }

    private void addListener(){
        textBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

//        textOk.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
