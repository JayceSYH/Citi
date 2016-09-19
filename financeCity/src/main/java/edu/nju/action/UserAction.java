package edu.nju.action;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.ExceptionsAndError.*;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.UserService.UserService;
import edu.nju.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Sun YuHao on 2016/9/2.
 */
@Controller
public class UserAction extends BaseAction {
    //private final String default_after_login = "/jsp/asset.jsp";
    private final String default_after_login = "";
    private final String server = "http://localhost:8080";
    private final String DEFAULT = "default";

    @Autowired
    UserService userService;

    @SuppressWarnings("unchecked")
    public String register() {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String username = request.getParameter("nickname");

        if (mobile == null || password == null || username == null) {
            return ERROR;
        }
        try {
            FinanceCityUser financeCityUser = userService.register(mobile, password, username);
            session.put("user", financeCityUser);
            ErrorManager.setError(request, ErrorManager.errorNormal);
            return SUCCESS;
        }
        catch(UserAlreadyExistException e) {
            e.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorUserAlreadyExist);
            return ERROR;
        }
        catch (InvalidMobileException i) {
            i.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorInvalidMobile);
            return ERROR;
        }
        catch (InvalidPasswordException i2) {
            i2.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorInvalidPassword);
            return ERROR;
        }
    }

    @SuppressWarnings("unchecked")
    public String login() {
        String username = request.getParameter("mobile");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            ErrorManager.setError(request, ErrorManager.errorNormal);
            return ERROR;
        }

        try {
            FinanceCityUser financeCityUser = userService.login(username, password);

            session.put("user", financeCityUser);
            //setReferURL(financeCityUser);
            ErrorManager.setError(request, ErrorManager.errorNormal);
            //return SUCCESS;
            return DEFAULT;
        }
        catch (UserNotExistException u) {
            u.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorUserNotExist);
        }
        catch (InvalidPasswordException i) {
            i.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorInvalidPassword);
        }

        return ERROR;
    }
    
    

    @SuppressWarnings("unchecked")
    public String getUserVO() {

        FinanceCityUser financeCityUser = (FinanceCityUser) session.get("user");
        if (financeCityUser == null) {
            session.put("refer_url", getRefererURL(request));
            ErrorManager.setError(request, ErrorManager.errorNotLogin);
            return LOGIN;
        }

        UserVO userVO = userService.getUserVO(financeCityUser);
        request.setAttribute("userVO", userVO);

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String setUserInfoInStep2() {
        String birthday_y = request.getParameter("year");
        String birthday_m = request.getParameter("month");
        String birthday_d = request.getParameter("day");
        String from = request.getParameter("from");
        String income = request.getParameter("income");
        String expense = request.getParameter("outlay");

        if (birthday_d == null || birthday_m == null || birthday_y == null || from == null || income == null || expense == null) {
            ErrorManager.setError(request, ErrorManager.errorInvalidParameter);
            return ERROR;
        }

        try {
            FinanceCityUser financeCityUser = (FinanceCityUser)session.get("user");
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            int income_i = Integer.valueOf(income);
            int expense_i = Integer.valueOf(expense);
            boolean isUrben_b = from.equals("city");

            userService.modifyUserInfo(toDateFormat(birthday_y, birthday_m, birthday_d), income_i, isUrben_b, expense_i, financeCityUser);
            ErrorManager.setError(request, ErrorManager.errorNormal);
            return SUCCESS;
        }
        catch (NotLoginException n) {
            session.put("refer_url", getRefererURL(request));
            ErrorManager.setError(request, ErrorManager.errorNotLogin);
            return LOGIN;
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorInvalidParameter);
            return ERROR;
        }
    }

    public String bindPayWay() {
        ErrorManager.setError(request, ErrorManager.errorNormal);
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String setTemperPrefer() {
        String amount = request.getParameter("amount"); //投资金额
        String year = request.getParameter("year"); //投资期限
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String ifPrepare = request.getParameter("ifPrepare");//是否做好意外大额支出准备
        String ifBifPre = request.getParameter("ifBigPre");//是否需要专门配置大额支出准备
        String type = request.getParameter("type");//配置大额支出准备的类型
        String backAmount = request.getParameter("backAmount");//预期赎回的金额
        String backYear = request.getParameter("backYear");//预期赎回时间
        String backMonth = request.getParameter("backMonth");
        String backDay = request.getParameter("backDay");
        String asMount = request.getParameter("asMount");//购买保险金额
        String risk = request.getParameter("risk");//风险承受能力
        String income = request.getParameter("income");//预期收益率
        String preferType = request.getParameter("preferType");//偏好投资品种

        if (amount == null || year == null || month == null || day == null ||
                ifPrepare == null || risk == null || income == null || preferType == null) {
            ErrorManager.setError(request, ErrorManager.errorInvalidParameter);
            return ERROR;
        }

        try {
            FinanceCityUser financeCityUser = (FinanceCityUser)session.get("user");
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            int amount_i = Integer.valueOf(amount);
            String data = toDateFormat(year, month, day);
            boolean ifPrepare_b = Integer.valueOf(ifPrepare) == 1;
            boolean ifBifPre_b = false;
            byte type_b = 0;
            int backAmount_i = 0;
            String back_date = null;
            int insurance_amount = 0;
            int[] risks = new int[2];
            String[] risks_s = risk.split(";");
            risks[0] = Integer.valueOf(risks_s[0]);
            risks[1] = Integer.valueOf(risks_s[1]);

            int[] income_rate = new int[2];
            String[] incomes = income.split(";");
            income_rate[0] = Integer.valueOf(incomes[0]);
            income_rate[1] = Integer.valueOf(incomes[1]);

            String preferType_En = "";
            if (preferType.equals("fund")) {
                preferType_En = "fund";
            }
            else if (preferType.equals("insurance")) {
                preferType_En = "insurance";
            }
            else {
                throw new InvalidParametersException("getTemperPrefer");
            }

            if (!ifPrepare_b) {
                ifBifPre_b = Integer.valueOf(ifBifPre) == 0;
                if (ifBifPre_b) {
                    if (type.equals("0")) {
                        type_b = 0;
                        insurance_amount = Integer.valueOf(asMount);
                    }
                    else if (type.equals("1")) {
                        type_b = 1;
                        backAmount_i = Integer.valueOf(backAmount);
                        back_date = toDateFormat(backYear, backMonth, backDay);
                    }
                    else {
                        throw new InvalidParametersException("setTemperPrefer");
                    }
                }
            }

            UserTemperPrefer userTemperPrefer = new UserTemperPrefer();
            userTemperPrefer.setExpectedCapital(new BigDecimal(amount_i));
            userTemperPrefer.setEndTime(Date.valueOf(data));
            userTemperPrefer.setIfPrepedBigExpense(ifPrepare_b ? (byte)1 : 0);
            userTemperPrefer.setIfConfigBigExpense(ifBifPre_b ? (byte)1 : 0);
            userTemperPrefer.setExpenseType(type_b);
            userTemperPrefer.setRedeemTime(back_date == null ? null : Date.valueOf(back_date));
            userTemperPrefer.setMayRedeemAmount(new BigDecimal(backAmount_i));
            userTemperPrefer.setInsuranceAmount(new BigDecimal(insurance_amount));
            userTemperPrefer.setRiskToleranceMin(new BigDecimal(risks[0]));
            userTemperPrefer.setRiskToleranceMax(new BigDecimal(risks[1]));
            userTemperPrefer.setExpectedProfitMin(new BigDecimal(income_rate[0]));
            userTemperPrefer.setExpectedProfitMax(new BigDecimal(income_rate[1]));
            userTemperPrefer.setChosenProducts(preferType_En);

            userService.setUserTemperPrefer(userTemperPrefer, financeCityUser);

            ErrorManager.setError(request, ErrorManager.errorNormal);
            return SUCCESS;
        }
        catch (NotLoginException n) {
            session.put("refer_url", getRefererURL(request));
            ErrorManager.setError(request, ErrorManager.errorNotLogin);
            return LOGIN;
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(request, ErrorManager.errorInvalidParameter);
            return ERROR;
        }
    }

    private String toDateFormat(String year, String month, String day) {
        return year + "-" + month + "-" + day;
    }

    private String getRefererURL(HttpServletRequest request) {
        String url = request.getHeader("Referer");
        url = toNeededURI(url);
        return url;
    }

    public String getRefer_url() {
        return (String)session.get("refer_url");
    }

    @SuppressWarnings("unchecked")
    private void setReferURL(FinanceCityUser financeCityUser) {
        if ((!session.containsKey("refer_url")) || ((String)session.get("refer_url")).contains("signup")) {
            session.put("refer_url", default_after_login);
        }

        //Default
        session.put("refer_url", default_after_login);
    }

    
    @SuppressWarnings("unchecked")
	public String loginURL() {
    	 String refer_url = request.getHeader("Referer");
         refer_url = toNeededURI(refer_url);
    	 session.put("refer_url", refer_url);
    	 System.out.println(refer_url);
    	 return SUCCESS;
    }

    private String toNeededURI(String url) {
        if (url == null) {
            return null;
        }
        return url.replace(server, "");
    }
}
