package edu.nju.action;

import edu.nju.service.ExceptionsAndError.*;
import edu.nju.service.POJO.SimpleTradeInfo;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.service.TradeService.TradeService;
import edu.nju.vo.BaseVO;
import edu.nju.vo.OrderResultVO;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@Controller
public class AndroidBuyAction extends AndroidAction {
    @Autowired
    SearchService searchService;
    @Autowired
    TradeService tradeService;

    @SuppressWarnings("unchecked")
    public String order() {
        Map map = getRequestMap();
        OrderResultVO orderResultVO = new OrderResultVO();

        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            List<Map> productList = (List<Map>)map.get("product_list");
            if (productList == null || productList.size() == 0) {
                throw new InvalidParametersException("product_list");
            }

            List<SimpleTradeInfo> simpleTradeInfoList = new ArrayList<>();
            for (Map tradeMap : productList) {
                int pid = (Integer)tradeMap.get("pid");
                double amount = getDouble(tradeMap.get("amount"));

                SimpleTradeInfo simpleTradeInfo = new SimpleTradeInfo();
                simpleTradeInfo.setAmount(amount);
                simpleTradeInfo.setProductId(pid);

                simpleTradeInfoList.add(simpleTradeInfo);
            }

            orderResultVO = tradeService.buyProduct(simpleTradeInfoList, financeCityUser);
            ErrorManager.setError(orderResultVO, ErrorManager.errorNormal);
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            ErrorManager.setError(orderResultVO, ErrorManager.errorNotLogin);
        }
        catch (NoSuchProductException n) {
            n.printStackTrace();
            ErrorManager.setError(orderResultVO, ErrorManager.errorNoSuchProduct);
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(orderResultVO, ErrorManager.errorInvalidParameter);
        }

        setResult(orderResultVO);

        return SUCCESS;
    }

    public String redeem() {
        Map map = getRequestMap();
        BaseVO baseVO = new BaseVO();

        try {
            FinanceCityUser financeCityUser = getUser();
            if (financeCityUser == null) {
                throw new NotLoginException();
            }

            String checkCode = (String)map.get("checkCode");
            Integer pid = (Integer)map.get("pid");
            if (checkCode == null || pid == null) {
                throw new InvalidParametersException("checkCode | pid");
            }

            if (tradeService.redeemProduct(checkCode, pid, financeCityUser)) {
                ErrorManager.setError(baseVO, ErrorManager.errorNormal);
            }
            else {
                ErrorManager.setError(baseVO, ErrorManager.errorInnerDataError);
            }
        }
        catch (NotLoginException n) {
            n.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorNotLogin);
        }
        catch (InvalidParametersException i) {
            i.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorInvalidParameter);
        }
        catch (NothingToRedeemException n) {
            n.printStackTrace();
            ErrorManager.setError(baseVO, ErrorManager.errorNothingToRedeem);
        }

        setResult(baseVO);
        return SUCCESS;
    }

    private Double getDouble(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof Integer) {
            return Double.valueOf((Integer)object);
        }
        else if (object instanceof Double) {
            return (Double)object;
        }

        return null;
    }
}
