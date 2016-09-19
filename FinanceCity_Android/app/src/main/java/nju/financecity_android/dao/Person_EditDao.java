package nju.financecity_android.dao;


public class Person_EditDao  extends  CommonDao{


    @Override
    public String getAction() {
        return host+"/api/user";
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
