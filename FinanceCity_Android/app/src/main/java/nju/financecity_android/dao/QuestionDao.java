package nju.financecity_android.dao;

import nju.financecity_android.util.HttpUtil;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by coral on 16-9-14.
 */
public class QuestionDao extends CommonDao {

    public QuestionDao(Map<String, Object> data) {
        setData(data);
    }
    @Override
    public String getAction() {
        return "/api/prefer";
    }

    @Override
    public String getFullUrl() {
        return CommonDao.host + getAction();
    }

    @Override
    public String sendRequest() {
        JSONObject jo = new JSONObject();
        for (String key: mData.keySet()) {
            mData.put(key, mData.get(key));
        }
        return HttpUtil.sendJsonWithSession(getFullUrl(), jo, "POST");
    }

    public void setData(Map<String, Object> data) {
        this.mData = data;
    }

    private Map<String, Object> mData;
}
