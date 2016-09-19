package edu.nju.action;

import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.Utils.JsonUtil;
import edu.nju.vo.BaseVO;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@Controller
public class AndroidAction extends BaseAction {
    private Map requestMap;
    private BaseVO result;
    private String textResult;
    private FinanceCityUser financeCityUser;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        super.setServletRequest(request);
        financeCityUser = null;

        try {
            requestMap = new JsonUtil(request.getInputStream()).getMap();
            if (requestMap == null) {
                requestMap = new HashMap();
            }
            else {
                Integer id = (Integer)requestMap.get("id");
                String session = (String)requestMap.get("sessionId");
                if (id != null && session != null) {
                    financeCityUser = new FinanceCityUser();
                    financeCityUser.setLoginSession(session);
                    financeCityUser.setID(id);
                }
            }
        }
        catch (IOException io) {
            io.printStackTrace();
            requestMap = new HashMap();
        }
    }

    protected Map getRequestMap() {
        return requestMap;
    }

    public BaseVO getResult() {
        return result;
    }

    public void setResult(BaseVO result) {
        this.result = result;
    }

    public FinanceCityUser getUser() {
        return financeCityUser;
    }

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }
}
