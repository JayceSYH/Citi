package nju.financecity_android.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coral on 16-9-15.
 */
public class RecommendVO {
    public List<RecommendCombVO> combinationList;
    public RecommendVO(){
        combinationList=new ArrayList<RecommendCombVO>();
    }
    public RecommendVO(List<RecommendCombVO> combinationList) {
        this.combinationList = combinationList;
    }
}
