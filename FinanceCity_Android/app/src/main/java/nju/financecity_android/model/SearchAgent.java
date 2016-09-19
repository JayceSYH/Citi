package nju.financecity_android.model;

import android.util.Log;

import nju.financecity_android.dao.CommonDao;
import nju.financecity_android.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import nju.financecity_android.dao.TypeDao;

/**
 * Created by Administrator on 2016/9/8.
 */
public class SearchAgent {
    public ArrayList<String> getAgent(String type)
    {
        JSONObject jObject=new JSONObject();
        try {
            jObject.put("type", type);
        }catch(Exception e)
        {
            Log.i("test","getAgent json exception");
            e.printStackTrace();
        }
//        String result= HttpUtil.sendJson(CommonDao.host + "/api/institution", jObject, "POST");
        String result=new TypeDao().sendPost(jObject);
        return exchangeAgent(result);
    }
    private static ArrayList<String> exchangeAgent(String source)
    {
        JSONObject jsonObject=null;
        ArrayList<String> list=new ArrayList<String>();
        try {
            jsonObject = new JSONObject(source);
            if(jsonObject.getInt("error")==0) {
                JSONArray jlist = jsonObject.getJSONArray("institutions");
                for (int i = 0; i < jlist.length(); i++) {
                    list.add(jlist.getString(i));
                }
                Log.i("test", "exchangeAgent: list=" + list);
            }
            else
            {
                Log.e("test", "exchangeAgent: json error");
            }
        } catch (Exception e)
        {
            Log.i("test","exchangeAgent: json exception");
            e.printStackTrace();
        }
        return list;
    }
}
