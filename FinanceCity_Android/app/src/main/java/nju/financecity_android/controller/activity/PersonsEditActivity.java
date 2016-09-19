package nju.financecity_android.controller.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nju.financecity_android.R;
import nju.financecity_android.model.Person_Edit;

/**
 * Created by sam on 16/9/5.
 */
public class PersonsEditActivity extends Activity {

    private List<String> list = new ArrayList<String>();
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    private EditText MyName;
    private EditText Mobile;
    private EditText GetIn;
    private EditText GetOut;
    private TextView Birth;
    private TextView Complete;

    private Person_Edit personEdit;

    private String theDate;
    private String isUrb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        initComponent();
        dealSpinner();
        addListener();
    }

    public void initComponent(){
        mySpinner = (Spinner) findViewById(R.id.city_spinner);
        MyName = (EditText) findViewById(R.id.nameid);
        Mobile = (EditText) findViewById(R.id.mobile);
        GetIn = (EditText) findViewById(R.id.get_in);
        GetOut = (EditText) findViewById(R.id.get_out);
        Birth = (TextView) findViewById(R.id.SetBir);

        Complete = (TextView) findViewById(R.id.complete);

        personEdit = new Person_Edit();

    }

    public void dealSpinner(){
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("城市");
        list.add("农村");
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入spinner 中*/
//                mySpinner.setText("您选择的是："+ adapter.getItem(arg2));
                isUrb = adapter.getItem(arg2);
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
//                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new Spinner.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void addListener(){
        Birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(PersonsEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        theDate = String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth);
                        Birth.setText(theDate);
                    }
                },2016,9,6).show();
            }
        });

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = MyName.getText().toString();
                String mobile = Mobile.getText().toString();
                String getin = GetIn.getText().toString();
                String getout = GetOut.getText().toString();
                personEdit.analyse(id,mobile,theDate,isUrb,Integer.parseInt(getin),Integer.parseInt(getout));
            }
        });
    }

}
