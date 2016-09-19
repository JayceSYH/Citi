package nju.financecity_android.dao;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/8.
 */
public class TypeDao extends CommonDao{
//    private static String url="http://172.19.105.116:8888/Citi/api/institution";//TODO 使用服务器URL
//    private static String url="http://192.168.1.111:8888/Citi/api/institution";
    @Override
    public String getAction() {
        return host+"/api/institution";
    }

    @Override
    public String getFullUrl() {
        return getAction();
    }

    @Override
    public String sendRequest() {
        return null;
    }

}
