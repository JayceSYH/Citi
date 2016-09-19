package edu.nju.service.ExceptionsAndError;

import edu.nju.vo.BaseVO;
import org.python.antlr.ast.Str;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sun YuHao on 2016/8/29.
 */
public class ErrorManager {
    static public final int errorNormal = 0;
    static public final int errorNotLogin = 1;
    static public final int errorDataNotFound = 2;
    static public final int errorInvalidPassword = 3;
    static public final int errorRegisterFailed = 4;
    static public final int errorNoSuchProduct = 5;
    static public final int errorInvalidParameter = 6;
    static public final int errorUserAlreadyExist = 7;
    static public final int errorUserNotExist = 8;
    static public final int errorInvalidMobile = 9;
    static public final int errorLoginFailed = 10;
    static public final int errorInnerDataError = 11;
    static public final int errorUnhandledMethod = 12;
    static public final int errorNoSuchInvestmentPortfolio = 13;
    static public final int errorNoSuchOrder = 14;
    static public final int errorPaymentFailed = 15;
    static public final int errorUserInfoNotSet = 16;
    static public final int errorInvalidUserPreference = 17;
    static public final int errorNothingToRedeem = 18;

    static private String[] errorDescription;
    static private String[] errorDescriptionCH;

    static {
        errorDescription = new String[] {
                "",
                "Not Login",
                "No Data Found",
                "Invalid Password",
                "Register Failed",
                "No Such Product",
                "Invalid Parameters",
                "User Already Exist",
                "User Not Exist",
                "Invalid Phone Number",
                "Login Failed",
                "Inner Data Error",
                "Unhandled Method",
                "No Such Investment Portfolio",
                "No Such Order",
                "Payment Failed",
                "User Info Not Set",
                "Invalid User Preference",
                "Nothing To Redeem"
        };
        errorDescriptionCH = new String[] {
                "",
                "未登录",
                "未找到相关数据",
                "无效的密码",
                "注册失败",
                "找不到满足条件的产品",
                "无效的参数",
                "该用户已存在",
                "该用户不存在",
                "无效的手机号",
                "登录失败",
                "内部数据错误",
                "未处理的提交方式",
                "该投资组合不存在",
                "不存在的订单",
                "交易失败",
                "用户信息不完整",
                "无效的用户偏好信息",
                "没有可赎回的产品"
        };
    }

    static private String getDescription(int i) {
        return errorDescription[i];
    }
    static private String getDescriptionCH(int i) { return errorDescriptionCH[i]; }

    static public void setError(BaseVO baseVO, int error) {
        baseVO.setError(error);
        baseVO.setMessage(ErrorManager.getDescription(error));
    }

    static public void setError(HttpServletRequest request, int error) {
        request.setAttribute("error", error);
        request.setAttribute("message", ErrorManager.getDescription(error));
    }
}
