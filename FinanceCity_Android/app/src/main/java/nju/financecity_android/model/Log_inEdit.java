package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import nju.financecity_android.dao.Log_EditDao;
import nju.financecity_android.dao.Log_inDao;
import nju.financecity_android.util.HttpUtil;


public class Log_inEdit {
    public HashMap<String,Object> analyse(String mobile, String secure,String password){
        JSONObject root = new JSONObject();
        try {
            root.put("mobile",mobile);
            root.put("secure_code",secure);
            root.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("bug","root put wrong");
        }

        String url = new Log_EditDao().getFullUrl();
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
