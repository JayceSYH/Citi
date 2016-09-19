package nju.financecity_android.dao;

import nju.financecity_android.util.HttpUtil;
import org.json.JSONObject;

/**
 * Created by coral on 16-9-15.
 */
public class RecommendDao extends CommonDao {
    @Override
    public String getAction() {
        return "/api/asset/recommend";
    }

    @Override
    public String getFullUrl() {
        return host + getAction();
    }

    @Override
    public String sendRequest() {
        return HttpUtil.sendJsonWithSession(getFullUrl(), new JSONObject(), "POST");
    }
}
