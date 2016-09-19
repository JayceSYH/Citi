package nju.financecity_android.dao;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sam on 16/9/6.
 */
public class Log_inDao extends  CommonDao{
//    private static String mainurl = "http://192.168.1.111:8888/Citi/api/";

    public String sendPost(JSONObject param) {

        String ResultRes = null;
        HttpURLConnection connection = null;
//        Log.i("testt", mainurl + Api);
        try {
            URL url = new URL(getFullUrl());
            connection = (HttpURLConnection) url.openConnection();
//            Log.i("testss", "try01");
//            String data = "\"username\":\"administer\",\"passwd\":\"123456\"";
            String data = param.toString();

            Log.i("login","param "+data);

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            // 设置请求的头
            connection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            connection.setRequestProperty("Content-Type",
                    "application/json");
            // 设置请求的头
//                    connection.setRequestProperty("Content-Length",
//                            String.valueOf(data.getBytes().length));
            // 设置请求的头
//                    connection
//                            .setRequestProperty("User-Agent",
//                                    "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0");
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(data);
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                Log.i("test", "result = " + response.toString());
                ResultRes = response.toString();
            } else {
                Log.i("test", "Wrong");
            }
//                    HttpClient httpClient = new DefaultHttpClient();
//                    Log.i("test","try0");
//                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
//                    Log.i("test","try1");
//                    HttpResponse httpResponse = httpClient.execute(httpGet);
//                    Log.i("test","try2");
//                    if(httpResponse.getStatusLine().getStatusCode() == 200){
//                        HttpEntity entity = httpResponse.getEntity();
//                        String response = EntityUtils.toString(entity,"utf-8");
//                        Log.i("test","response = "+response);
//                    }else {
//                        Log.i("test","Wrong");
//                    }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("testss", "result=" + e);
        }

        return ResultRes;
    }

    @Override
    public String getAction() {
        return host+"/api/login";
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
