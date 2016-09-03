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
    static public final int errorDateNotFound = 2;
    static public final int errorInvalidPassword = 3;
    static public final int errorRegisterFailed = 4;
    static public final int errorNoSuchProduct = 5;
    static public final int errorInvalidParameter = 6;
    static public final int errorUserAlreadyExist = 7;
    static public final int errorUserNotExist = 8;
    static public final int errorInvalidMobile = 9;
    static public final int errorLoginFailed = 10;
    static public final int errorInnerDataError = 11;

    static private String[] errorDescreption;
    static private String[] errorDescreptionCH;

    static {
        errorDescreption = new String[] {
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
                "Inner Data Error"
        };
        errorDescreptionCH = new String[] {
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
                "内部数据错误"
        };
    }

    static private String getDescription(int i) {
        return errorDescreption[i];
    }
    static private String getDescriptionCH(int i) { return errorDescreptionCH[i]; }

    static public void setError(BaseVO baseVO, int error) {
        baseVO.setError(error);
        baseVO.setMessage(ErrorManager.getDescription(error));
    }

    static public void setError(HttpServletRequest request, int error) {
        request.setAttribute("error", error);
        request.setAttribute("message", ErrorManager.getDescription(error));
    }
}
