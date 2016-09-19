package edu.nju.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import edu.nju.service.Cache.ProductCache;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;
import edu.nju.service.POJO.ProductVOFactory;
import edu.nju.service.POJO.SearchFilterFactory;
import edu.nju.service.SearchService.ProductFilter;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
@Controller
public class SearchFilterAction_ extends AndroidAction {
    @Autowired
    SearchService searchService;

    @SuppressWarnings("unchecked")
    public String filter() {
        ActionContext context = ActionContext.getContext();

        String json_data = request.getParameter("data");

        Map map = new JsonUtil(json_data).getMap();

        if (map == null){
            ErrorManager.setError(request, ErrorManager.errorInvalidParameter);
            return SUCCESS;
        }

        System.out.println(map);

        String type = (String) map.get("type");
        String key = (String) map.get("keyword");
        String page_num_string = (String) map.get("page_num");

        String tag = getTag(type, key, (Map)map.get("options"));

        int page_num = Integer.parseInt(page_num_string) - 1;
        int page_length = 1;

        ProductVOFactory resultFactory = new ProductVOFactory();

        System.out.println("page_num:"+page_num);

        try {
            ProductCache cache = (ProductCache)session.get("searchCache");
            if (cache == null) {
                cache = new ProductCache();
            }

            List<Product> productList;
            if (cache.getCached(tag) == null) {
                ProductFilter productFilter = SearchFilterFactory.createFilter(type, map);
                productList = searchService.searchProductsByKey(key, type);
                for (Product product : productList) {
                    if (productFilter.isChosen(product.getProduct())) {
                        cache.cache(tag, product, null);
                    }
                }
                session.put("searchCache", cache);
            }
            else {
                productList = cache.getCached(tag);
            }


            for (int i = page_num * 8; i < (page_num + 1) * 8 && i < productList.size(); ++i) {
                Product product = productList.get(i);

                resultFactory.addProduct(product);
            }

            page_length = (cache.getCachedSize(tag) + 7) / 8;
            page_length = page_length == 0 ? 1 : page_length;

            System.out.println(resultFactory.getResultList().length);

            context.put("searchResultJSON", JSON.toJSON(resultFactory.getResultList()));
            context.put("searchResult", resultFactory.getResultList());

        } catch (InvalidParametersException e) {
            e.printStackTrace();
        }

        context.put("currentPage", page_num + 1);
        context.put("pageLength", page_length);

        return SUCCESS;
    }

    private String getTag(String type, String key, Map options) {
        return type + key + options.toString();
    }
}
