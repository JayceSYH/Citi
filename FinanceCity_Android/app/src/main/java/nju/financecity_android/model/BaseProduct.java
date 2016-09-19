package nju.financecity_android.model;

import nju.financecity_android.dao.ProductDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by coral on 16-9-3.
 */
public abstract class BaseProduct {

    public BaseProduct(String productId) {
        setProductId(productId);
        mData = new HashMap<>();
        processData();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
        mRawData = new ProductDao(productId).readData();
        if (mRawData.get("history") != null) {
            ((Map) mRawData.get("data")).put("history", mRawData.get("history"));
        }
        if (mRawData.get("error").toString().equals("0")) {
            mRawData = (Map) mRawData.get("data");
        }
    }

    public String getProperty(String name) {
        return mData.get(name).toString();
    }

    public Map getRawData() {
        return mRawData;
    }

    public Map<String, Object> getProperties() {
        return mData;
    }

    public Set<String> getPropertyList() {
        return mData.keySet();
    }

    /**
     * 将读取到的数据写入到属性。
     */
    protected abstract void processData();

    protected Map mRawData;
    protected Map<String, Object> mData;
    protected String productId;
}
