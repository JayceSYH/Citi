package nju.financecity_android.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import nju.financecity_android.R;
import nju.financecity_android.model.Log_in;
import nju.financecity_android.model.UserSession;

/**
 * Created by sam on 16/9/4.
 */
public class Log_inActivity extends Activity {

    private LayoutInflater LInflater;
    private TextView accountText;
    private TextView pswText;
    private TextView Btnreg;
    private Button BtnSub;

    private String Account;
    private String Password;

    private Log_in log_inModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        initComponents();
        addListener();

    }

    public void initComponents(){
        LInflater = LayoutInflater.from(this);
        log_inModel = new Log_in();
//        HashMap<String,Object> res = new HashMap<String, Object>();
        accountText = (TextView) findViewById(R.id.accountEt);
        pswText = (TextView) findViewById(R.id.pwdEt);
        Btnreg = (TextView) findViewById(R.id.Btnregister);
        BtnSub = (Button) findViewById(R.id.BtnSub);
    }

    public void addListener(){
        Btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent RegIntent = new Intent(Log_inActivity.this,RegisterActivity.class);
                        startActivity(RegIntent);
                        Log_inActivity.this.finish();

                        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    }
                },300);
            }
        });

        BtnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account = accountText.getText().toString();
                Password = pswText.getText().toString();
                SendPost(Account,Password);
//                Toast.makeText(Log_inActivity.this,"click",Toast.LENGTH_SHORT).show();
//                HashMap<String,Object> res = log_inModel.analyse(Account,Password);
//                int error = (int) res.get("error");
//                String mess = (String) res.get("message");
//                String sess = (String) res.get("session");
//                if (error!=0){
//                    UserSession.setCurrUser(new UserSession(Account,sess));
//                    Intent intent = new Intent(Log_inActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(Log_inActivity.this,mess,Toast.LENGTH_SHORT).show();
//                }
//                Log.d("account",Account);
//                Log.d("account",Password);
            }
        });
    }

    private void SendPost(String Acc,String Pass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HashMap<String,Object> res = log_inModel.analyse(Account,Password);
                    int error = (int) res.get("error");
                    Log.d("SeeErr","error为"+error+"");
                    String mess = (String) res.get("message");
                    Log.d("SeeErr","mess为"+mess);
                    String sess = (String) res.get("session");
                    int id=(int)res.get("id");
                    if (error==0){
                        Log.i("test","change to mainActivity1");
                        UserSession.setCurrUser(new UserSession(id+"",sess));
                        Log.i("test","change to mainActivity2");
                        Intent intent = new Intent(Log_inActivity.this,MainActivity.class);
                        Log.i("test","change to mainActivity3");
                        startActivity(intent);
                    }else if(error!=0){
                        Toast.makeText(Log_inActivity.this,mess,Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
