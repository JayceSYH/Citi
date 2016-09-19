package nju.financecity_android.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import nju.financecity_android.dao.InvestmentDao;
import nju.financecity_android.vo.ProductInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by coral on 16-9-13.
 */
public class UserInvestment {

    private UserInvestment() {}

    public static UserInvestment getCurrUserInvestment() {
        JSONObject jsonObject=new JSONObject();//value
//                UserSession user=UserSession.getCurrUser();
//                Log.i("test","user "+user.getSessionId());
        try {
//            jsonObjects.put("id", Integer.parseInt(user.getUserId()));//TODO 传递当前用户
//            jsonObjects.put("sessionId",user.getSessionId());
            jsonObject.put("id", 4);
            jsonObject.put("sessionId","911ba5154b102456b43298be84d38dd9");
        }catch(Exception e)
        {
            Log.i("test","user session or json exception");
            e.printStackTrace();
        }
        String rawResult = new InvestmentDao().sendPost(jsonObject);
        String result=new InvestmentDao().sendPost(jsonObject);
        JSONArray jValue=null;
        try{
            jValue=new JSONObject(result).getJSONArray("investmentPortfolioList");
        }catch(Exception e)
        {
            Log.i("test","asset value result exception");
            e.printStackTrace();
        }

        List<ProductInfo> productInfoList = new ArrayList<>();
        try {
            for (int i = 0; i < jValue.length(); i++) {
                JSONArray jproducts=(JSONArray)jValue.getJSONObject(i).get("productVOs");
                for(int j=0;j<jproducts.length();j++)
                {
                    ProductInfo info = new ProductInfo();
                    JSONObject product = jproducts.getJSONObject(j);
                    info.currPrice = Float.valueOf(product.get("currentValue").toString());
                    info.productName = product.get("name").toString();
                    info.productId = product.get("id").toString();
                    info.buyPrice = Float.valueOf(product.get("buyingValue").toString());
                    info.buy = product.get("buyingDate").toString();
                    info.expiration = product.get("endDate").toString();
                    info.type = product.get("type").toString();
                    productInfoList.add(info);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        UserInvestment investment = new UserInvestment();
        investment.mData = productInfoList;
        return investment;
    }

    public List<ProductInfo> getProductList() {
        return mData;
    }

    public List<ProductInfo> mData;
}
