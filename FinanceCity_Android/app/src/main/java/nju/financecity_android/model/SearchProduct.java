package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nju.financecity_android.dao.SearchDao;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SearchProduct {
    public static ArrayList<HashMap<String,Object>> analyse(JSONObject param)
    {
        // 先封装一个 JSON 对象
//        JSONObject param = new JSONObject();
//        try {
//            param.put("keyword", "300");
//            param.put("type", "Fund");
//        } catch (JSONException e) {}
        String mRawData= new SearchDao().sendPost(param);
//        String mRawData="{error=0,\"data\":[{\"est_date\":null,\"expected_income_rate\":null,\"mng_charge_rate\":0.6,\"name\":\"华泰柏瑞沪深300交易型开放式指数证券投资基金\",\"net_value\":null,\"pid\":null,\"productType\":\"fund\",\"sid\":\"510300\",\"state\":null,\"type\":\"ETF基金\"}]}";
        return onPostExecute(mRawData);
    }

    public static ArrayList<HashMap<String,Object>> onPostExecute(String result)
    {
        ArrayList<HashMap<String,Object>> resultList=new ArrayList<HashMap<String,Object>>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            Log.i("test","SearchProduct: jsonObject="+jsonObject.toString());
            int resultCode = jsonObject.getInt("error");
            if (resultCode == 0) {
                JSONArray resultJsonArray = jsonObject.getJSONArray("data");
                Log.i("test","resultJsonArray="+resultJsonArray.toString());
                for (int i = 0; i < resultJsonArray.length(); i++) {
                    JSONObject resultJsonObject = resultJsonArray.getJSONObject(i);
                    String type = resultJsonObject.getString("productType");
                    Log.i("test","SearchProduct: type="+type);
                    HashMap<String, Object> map = new HashMap<>();
                    switch (type) {
                        case "bank":
//                            map.put("productId", resultJsonObject.getInt("productId"));
                            map.put("productType", type);
                            map.put("pid", resultJsonObject.getInt("pid"));
                            map.put("name", resultJsonObject.getString("name"));
                            map.put("yearly_income_rate", resultJsonObject.get("year_rate"));
                            map.put("product_type", resultJsonObject.getString("product_type"));
                            map.put("income_type", resultJsonObject.getString("income_type"));
                            map.put("initial_money", resultJsonObject.getInt("initial_money"));
                            map.put("open_date", resultJsonObject.getString("open_date"));
                            map.put("distributor_bank", resultJsonObject.getString("distributor_bank"));
                            map.put("distributor_institution", resultJsonObject.getString("distributor_institution"));
                            break;
                        case "fund":
                            map.put("productType", type);
                            map.put("pid", resultJsonObject.get("pid"));
                            map.put("name", resultJsonObject.get("name"));
                            map.put("expected_income_rate", resultJsonObject.get("expected_income_rate"));
                            map.put("state", resultJsonObject.get("state"));
                            map.put("net_value", resultJsonObject.get("net_value"));
                            map.put("sid", resultJsonObject.get("sid"));
                            map.put("type", resultJsonObject.get("type"));
                            map.put("mng_charge_rate", resultJsonObject.get("mng_charge_rate"));
                            map.put("est_date", resultJsonObject.get("est_date"));
                            break;
                        case "insurance":
//                            map.put("productId", resultJsonObject.getInt("productId"));
                            map.put("productType", type);
                            map.put("pid", resultJsonObject.getInt("pid"));
                            map.put("name", resultJsonObject.getString("name"));
                            map.put("insurance_life", resultJsonObject.getDouble("insurance_life"));
                            map.put("amount_in_force", resultJsonObject.getString("amount_in_force"));
                            map.put("way_of_charge", resultJsonObject.getString("way_of_charge"));
                            map.put("distributor", resultJsonObject.getInt("distributor"));
                            break;
                        case "bond":
//                            map.put("productId", resultJsonObject.getInt("productId"));
                            map.put("productType", type);
                            map.put("pid", resultJsonObject.getInt("pid"));
                            map.put("name", resultJsonObject.getString("name"));
                            map.put("year_interest_rate", resultJsonObject.getDouble("year_rate"));
                            map.put("nominal_interest_rate", resultJsonObject.getString("nominal_interest_rate"));
                            map.put("life", resultJsonObject.getString("life"));
                            map.put("type", resultJsonObject.getString("type"));
                            map.put("code", resultJsonObject.getInt("code"));
                            break;
                    }
                    resultList.add(map);
                    Log.i("test","SearchProduct: map="+map.toString());
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("test","SearchProduct: resultList="+resultList.toString());
        return resultList;
    }
}
