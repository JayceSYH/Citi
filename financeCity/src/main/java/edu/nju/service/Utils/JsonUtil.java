package edu.nju.service.Utils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
public class JsonUtil {
    private String result;

    public JsonUtil(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        result = stringBuilder.toString();
    }

    public JsonUtil(String srcString) {
        result = srcString;
    }

    public String getResultString() {
        return result;
    }

    public Map getMap() {
        if (result != null) {
            try {
                return JSONObject.fromObject(result);
            }catch (JSONException e) {
                return null;
            }
        }
        else {
            return null;
        }
    }
}
