package nju.financecity_android.dao;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by coral on 16-9-3.
 */
public abstract class CommonDao {

//    public static final String host = "http://172.19.115.96:8080";
    // public static String host= "http://172.19.115.96:8080";
//     public static String host="http://172.19.108.178:8888/Citi";
    public static String host="http://172.19.108.178:8888/Citi";
//    public static String host="http://172.20.10.2:8888/Citi";
    /**
     * 指定接口。
     * 不一定要实现这个方法。
     *
     * @return api地址
     */
    public abstract String getAction();

    /**
     * 在这里定义完整的接口url。
     *
     * @return 完整的接口url。如果是get请求应该包含所有的参数
     */
    public abstract String getFullUrl();

    /**
     * 在这里发送自定义http请求并返回响应内容。
     *
     * @return 服务器响应内容。
     */
    public abstract String sendRequest();

    public Map readData() {
        Map data = null;
        String jsonString = sendRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            data = objectMapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            System.err.println("读取到了数据，但是在解析为json的时候出现了错误。将结果包装为默认对象。");
            data = new HashMap();
            data.put("error", 1);
            data.put("message", "Error parsing Json string");
            data.put("data", jsonString);
        }
        return data;
    }

    public String sendPost(JSONObject param)
    {
        try {
            HttpPost request = new HttpPost(getFullUrl());
            Log.i("test", "sendPost: "+getFullUrl());
            // 绑定到请求 Entry
            StringEntity se = new StringEntity(param.toString());

            Log.i("test","sendRequest: StringEntity="+param.toString());

            se.setContentType("application/json");
            request.setEntity(se);
            // 发送请求
            HttpResponse httpResponse = null;
            httpResponse = new DefaultHttpClient().execute(request);
            // 得到应答的字符串，这也是一个 JSON 格式保存的数据
            String retSrc = EntityUtils.toString(httpResponse.getEntity());
            Log.i("test","sendRequest: retSrc="+retSrc);
            return retSrc;
        } catch (Exception e)
        {
            Log.e("test", "sendRequest: Exception");
            e.printStackTrace();
        }
        return null;
    }
}
