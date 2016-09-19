package nju.financecity_android.util;

import android.util.Log;

import nju.financecity_android.dao.CommonDao;
import nju.financecity_android.model.UserSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * Created by coral on 16-9-3.
 */
public class HttpUtil {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     *
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String sendRequest(String url, String requestBody, String method) {
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(method);
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.connect();

            PrintWriter pw = new PrintWriter(connection.getOutputStream());
            pw.print(requestBody);
            pw.flush();
            pw.close();

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                result += new String(scanner.nextLine().getBytes("UTF-8"), "UTF-8") + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("recommend","result string "+result);
        return result;
    }

    public static String sendJsonWithSession(String url, JSONObject jsonData, String method) {
        try {
//            if (UserSession.getCurrUser() != null) {
//                jsonData.put("id", UserSession.getCurrUser().getUserId());
//                jsonData.put("sessionId", UserSession.getCurrUser().getSessionId());
                jsonData.put("id", 4);//TODO use currentUser
                jsonData.put("sessionId", "39f175661b1b8961b9ad977326a1825c");
                Log.i("recommend","parameters "+jsonData.toString());
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sendJson(url, jsonData, method);
    }

    public static String sendJson(String url, JSONObject jsonData, String method) {
        return sendRequest(url, jsonData.toString(), method);
    }

    public static void main(String[] args) {
        // System.out.println(sendRequest(CommonDao.host + "/api/login", "{'username': '18187459874', 'password':'00000000' }", "POST"));
        // System.out.println(sendRequest(CommonDao.host + "/api/user", "{'id': 4, 'session': 'bae11f9a5a0b8c29ea327adb06609f94'}", "GET"));
        // System.out.println(sendGet(CommonDao.host + "/api/user?id=4&&sessionId=a74739ceefa71db663907dfe82fb3728"));
        // System.out.println(sendRequest(CommonDao.host + "/api/investment", "{'id':4, 'session':'8f29c9431ac2b281c29205342c127636'}", "POST"));
        System.out.println(sendRequest(CommonDao.host + "/api/product/info", "{'id':40000177}", "POST"));
    }
}
