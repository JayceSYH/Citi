package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.SearchService.SearchService;

/**
 * Created by Sun YuHao on 2016/8/18.
 */
public interface CategoryInvest {
    InvestResult invest(UserTemperPrefer userInfo, SearchService searchService, AssetCategoryAllocation allocation);
    String getCategoryName();
}
