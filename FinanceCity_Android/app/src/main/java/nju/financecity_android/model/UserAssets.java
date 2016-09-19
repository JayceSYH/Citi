package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nju.financecity_android.dao.AssetHistoryDao;
import nju.financecity_android.dao.AssetValueDao;
import nju.financecity_android.dao.InvestmentDao;
import nju.financecity_android.vo.ProductInfo;

/**
 * Created by Administrator on 2016/9/16.
 */
public class UserAssets {
    private static JSONArray value;
    private static JSONArray history;
    private UserAssets(){}

    public static JSONArray getValue() {
        if(value==null)
        {
            JSONObject jsonObject=new JSONObject();//value
//            UserSession user=UserSession.getCurrUser();
//            Log.i("test","user "+user.getSessionId());
            try {
//            jsonObject.put("id", Integer.parseInt(user.getUserId()));//TODO 传递当前用户
//            jsonObject.put("sessionId",user.getSessionId());
                jsonObject.put("id", 6);
                jsonObject.put("sessionId","745acb2a229043089cf1d04114847705");
//            jsonObject.put("days",20);//TODO
            }catch(Exception e)
            {
                Log.i("test","user session or json exception");
                e.printStackTrace();
            }

            Log.i("test","jObject of parameters "+jsonObject);

            String result=new AssetValueDao().sendPost(jsonObject);
            try{
                value=new JSONObject(result).getJSONArray("assetValues");
            }catch(Exception e)
            {
                Log.i("test","asset value result exception");
                e.printStackTrace();
            }
        }
        return value;
    }

    public static JSONArray getHistory() {
        if(history==null)
        {
            JSONObject jsonObject=new JSONObject();//history
//            UserSession user=UserSession.getCurrUser();
//            Log.i("test","user "+user.getSessionId());
            try {
//            jsonObject.put("id", Integer.parseInt(user.getUserId()));
//            jsonObject.put("sessionId",user.getSessionId());
                jsonObject.put("id", 4);
                jsonObject.put("sessionId","e546818afd542087a731700acfcb2d43");
            }catch(Exception e)
            {
                Log.i("test","user session or json exception");
                e.printStackTrace();
            }

            Log.i("test","jObject of parameters "+jsonObject);

            String result=new AssetHistoryDao().sendPost(jsonObject);
            try{
                history=new JSONObject(result).getJSONArray("tradeHistoryVOList");
            }catch(Exception e)
            {
                Log.i("test","asset value result exception");
                e.printStackTrace();
            }
        }
        return history;
    }
}
