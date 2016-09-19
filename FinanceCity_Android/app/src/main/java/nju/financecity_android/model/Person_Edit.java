package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import nju.financecity_android.dao.PersonDao;
import nju.financecity_android.dao.Person_EditDao;
import nju.financecity_android.util.HttpUtil;


public class Person_Edit {

    public HashMap<String,Object> analyse(String id,String mob,String bir,String urb,int income,int expense){
        UserSession userSession = UserSession.getCurrUser();
        String userid = userSession.getUserId();
        String sessionid = userSession.getSessionId();
        JSONObject root = new JSONObject();
        try {
            root.put("id",Integer.parseInt(userid));
            root.put("sessionId",sessionid);
            root.put("birthday",bir);
            int Urban = 0;
            if(urb.equals("城市")){
                Urban = 1;
            }
            root.put("isUrban",Urban);
            root.put("income",income);
            root.put("expense",expense);
            root.put("id",id);
            root.put("mobile",mob);

        }catch (JSONException e) {
            e.printStackTrace();
            Log.d("bug","root put wrong");
        }

        String url = new Person_EditDao().getFullUrl();
        String mRawData = HttpUtil.sendJson(url,root,"PUT");

        return DetailGet(mRawData);
    }

    private HashMap<String,Object> DetailGet(String anGet) {
        HashMap<String, Object> Result = new HashMap<String, Object>();
        try {
            JSONObject Root = new JSONObject(anGet);
            Log.i("test", "Log_in Root:" + Root.toString());
            //error
            int error = Root.getInt("error");
            Result.put("error", error);
            //message
            String message = Root.getString("message");
            Result.put("message", message);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return Result;
    }
}
