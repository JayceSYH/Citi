package nju.financecity_android.model;

import nju.financecity_android.dao.CommonDao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by coral on 16-9-4.
 */
public class User {

    public static User login(String account, String passwd) {
        return null;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;
    String mobile;
    String emailAddr;
    String sessionId;
}
