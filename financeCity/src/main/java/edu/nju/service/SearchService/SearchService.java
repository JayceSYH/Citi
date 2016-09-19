package edu.nju.service.SearchService;

import edu.nju.model.CategoryIndex;
import edu.nju.model.CategoryMarketWeeklyHistory;
import edu.nju.model.CategoryRtrWeeklyHistory;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.ExceptionsAndError.DataNotFoundException;
import edu.nju.service.ExceptionsAndError.NoSuchProductException;
import edu.nju.service.POJO.NAVHistory;
import edu.nju.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public interface SearchService {
    /**
     * get product info
     * @param productName .
     * @return product info
     */
    List<Product> getProductByName(String productName) throws NoSuchProductException;

    /**
     * get product by id
     * @param ID .
     * @return product .
     * @throws NoSuchProductException .
     */
    Product getProductByID(Integer ID) throws NoSuchProductException;

    /**
     * search product by filter
     * @param filter .
     * @return products that are chosen
     */
    List<Product> searchProductByFilter(ProductFilter filter);

    /**
     * search products by keyword
     * @param keyWord .
     * @return products
     */
    List<Product> searchProductsByKey(String keyWord, String type);

    /**
     * search product by condition
     * @param type .
     * @param cond .
     * Note:object name is 'p'
     * @return products list
     */
    List<Product> searchProductsByCondition(String type, String cond);

    /**
     * get product list by order
     * Note: object name is 'p'
     * @param type .
     * @param order .
     * @return product list order by param
     */
    List<Product> getProductListByOrder(String type, String order);

    /**
     * search product by condition with order
     * @param type .
     * @param cond .
     * @param order .
     * @return
     */
    List<Product> searchProductsByConditionWithOrder(String type, String cond, String order);

    /**
     * get history data
     * @param productId .
     * @return history data
     */
    HistoryDataVO getHistoryDataVO(Integer productId);

    /**
     * get product detail
     * @param productID .
     * @return product detail
     */
    ProductDetailVO getProductDetailVO(Integer productID);

    /**
     * get project
     * @param projectID .
     * @return project
     */
    ProjectVO getProjectVO(Integer projectID);

    /**
     * search some object by min word
     * @param type .
     * @param word .
     * @return min word.
     */
    Object searchMin(String type, String word);

    /**
     * get category index
     * @return category index
     */
    CategoryIndex getCategoryIndex();

    /**
     * get products by ids
     * @param ids .
     * @return products
     */
    Product[] getProductsByIds(int[] ids);

    /**
     * get cost of products
     * @param id .
     * @param amount .
     * @return cost
     */
    double getCost(int[] id, int[] amount);

    /**
     * get fund value history
     * @param id .
     * @return .
     */
    NAVHistory[] getFundValueHistory(Integer id, Integer days) throws DataNotFoundException;

    NAVHistory[] getBankValueHistory(Integer id, Integer days) throws DataNotFoundException;

    List<CategoryRtrWeeklyHistory> getCategoryRtrWeeklyHistory(Integer days);

    CategoryMarketWeeklyHistory getCategoryMarket();

    /**
     * get institution list
     * @return institution list
     */
    List<String> getInstitutionNameList(String category);

    List<String> getBondYieldType();

    List<String> getBondStateType();

    List<String> getBankYieldType();

    List<String> getFundTargetType();

    List<String> getFundState();

    double[] getHS_300ByTime();
}
