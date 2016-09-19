package edu.nju.action;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.ExceptionsAndError.*;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.UserService.UserService;
import edu.nju.vo.BaseVO;
import edu.nju.vo.SessionIdVO;
import edu.nju.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@Controller
@SuppressWarnings("unchecked")
public class AndroidUserAction extends AndroidAction {
    @Autowired
    UserService userService;

    private String register() {
        Map map = getRequestMap();
        SessionIdVO sessionIdVO = new SessionIdVO();

        try {
            FinanceCityUser financeCityUser = userService.register((String) map.get("mobile"), (String) map.get("password"), (String)map.get("username"));

            if (financeCityUser != null) {
                sessionIdVO.setSessionId(financeCityUser.getLoginSession());
                sessionIdVO.setId(financeCityUser.getID());
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
        catch (Exception e2) {
            e2.printStackTrace();
            ErrorManager.setError(sessionIdVO, ErrorManager.errorInvalidParameter);
        }

        setResult(sessionIdVO);

        return SUCCESS;
    }

    private String getUserVO() {
        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

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
        Map map = getRequestMap();

        BaseVO baseVO = new BaseVO();
        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

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
        try {

            String username = (String) map.get("username");
            String password = (String) map.get("password");
            if (username == null || password == null) {
                ErrorManager.setError(ret, ErrorManager.errorInvalidParameter);

                setResult(ret);
                return SUCCESS;
            }

            FinanceCityUser financeCityUser = userService.login(username, password);
            if (financeCityUser == null) {
                ErrorManager.setError(ret, ErrorManager.errorInvalidPassword);

                setResult(ret);
            } else {
                ErrorManager.setError(ret, ErrorManager.errorNormal);
                ret.setSessionId(financeCityUser.getLoginSession());
                ret.setId(financeCityUser.getID());
                setResult(ret);
            }
        }
        catch (Exception e) {
            ErrorManager.setError(ret, ErrorManager.errorInvalidParameter);
            setResult(ret);
        }

        return SUCCESS;
    }

    public String setTemperPrefer() {
        Map map = getRequestMap();
        BaseVO baseVO = new BaseVO();

        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            int amount = (Integer)map.get("amount");//投资金额
            String data = (String)map.get("date");//投资期限 yyyy-MM-dd
            String backDate = null;//预期赎回时间
            boolean ifPrepare = (Integer)map.get("ifPrepare") == 1;//是否做好意外大额支出准备
            boolean ifBifPre = false;//是否做好意外大额支出准备
            byte type = ((Integer)map.get("type")).byteValue();//配置大额支出准备的类型
            int backAmount = 0;//预期赎回的金额
            int insurance_amount = 0;//购买保险金额
            List<Integer> risks = (List<Integer>)map.get("risk");//风险承受能力
            List<Integer> income_rate = (List<Integer>)map.get("income");//预期收益率
            String preferType = (String)map.get("preferType");//偏好投资品种

            if (preferType == null || !(preferType.equals("fund") || preferType.equals("insurance"))) {
                throw new InvalidParametersException("preferType");
            }

            if (!ifPrepare) {
                ifBifPre = (Integer)map.get("ifBigPre") == 1;
                if (ifBifPre) {
                    if (type == 0) {
                        insurance_amount = (Integer)map.get("asAmount");
                    }
                    else if (type == 1) {
                        backAmount = (Integer)map.get("backAmount");
                        backDate = (String)map.get("backDate");
                    }
                }
            }

            UserTemperPrefer userTemperPrefer = new UserTemperPrefer();
            userTemperPrefer.setExpectedCapital(new BigDecimal(amount));
            userTemperPrefer.setEndTime(Date.valueOf(data));
            userTemperPrefer.setIfPrepedBigExpense(ifPrepare ? (byte) 1 : 0);
            userTemperPrefer.setIfConfigBigExpense(ifBifPre ? (byte) 1 : 0);
            userTemperPrefer.setExpenseType(type);
            userTemperPrefer.setRedeemTime(backDate == null ? null : Date.valueOf(backDate));
            userTemperPrefer.setMayRedeemAmount(new BigDecimal(backAmount));
            userTemperPrefer.setInsuranceAmount(new BigDecimal(insurance_amount));
            userTemperPrefer.setRiskToleranceMin(new BigDecimal(risks.get(0)));
            userTemperPrefer.setRiskToleranceMax(new BigDecimal(risks.get(1)));
            userTemperPrefer.setExpectedProfitMin(new BigDecimal(income_rate.get(0)));
            userTemperPrefer.setExpectedProfitMax(new BigDecimal(income_rate.get(1)));
            userTemperPrefer.setChosenProducts(preferType);

            userService.setUserTemperPrefer(userTemperPrefer, financeCityUser);

            ErrorManager.setError(baseVO, ErrorManager.errorNormal);
            return SUCCESS;
        } catch (NotLoginException n) {
            n.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorNotLogin);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorInvalidParameter);
            return SUCCESS;
        }
    }
}
