package nju.financecity_android.dao;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sam on 16/9/11.
 */
public class PersonDao extends CommonDao{
//    private static String mainurl = "http://172.28.3.163:8080/api/";

    public String getData(String sessid){
        String ResultRes = "";
        HttpURLConnection connection = null;
        try{
            URL url = new URL(getFullUrl()+sessid);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("Get");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            // 设置请求的头
            connection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            connection.setRequestProperty("Content-Type",
                    "application/json");
//            connection.setRequestProperty("id",sessid);
            //连接
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                //对获取到的输入流进行处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                Log.i("testResult", "result = " + response.toString());
                ResultRes = response.toString();
            }else{
                Log.i("test", "Wrong");
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return ResultRes;
    }

    @Override
    public String getAction() {
        return host+"/api/user/";
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
