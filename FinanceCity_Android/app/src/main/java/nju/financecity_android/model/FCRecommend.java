package nju.financecity_android.model;

import android.util.Log;

import nju.financecity_android.dao.RecommendDao;
import nju.financecity_android.vo.RecommendCombVO;
import nju.financecity_android.vo.RecommendSingleVO;
import nju.financecity_android.vo.RecommendVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by coral on 16-9-15.
 */
public class FCRecommend {

    public static RecommendVO getRecommend() {
        RecommendVO result = new RecommendVO();
        result.combinationList = new ArrayList<>();
        RecommendDao dao = new RecommendDao();
        Log.i("recommend","start getting Data");
        Map rawData = dao.readData();
        Log.i("recommend","rawData "+rawData);
        List recComb = null;
        if (rawData.containsKey("data")) {
            recComb = (List) rawData.get("data");
        } else {
            return null;
        }
        for (Object combination: recComb) {
            Map comb = (Map) combination;
            RecommendCombVO combVO = new RecommendCombVO();
            combVO.products = new ArrayList<>();
            if (!comb.containsKey("products")) {
                return null;
            }
            List prodList = (List) comb.get("products");
            for (Object rawProduct: prodList) {
                Map product = (Map) rawProduct;
                RecommendSingleVO singleVO = new RecommendSingleVO();
                singleVO.amount = Double.parseDouble(product.get("amount").toString());
                singleVO.id = Integer.parseInt(product.get("id").toString());
                singleVO.name=product.get("name").toString();
                singleVO.productType=product.get("productType").toString();
                singleVO.percentage=Double.parseDouble(product.get("amount").toString());
                combVO.products.add(singleVO);
            }
            combVO.yield_score=Integer.parseInt(comb.get("yield_score").toString());
            combVO.risk_score=Integer.parseInt(comb.get("risk_score").toString());
            combVO.flow_score=Integer.parseInt(comb.get("flow_score").toString());
            combVO.length_score=Integer.parseInt(comb.get("length_score").toString());
            combVO.total_amount=Double.parseDouble(comb.get("total_amount").toString());
//            combVO.checkCode=comb.get("checkCode").toString();TODO 需要这个嘛
            result.combinationList.add(combVO);
        }
        return result;
    }
}
