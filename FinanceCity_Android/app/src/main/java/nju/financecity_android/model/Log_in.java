package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import nju.financecity_android.dao.Log_inDao;

/**
 * Created by sam on 16/9/6.
 */
public class Log_in {

    public HashMap<String,Object> analyse(String username, String passwd){
        JSONObject root = new JSONObject();
        try {
            root.put("username",username);
            root.put("password",passwd);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("bug","root put wrong");
        }

        String mRawData = new Log_inDao().sendPost(root);

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
            //session
            String session = Root.getString("sessionId");
            Result.put("session",session);

            int id = Root.getInt("id");
            Result.put("id",id);

            Log.i("test","can still to here");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return Result;
    }

}
