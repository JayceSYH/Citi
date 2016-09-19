package nju.financecity_android.dao;

import nju.financecity_android.util.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by coral on 16-9-9.
 */
public class ProductsDao extends CommonDao {

    public ProductsDao(List<String> ids) {
        this.ids = ids;
    }

    private List<String> ids;

    @Override
    public String getAction() {
        return "/api/product/infos";
    }

    @Override
    public String getFullUrl() {
        return CommonDao.host + getAction();
    }

    @Override
    public String sendRequest() {
        String paramStr = "";
        for (String id: ids) {
            if (!paramStr.equals("")) {
                paramStr += ":" + id;
            } else {
                paramStr = id;
            }
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("ids", paramStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HttpUtil.sendJson(getFullUrl(), jo, "POST");
    }
}
