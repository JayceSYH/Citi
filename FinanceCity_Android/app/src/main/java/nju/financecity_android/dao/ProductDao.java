package nju.financecity_android.dao;

import nju.financecity_android.util.HttpUtil;
import nju.financecity_android.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by coral on 16-9-3.
 */
public class ProductDao extends CommonDao {

    public ProductDao(String productId) {
        setProductId(productId);
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String getAction() {
        return "/api/product/info";
    }

    @Override
    public String getFullUrl() {
        return CommonDao.host + getAction();
    }

    @Override
    public String sendRequest() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", StringUtil.parseInt(productId));
            jo.put("days", 60);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return HttpUtil.sendJson(getFullUrl(), jo, "POST");
    }

    private String productId;

}
