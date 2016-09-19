package edu.nju.action;

import edu.nju.model.PayWay;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.PayService.PayService;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.vo.BaseVO;
import edu.nju.vo.PayWayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@Controller
public class AndroidPayAction extends AndroidAction {
    @Autowired
    PayService payService;

    private String bindPayWay() {
        BaseVO baseVO = new BaseVO();
        ErrorManager.setError(baseVO, ErrorManager.errorNormal);

        setResult(baseVO);

        return SUCCESS;
    }

    private String getPayWayList() {
        Map map = getRequestMap();

        PayWayVO payWayVO = new PayWayVO();

        try {

            FinanceCityUser financeCityUser = new FinanceCityUser();
            financeCityUser.setID((Integer)map.get("id"));
            financeCityUser.setLoginSession((String)map.get("session"));
            List<PayWay> payWayList = payService.getPayWayList(financeCityUser);

            if (payWayList == null) {
                ErrorManager.setError(payWayVO, ErrorManager.errorDataNotFound);
                setResult(payWayVO);

                return SUCCESS;
            }

            int size = payWayList.size();
            PayWayVO.PayWayResult[] payWayResults = new PayWayVO.PayWayResult[size];
            for (int i = 0; i < size; ++i) {
                payWayResults[i].setPid(payWayList.get(i).getId());
                payWayResults[i].setDescription(payWayList.get(i).getPayWay());
            }

            payWayVO.setDate(payWayResults);
            ErrorManager.setError(payWayVO, ErrorManager.errorNormal);

            setResult(payWayVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(payWayVO, ErrorManager.errorNotLogin);
            setResult(payWayVO);
        }

        return SUCCESS;
    }

    public String payMode() {
        String method = request.getMethod();

        if (method.equals("GET")) {
            return getPayWayList();
        }
        else if (method.equals("POST")) {
            return bindPayWay();
        }
        else {
            BaseVO baseVO = new BaseVO();
            ErrorManager.setError(baseVO, ErrorManager.errorUnhandledMethod);
        }

        return SUCCESS;
    }

    public String pay() {
        Map map = getRequestMap();
        BaseVO baseVO = new BaseVO();

        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            String checkCode = (String)map.get("checkCode");

            boolean success = payService.payForPortfolio(checkCode, financeCityUser);
            if (success) {
                ErrorManager.setError(baseVO, ErrorManager.errorNormal);
            }
            else {
                ErrorManager.setError(baseVO, ErrorManager.errorPaymentFailed);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorInvalidParameter);
        }

        setResult(baseVO);

        return SUCCESS;
    }
}