package edu.nju.action;

import edu.nju.service.ExceptionsAndError.*;
import edu.nju.service.ServiceManagerImpl;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.UserService.UserService;
import edu.nju.vo.BaseVO;
import edu.nju.vo.SessionIdVO;
import edu.nju.vo.UserVO;

import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@SuppressWarnings("unchecked")
public class AndroidUserAction extends AndroidAction {
    private String register() {
        Map map = getRequestMap();
        UserService userService = ServiceManagerImpl.getInstance().getUserService();
        SessionIdVO sessionIdVO = new SessionIdVO();

        try {
            FinanceCityUser financeCityUser = userService.register((String) map.get("mobile"), (String) map.get("password"), (String)map.get("username"));

            if (financeCityUser != null) {
                sessionIdVO.setSessionId(financeCityUser.getID());
                ErrorManager.setError(sessionIdVO, ErrorManager.errorNormal);
                setResult(sessionIdVO);

                return SUCCESS;
            }
            else {
                ErrorManager.setError(sessionIdVO, ErrorManager.errorRegisterFailed);
                setResult(sessionIdVO);
                return SUCCESS;
            }
        }
        catch (UserAlreadyExistException e) {
            e.printStackTrace();
            ErrorManager.setError(sessionIdVO, ErrorManager.errorUserAlreadyExist);
        }
        catch (InvalidPasswordException i) {
            i.printStackTrace();
            ErrorManager.setError(sessionIdVO, ErrorManager.errorInvalidPassword);
        }
        catch (InvalidMobileException i2) {
            i2.printStackTrace();
            ErrorManager.setError(sessionIdVO, ErrorManager.errorInvalidMobile);
        }

        setResult(sessionIdVO);

        return SUCCESS;
    }

    private String getUserVO() {
        Map map = getRequestMap();

        UserService userService = ServiceManagerImpl.getInstance().getUserService();

        try {
            FinanceCityUser financeCityUser = new FinanceCityUser();
            financeCityUser.setID((Integer)map.get("id"));
            financeCityUser.setLoginSession((String)map.get("session"));

            setResult(userService.getUserVO(financeCityUser));
        }
        catch (Exception e) {
            e.printStackTrace();
            UserVO userVO = new UserVO();
            ErrorManager.setError(userVO, ErrorManager.errorNotLogin);

            setResult(userVO);
        }

        return SUCCESS;
    }

    private String setUserInfo() {
        UserService userService = ServiceManagerImpl.getInstance().getUserService();
        Map map = getRequestMap();

        BaseVO baseVO = new BaseVO();
        try {
            FinanceCityUser financeCityUser = new FinanceCityUser();
            financeCityUser.setID((Integer)map.get("id"));
            financeCityUser.setLoginSession((String)map.get("session"));

            userService.modifyUserInfo((String)map.get("birthday"), (Integer)map.get("income"),
                    (Integer)map.get("isUrben") == 1, (Integer)map.get("expense"),
                    financeCityUser);

            ErrorManager.setError(baseVO, ErrorManager.errorNormal);
            setResult(baseVO);
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorNotLogin);
            setResult(baseVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorInvalidParameter);
            setResult(baseVO);
        }

        return SUCCESS;
    }

    public String api_User() {
        String method = request.getMethod();
        if (method.equals("POST")) {
            return register();
        }
        else if (method.equals("GET")) {
            return getUserVO();
        }
        else if (method.equals("PUT")) {
            return setUserInfo();
        }
        else {
            BaseVO baseVO = new BaseVO();
            ErrorManager.setError(baseVO, ErrorManager.errorUnhandledMethod);
            setResult(baseVO);
        }

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String login() {
        Map map = getRequestMap();

        SessionIdVO ret = new SessionIdVO();
        UserService userService = ServiceManagerImpl.getInstance().getUserService();

        String username = (String)map.get("username");
        String password = (String)map.get("password");
        if (username == null || password == null) {
            ErrorManager.setError(ret, ErrorManager.errorInvalidParameter);

            setResult(ret);
            return SUCCESS;
        }

        FinanceCityUser financeCityUser = userService.login(username, password);
        if (financeCityUser == null) {
            ErrorManager.setError(ret, ErrorManager.errorInvalidPassword);

            setResult(ret);
        }
        else {
            ErrorManager.setError(ret, ErrorManager.errorNormal);
            ret.setSessionId(financeCityUser.getID());
            setResult(ret);
        }

        return SUCCESS;
    }
}