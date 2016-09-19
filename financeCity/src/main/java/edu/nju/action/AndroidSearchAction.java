package edu.nju.action;

import edu.nju.model.ProductBank;
import edu.nju.model.ProductFund;
import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.ExceptionsAndError.DataNotFoundException;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.ExceptionsAndError.NoSuchProductException;
import edu.nju.service.POJO.NAVHistory;
import edu.nju.service.POJO.ProductVOFactory;
import edu.nju.service.POJO.SearchFilterFactory;
import edu.nju.service.SearchService.ProductFilter;
import edu.nju.service.SearchService.SearchService;
import edu.nju.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/3.
 */
@Controller
public class AndroidSearchAction extends AndroidAction {
    @Autowired
    SearchService searchService;

    public String findProductById() {
        Map map = getRequestMap();

        ProductDetailVO productDetailVO = new ProductDetailVO();

        try {
            int id = (Integer)map.get("id");
            Integer days = (Integer)map.get("days");

            Product product = searchService.getProductByID(id);
            if (product != null) {
                ErrorManager.setError(productDetailVO, ErrorManager.errorNormal);
                productDetailVO.setType(product.getCategory().getChineseName());
                productDetailVO.setData(product.getProduct());
                if (product.getCategory().belongTo(ProductCategoryManager.categoryBank) &&
                        ProductCategoryManager.ifNetBankProduct((ProductBank)product.getProduct())) {
                    productDetailVO.setHistory(searchService.getBankValueHistory(product.getID(), days));
                }
                else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
                    productDetailVO.setHistory(searchService.getFundValueHistory(product.getID(), days));
                }

                setResult(productDetailVO);
                return SUCCESS;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ErrorManager.setError(productDetailVO, ErrorManager.errorNoSuchProduct);
        setResult(productDetailVO);
        return SUCCESS;
    }

    public String getProductsByIds() {
        Map map = getRequestMap();

        ProductsDetailVO productsDetailVO = new ProductsDetailVO();

        try {
            String ids = (String) map.get("ids");
            String idList_s[] = ids.split(":");
            int[] list = new int[idList_s.length];
            for (int i = 0; i < idList_s.length; ++i) {
                int id_i = Integer.valueOf(idList_s[i]);
                list[i] = id_i;
            }

            Product[] products = searchService.getProductsByIds(list);
            if (products.length == 0) {
                ErrorManager.setError(productsDetailVO, ErrorManager.errorDataNotFound);
                setResult(productsDetailVO);

                return SUCCESS;
            }

            ProductDetailVO[] data = new ProductDetailVO[products.length];

            for (int i = 0; i < data.length; ++i) {
                data[i].setData(products[i].getProduct());
                data[i].setType(products[i].getName());
            }
            productsDetailVO.setData(data);

            productsDetailVO.setData(null);
            ErrorManager.setError(productsDetailVO, ErrorManager.errorNormal);
            setResult(productsDetailVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(productsDetailVO, ErrorManager.errorInvalidParameter);
            setResult(productsDetailVO);
        }

        return SUCCESS;
    }

    public String searchProducts() {
        Map map = getRequestMap();

        SearchResultVO searchResult = new SearchResultVO();

        try {
            String key = (String)map.get("keyword");
            String type = (String)map.get("type");
            Byte order = (Byte) map.get("order");
            String searchType;

            ProductFilter productFilter = SearchFilterFactory.createFilter(type, map);
            Category category = ProductCategoryManager.getCategoryByName(type);
            if (category == null) {
                searchType = null;
            }
            else {
                searchType = category.getBiggerCategory().getCategoryName();
            }

            List<Product> productList = searchService.searchProductsByKey(key, searchType);

            //set order
            if (type.equals(ProductCategoryManager.categoryFund) && order != null) {
                if (order == 1) {
                    productList.sort((p1, p2) -> ((ProductFund) p2.getProduct()).getYearlyRtnRate().compareTo(((ProductFund) p1.getProduct()).getYearlyRtnRate()));
                }
                else if (order == 2) {
                    productList.sort((p1, p2) -> ((ProductFund) p1.getProduct()).getYearlyRtnRate().compareTo(((ProductFund) p2.getProduct()).getYearlyRtnRate()));
                }
            }

            ProductVOFactory productVOFactory = new ProductVOFactory();
            if (productList != null) {
                for (Product product : productList) {
                    if (productFilter.isChosen(product.getProduct())) {
                        productVOFactory.addProduct(product);
                    }
                }
            }

            searchResult.setData(productVOFactory.getResultList());
            setResult(searchResult);
        }
        catch (Exception i) {
            i.printStackTrace();
            ErrorManager.setError(searchResult,ErrorManager.errorInvalidParameter);
            setResult(searchResult);
        }

        return SUCCESS;
    }

    public String getInstitutionList() {
        Map map = getRequestMap();
        InstitutionListVO institutionListVO = new InstitutionListVO();

        try {
            String category = (String)map.get("type");
            List<String> institutions = searchService.getInstitutionNameList(category);

            if (institutions == null) {
                ErrorManager.setError(institutionListVO, ErrorManager.errorDataNotFound);
            }
            else {
                institutionListVO.setInstitutions(institutions);
                ErrorManager.setError(institutionListVO, ErrorManager.errorNormal);
            }
        }
        catch (Exception e) {
            ErrorManager.setError(institutionListVO, ErrorManager.errorInvalidParameter);
        }

        setResult(institutionListVO);
        return SUCCESS;
    }

    public String getFundValueHistory() {
        Map map = getRequestMap();
        FundHistoryVO fundHistoryVO = new FundHistoryVO();

        try {
            Integer id = (Integer)map.get("id");
            Integer days = (Integer) map.get("days");
            if (days == null) {
                days = Integer.MAX_VALUE;
            }

            NAVHistory[] fundValueHistories = searchService.getFundValueHistory(id, days);
            ErrorManager.setError(fundHistoryVO, ErrorManager.errorNormal);
            fundHistoryVO.setData(fundValueHistories);
        }
        catch (DataNotFoundException n) {
            n.printStackTrace();
            ErrorManager.setError(fundHistoryVO, ErrorManager.errorDataNotFound);
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(fundHistoryVO, ErrorManager.errorInvalidParameter);
        }

        setResult(fundHistoryVO);
        return SUCCESS;
    }

}
