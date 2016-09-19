package edu.nju.service.AssetManagementService;

import edu.nju.model.InvestedProducts;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.POJO.AssetValue;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.vo.CurrentInvestmentVO;
import edu.nju.vo.ProductVO;
import edu.nju.vo.TradeHistoryListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public interface AssetManagementService {

    /**
     * get InvestProductVOList
     * @return investProduct
     */
    CurrentInvestmentVO getInvestProductVOList(FinanceCityUser financeCityUser);

    /**
     * get trad history
     * @return trad history
     * @throws NotLoginException
     */
    TradeHistoryListVO getTradeHistory(FinanceCityUser financeCityUser);

    /**
     * update asset value
     * @param financeCityUser .
     * @throws NotLoginException
     */
    void updateAssetValue(FinanceCityUser financeCityUser) throws NotLoginException;

    /**
     * get asset value history
     * @param financeCityUser .
     * @param days .
     * @return .
     * @throws NotLoginException
     */
    List<AssetValue> getAssetValueHistory(FinanceCityUser financeCityUser, int days) throws NotLoginException;

    /**
     * get invested products
     * @param checkCode .
     * @param financeCityUser .
     * @return
     * @throws NotLoginException
     */
    List<ProductVO> getInvestedProduct(String checkCode, FinanceCityUser financeCityUser) throws NotLoginException;
}
