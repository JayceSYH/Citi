package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import nju.financecity_android.dao.PaymentDao;
import nju.financecity_android.util.HttpUtil;


public class Payment_Mode {

    public HashMap<String,Object> analyse(String payment_mode){
        UserSession userSession = UserSession.getCurrUser();
        String userid = userSession.getUserId();
        String sessionid = userSession.getSessionId();
        JSONObject root = new JSONObject();
        try {
            root.put("payment_mode",payment_mode);
            root.put("id",Integer.parseInt(userid));
            root.put("sessionId",sessionid);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("bug","root put wrong");
        }

        String url = new PaymentDao().getFullUrl();
        String mRawData = HttpUtil.sendJson(url,root,"POST");

        return DetailPost(mRawData);
    }

    private HashMap<String,Object> DetailPost(String anPost){
        HashMap<String,Object> Result = new HashMap<String,Object>();
        try {
            JSONObject Root = new JSONObject(anPost);
            Log.i("test","Log_in Root:"+Root.toString());
            //error
            int error = Root.getInt("error");
            Result.put("error",error);
            //message
            String message = Root.getString("message");
            Result.put("message",message);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return Result;
    }
}
