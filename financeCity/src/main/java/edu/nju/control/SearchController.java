package edu.nju.control;

import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;
import edu.nju.service.POJO.ProductVOFactory;
import edu.nju.service.POJO.SearchFilterFactory;
import edu.nju.service.SearchService.ProductFilter;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.ServiceManagerImpl;
import edu.nju.vo.ProductDetailVO;
import edu.nju.vo.ProductsDetailVO;
import edu.nju.vo.SearchResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/8/30.
 */
@Controller
@RequestMapping(value = "/")
public class SearchController {
    @RequestMapping(value = "product/info/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    ProductDetailVO findProductByID(@PathVariable Integer id) {
        SearchService searchService = ServiceManagerImpl.getInstance().getSearchService();
            ProductDetailVO productDetailVO = new ProductDetailVO();

            try {
                Product product = searchService.getProductByID(id);
                if (product != null) {
                    ErrorManager.setError(productDetailVO, ErrorManager.errorNormal);
                    productDetailVO.setType(product.getCategory().getChineseName());
                    productDetailVO.setData(product.getProduct());

                    return productDetailVO;
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ErrorManager.setError(productDetailVO, ErrorManager.errorNoSuchProduct);
        return productDetailVO;
    }

    @RequestMapping(value = "product/infos", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    ProductsDetailVO getIds(@RequestParam(value = "ids") String ids) {
        SearchService searchService = ServiceManagerImpl.getInstance().getSearchService();
        ProductsDetailVO productsDetailVO = new ProductsDetailVO();

        String idList_s[] = ids.split(":");
        int[] list = new int[idList_s.length];
        try {
            for (int i = 0; i < idList_s.length; ++i) {
                int id_i = Integer.valueOf(idList_s[i]);
                list[i] = id_i;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ErrorManager.setError(productsDetailVO, ErrorManager.errorInvalidParameter);
            return productsDetailVO;
        }

        Product[] products = searchService.getProductsByIds(list);
        if (products.length == 0) {
            ErrorManager.setError(productsDetailVO, ErrorManager.errorDateNotFound);
            return productsDetailVO;
        }

        Object[] data = new Object[products.length];

        for (int i = 0; i < data.length; ++i) {
            data[i] = products[i].getProduct();
        }
        productsDetailVO.setData(data);

        productsDetailVO.setData(new Object[0]);
        ErrorManager.setError(productsDetailVO, ErrorManager.errorNormal);
        return productsDetailVO;
    }

    @RequestMapping(value = "product/s", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    SearchResultVO searchProduct(@RequestBody Map map) {
        SearchService searchService = ServiceManagerImpl.getInstance().getSearchService();
        String key = (String)map.get("keyword");
        String type = (String)map.get("type");
        SearchResultVO searchResult = new SearchResultVO();

        try {
            ProductFilter productFilter = SearchFilterFactory.createFilter(type, map);
            List<Product> productList = searchService.searchProductsByKey(key);

            ProductVOFactory productVOFactory = new ProductVOFactory();
            for (Product product : productList) {
                if (productFilter.isChosen(product.getProduct())) {
                    productVOFactory.addProduct(product);
                }
            }

            searchResult.setData(productVOFactory.getResultList());
            return searchResult;
        }
        catch (InvalidParametersException i) {
            i.printStackTrace();
            ErrorManager.setError(searchResult,ErrorManager.errorInvalidParameter);
            return searchResult;
        }
    }

}