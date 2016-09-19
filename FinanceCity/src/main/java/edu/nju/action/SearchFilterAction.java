package edu.nju.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;
import edu.nju.service.POJO.ProductVOFactory;
import edu.nju.service.POJO.SearchFilterFactory;
import edu.nju.service.SearchService.ProductFilter;
import edu.nju.service.SearchService.SearchService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

/**
 * Created by Hermit on 16/9/3.
 */
@Controller
public class SearchFilterAction extends BaseAction {
    @Autowired
    SearchService searchService;

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("error", 0);
        map.put("message", "none");

        Map<String, Object> bank = new HashMap<>();
        bank.put("pid", "0001");
        bank.put("yearly_income_rate", "0.0295");
        bank.put("product_type", "开放式净值型");
        bank.put("income_type", "保本浮动收益型");
        bank.put("initial_money", "50000");
        bank.put("open_date", "yyyy-MM-dd");
        bank.put("distributor_bank", "浦发银行");
        bank.put("distributor_institution", "浦发银行");

        ArrayList<Map> list = new ArrayList<>();
        list.add(bank);

        map.put("data", list);

        return map;
    }

    @SuppressWarnings("unchecked")
    public String filter() {
        ActionContext context = ActionContext.getContext();

        String json_data = request.getParameter("data");

        Map<String, Object> map = null;

        JSONObject jsonObject = JSONObject.fromObject(json_data);
        map = jsonToMap(jsonObject);


        if(map==null){
            return SUCCESS;
        }

        System.out.println(map);

        String type = (String) map.get("type");
        String key = (String) map.get("keyword");
        String page_num_string = (String) map.get("page_num");

        int page_num = Integer.parseInt(page_num_string) - 1;
        int page_length = 1;

        ProductVOFactory resultFactory = new ProductVOFactory();

        System.out.println("page_num:"+page_num);

        try {
            ProductFilter productFilter = SearchFilterFactory.createFilter(type, map);
            List<Product> productList = searchService.searchProductsByKey(key, type);

            int index = 0;

            for(int i=0;i<productList.size();i++){
                Product product = productList.get(i);
                if(productFilter.isChosen(product.getProduct())){
                    if(index>=page_num*8&&index<(page_num+1)*8){//保证相应页面8个产品上限
                        System.out.println("add Product");
                        resultFactory.addProduct(product);
                    }
                    System.out.println("index:"+index);
                    index++;
                }
                System.out.println("i:"+i);
            }

        page_length = productList.size()/8+1;
        page_length = resultFactory.getResultList().length/8+1;

        System.out.println(resultFactory.getResultList().length);

        context.put("searchResultJSON", JSON.toJSON(resultFactory.getResultList()));
        context.put("searchResult", resultFactory.getResultList());

    } catch (InvalidParametersException e) {
        e.printStackTrace();
    }

        context.put("currentPage", page_num+1);
        context.put("pageLength", page_length);

        return SUCCESS;
    }

    private Map<String, Object> jsonToMap(JSONObject jsonObject) {

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", jsonObject.get("keyword"));
        map.put("type", jsonObject.get("type"));
        map.put("page_num", jsonObject.get("page_num"));

        JSONObject options = jsonObject.getJSONObject("options");

        Map<String, Object> optMap = new HashMap<>();
        Iterator it = options.keys();
        while(it.hasNext()){
            String key = (String) it.next();
            Object object = options.get(key);
            System.out.println("key:"+object);
            if(object==null){
                String temp = null;
                optMap.put(key, temp);
            }else{
                optMap.put(key, object);
            }
        }

        map.put("options", optMap);

        return map;
    }
}
