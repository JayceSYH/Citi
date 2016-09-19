package edu.nju.service.InvestAdvisorService.Strategy;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.POJO.SimpleTradeInfo;
import edu.nju.service.SearchService.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/10.
 */
@Service
public interface InvestStrategy {
    List<List<SimpleTradeInfo>> createInvestmentPortfolio(UserTemperPrefer preference, SearchService searchService);
}
