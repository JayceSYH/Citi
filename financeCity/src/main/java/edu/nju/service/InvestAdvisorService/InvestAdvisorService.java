package edu.nju.service.InvestAdvisorService;

import edu.nju.model.UserTemperPrefer;
import edu.nju.service.ExceptionsAndError.InvalidUserPreferenceException;
import edu.nju.service.ExceptionsAndError.NotAllConfigurationSetException;
import edu.nju.service.ExceptionsAndError.NotLoginException;
import edu.nju.service.POJO.InvestResult;
import edu.nju.service.POJO.PortfolioScores;
import edu.nju.service.POJO.SimpleTradeInfo;
import edu.nju.service.POJO.TradeInfoWithCheckCode;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.vo.TemperPreferVO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public interface InvestAdvisorService {
    /**
     * create investment portfolio
     * @param preference
     * @return invest result
     * @throws NotAllConfigurationSetException
     */
    List<TradeInfoWithCheckCode> createInvestmentPortFolio(UserTemperPrefer preference) throws NotAllConfigurationSetException, InvalidUserPreferenceException;

    PortfolioScores getPortfolioScore(List<SimpleTradeInfo> list);
}
