package nju.financecity_android.dao;


public class PaymentDao extends CommonDao {
    @Override
    public String getAction() {
        return host+"/payment/mode";
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
