package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.SearchService.SearchService;

import java.util.Map;

/**
 * Created by Sun YuHao on 2016/8/18.
 */
public interface AssetCategoryAllocator {
    /**
     * create allocation
     * @param userInfo , .
     */
    void createAllocation(UserTemperPrefer userInfo, SearchService searchService);

    /**
     * get category allocation
     * @param category .
     * @return .
     */
    AssetCategoryAllocation getCategoryAllocation(String category);
}
