package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import nju.financecity_android.dao.PersonDao;

/**
 * Created by sam on 16/9/11.
 */
public class PersonGet {
    public HashMap<String,Object> analyse(){
        UserSession userSession = UserSession.getCurrUser();
        String userid = userSession.getUserId();
        String sessionid = userSession.getSessionId();
//        JSONObject root = new JSONObject();
//        try {
//            root.put("id",Integer.parseInt(userid));
//            root.put("sessionId",sessionid);
//        }catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("bug","root put wrong");
//        }
        String SessId = "{\"id\""+":"+userid+","+"\"sessionId\""+":"+"\""+sessionid+"\"}";
        String mRawData = new PersonDao().getData(SessId);

        return DetailGet(mRawData);
    }

    private HashMap<String,Object> DetailGet(String anGet){
        HashMap<String,Object> Result = new HashMap<String,Object>();
        try {
            JSONObject Root = new JSONObject(anGet);
            Log.i("test","Log_in Root:"+Root.toString());
            //error
            int error = Root.getInt("error");
            Result.put("error",error);
            //message
            String message = Root.getString("message");
            Result.put("message",message);
            JSONArray array = Root.getJSONArray("data");
            for(int i=0;i < array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                int id = jsonObject.getInt("id");
                Result.put("id",id);
                String mobile = jsonObject.getString("mobile");
                Result.put("mobile",mobile);
                String birthday = jsonObject.getString("birthday");
                Result.put("birthday",birthday);
                int isUrban = jsonObject.getInt("isUrban");
                Result.put("isUrban",isUrban);
                int income = jsonObject.getInt("income");
                Result.put("income",income);
                int expense = jsonObject.getInt("expense");
                Result.put("expense",expense);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return Result;
    }



}
