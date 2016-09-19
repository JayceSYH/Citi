package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import nju.financecity_android.R;
import nju.financecity_android.model.PersonGet;

/**
 * Created by sam on 16/9/5.
 */
public class Persons extends Fragment {

    private TextView My_Name;
    private TextView Mobile;
    private TextView Birthday;
    private TextView IfCity;
    private TextView GetIn;
    private TextView GetOut;
    private ImageView Modify, prefer;

    private View theview;
    private PersonGet personGet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_user, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        theview = getView();
        initComponent();
        addListener();
    }

    private void initComponent(){
        My_Name = (TextView) findViewById(R.id.id_name);
        Mobile = (TextView) findViewById(R.id.mobile);
        Birthday = (TextView) findViewById(R.id.birthday);
        IfCity = (TextView) findViewById(R.id.city_country);
        GetIn = (TextView) findViewById(R.id.get_in);
        GetOut = (TextView) findViewById(R.id.get_out);
        Modify = (ImageView) findViewById(R.id.modify_mes);
        prefer = (ImageView) findViewById(R.id.btPrefer);

        personGet = new PersonGet();

        updateMessage();

    }

    private View findViewById(int ResId){
        View FindView = theview.findViewById(ResId);
        return FindView;
    }

    private void addListener(){
        prefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Persons.this.getActivity(), QuestionActivity.class);
                Persons.this.getActivity().startActivity(intent);
            }
        });

        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PersonsEditActivity.class);
                startActivity(intent);

            }
        });
    }

    private void updateMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HashMap<String ,Object> res = personGet.analyse();
                    int error = (int) res.get("error");
                    Log.d("SeeErr","error为"+error+"");
                    String mess = (String) res.get("message");
                    Log.d("SeeErr","mess为"+mess);
                    if (error == 0){
                        int id = (int) res.get("id");
                        String mob = (String) res.get("mobile");
                        String bir = (String) res.get("birthday");
                        int urb = (int) res.get("isUrban");
                        int come = (int) res.get("income");
                        int expen = (int) res.get("expense");
                        SetTextView(id,mob,bir,urb,come,expen);
                    }else {
                        Toast.makeText(getActivity(),mess,Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void SetTextView(int id,String mob,String bir,int urb,int income,int expense){
        My_Name.setText(id+"");
        Mobile.setText(mob);
        Birthday.setText(bir);
        if (urb==0){
            IfCity.setText("农村");
        }else {
            IfCity.setText("城市");
        }
        GetIn.setText(income+"");
        GetOut.setText(expense+"");
    }
}
