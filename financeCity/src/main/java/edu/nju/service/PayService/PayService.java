package edu.nju.service.PayService;

import edu.nju.model.PayWay;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.POJO.Payment;
import edu.nju.service.POJO.SimplePayWay;
import edu.nju.service.Sessions.FinanceCityUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public interface PayService {
    /**
     * @return if success
     */
    boolean bindCards(String cardNumber);

    /**
     * bind pay way
     * @param payWay .
     * @param financeCityUser .
     * @throws NotLoginException
     */
    void bindPayWay(SimplePayWay payWay, FinanceCityUser financeCityUser) throws NotLoginException;

    /**
     * get pay way list
     * @param financeCityUser .
     * @return pay way list
     * @throws NotLoginException
     */
    List<PayWay> getPayWayList(FinanceCityUser financeCityUser) throws NotLoginException;

    /**
     * pay for portfolio
     * @param checkCode .
     * @param financeCityUser .
     * @return
     */
    boolean payForPortfolio(String checkCode, FinanceCityUser financeCityUser);

    /**
     * redeem product
     * @return if success
     */
    boolean redeemProduct();
}
