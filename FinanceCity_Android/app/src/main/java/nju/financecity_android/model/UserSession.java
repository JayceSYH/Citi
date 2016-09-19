package nju.financecity_android.model;

/**
 * Created by coral on 16-9-4.
 */
public class UserSession {

    public UserSession(String Userid,String Sessionid) {
        this.userId = Userid;
        this.sessionId = Sessionid;
    }

    public static UserSession getCurrUser() {
        return currUser;
    }

    public static void setCurrUser(UserSession CurrUser){
        currUser = CurrUser;
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public static void disableUser() {
        currUser = null;
    }

    public boolean isLoggedIn() {
        return currUser == null;
    }

    static UserSession currUser;

    String userId;
    String sessionId;
}
