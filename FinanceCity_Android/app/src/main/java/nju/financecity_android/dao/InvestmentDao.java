package nju.financecity_android.dao;

import nju.financecity_android.util.HttpUtil;
import org.json.JSONObject;

/**
 * Created by coral on 16-9-12.
 */
public class InvestmentDao extends CommonDao {
    @Override
    public String getAction() {
        return "/api/investment";
    }

    @Override
    public String getFullUrl() {
        return CommonDao.host + getAction();
    }

    @Override
    public String sendRequest() {
        return HttpUtil.sendJsonWithSession(getFullUrl(), new JSONObject(), "POST");
    }

    public static void main(String[] args) {
    }
}
