package nju.financecity_android.dao;

/**
 * Created by Administrator on 2016/9/12.
 */
public class AssetValueDao extends CommonDao{
//    private static String url="http://192.168.1.111:8888/Citi/api/asset/value";
    @Override
    public String getAction() {
        return host+"/api/asset/value";
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
