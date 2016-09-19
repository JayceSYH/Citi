package edu.nju.service.InvestAdvisorService.Strategy.StrategyImpl;

import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWLogicalArray;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import edu.nju.model.CategoryIndex;
import edu.nju.model.CategoryMarketWeeklyHistory;
import edu.nju.model.CategoryRtrWeeklyHistory;
import edu.nju.model.UserTemperPrefer;
import edu.nju.service.CategoryAndProduct.Category;
import edu.nju.service.POJO.AssetCategoryAllocation;
import edu.nju.service.SearchService.SearchService;
import edu.nju.service.Utils.ARIMA;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.Utils.Arima.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import process3.ClassProcess3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sun YuHao on 2016/8/20.
 */
@Component
public class AssetCategoryAllocatorImpl implements AssetCategoryAllocator {
    @Autowired
    SearchService searchService;

    static public final int arimaHistory = 30;
    private Map<String, AssetCategoryAllocation> assetCategoryAllocationList;

    @SuppressWarnings("unchecked")
    @Override
    public void createAllocation(UserTemperPrefer userInfo, SearchService searchService) {
        init();

        double capital = userInfo.getExpectedCapital().doubleValue();

        CategoryInfo[] categoryInfos = calcuCategoryPortion(capital, userInfo, getCategoriesInfo(searchService, userInfo), searchService);

        for (CategoryInfo categoryInfo : categoryInfos) {
            AssetCategoryAllocation assetCategoryAllocation = new AssetCategoryAllocation();
            assetCategoryAllocation.setFlowCapital(categoryInfo.flowCapital);
            assetCategoryAllocation.setFreeCapital(categoryInfo.freeCapital);
            assetCategoryAllocationList.put(categoryInfo.category.getCategoryName(), assetCategoryAllocation);
        }
    }

    @Override
    public AssetCategoryAllocation getCategoryAllocation(String category) {
        return assetCategoryAllocationList.get(category);
    }

    private void init() {
        assetCategoryAllocationList = new HashMap<>();
    }

    private CategoryInfo[] calcuCategoryPortion(double capital, UserTemperPrefer userTemperPrefer,
                                                CategoryInfo[] categoryInfos, SearchService searchService) {
        int categoryNum = ProductCategoryManager.categoryNum - 1;
        boolean[][] sign = new boolean[1][categoryNum];
        double[][] E = new double[CategoryInfo.historyNum][categoryNum];
        double[][] W = new double[1][categoryNum];
        double[][] Exp = new double[1][categoryNum];
        double[][] LC = new double[1][categoryNum];
        double[][] E_hs = getHS_300();
        double rf = searchService.getCategoryIndex().getRiskFreeInterest().doubleValue();
        double min_insurance = userTemperPrefer.getInsuranceAmount().doubleValue();
        double money_start = capital - min_insurance;
        double volatility_min = userTemperPrefer.getRiskToleranceMin().doubleValue();
        double volatility_max = userTemperPrefer.getRiskToleranceMax().doubleValue();
        double volatility = (volatility_max + volatility_min) / 2;
        double money_flexble = userTemperPrefer.getMayRedeemAmount().doubleValue();
        int k = 0;

        int no = 0;
        for (int i = 0; i < ProductCategoryManager.categoryNum; ++i) {
            if (categoryInfos[i].category.equals(ProductCategoryManager.categoryInsurance)) {
                continue;
            }

            sign[1][no] = categoryInfos[no].chosen;
            k += categoryInfos[no].chosen ? 1 : 0;
            for (int j = 0; j < CategoryInfo.historyNum; ++j) {
                E[j][no] = categoryInfos[no].E[j];
            }
            W[1][no] = categoryInfos[no].W;
            Exp[1][no] = categoryInfos[no].Exp;
            LC[1][no] = categoryInfos[no].LC;
            ++no;
        }

        try {
            ClassProcess3 process3 = new ClassProcess3();
            MWLogicalArray Msign = new MWLogicalArray(sign);
            MWNumericArray Mk = new MWNumericArray(k, MWClassID.DOUBLE);
            MWNumericArray ME = new MWNumericArray(E, MWClassID.DOUBLE);
            MWNumericArray ME_hs = new MWNumericArray(E_hs, MWClassID.DOUBLE);
            MWNumericArray MW = new MWNumericArray(W, MWClassID.DOUBLE);
            MWNumericArray MExp = new MWNumericArray(Exp, MWClassID.DOUBLE);
            MWNumericArray MLC = new MWNumericArray(LC, MWClassID.DOUBLE);
            MWNumericArray Mrf = new MWNumericArray(rf, MWClassID.DOUBLE);
            MWNumericArray Mmoney_start = new MWNumericArray(money_start, MWClassID.DOUBLE);
            MWNumericArray Mmoney_flexble = new MWNumericArray(money_flexble, MWClassID.DOUBLE);
            MWNumericArray Mvolatility = new MWNumericArray(volatility, MWClassID.DOUBLE);

            Object[] result = process3.process3(1, Msign, Mk, ME, ME_hs, MW, MExp, MLC, Mrf, Mmoney_start, Mmoney_flexble, Mvolatility);
            MWNumericArray temp = (MWNumericArray)result[0];
            double[][] ret =  (double[][])temp.toDoubleArray();

            no = 0;
            for (int i  = 0; i < ProductCategoryManager.categoryNum; ++i) {
                if (categoryInfos[i].category.equals(ProductCategoryManager.categoryInsurance)) {
                    categoryInfos[i].freeCapital = min_insurance;
                }
                else {
                    categoryInfos[i].freeCapital = ret[0][no];
                    categoryInfos[i].flowCapital = ret[1][no];
                    ++no;
                }
            }

            return categoryInfos;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new CategoryInfo[0];
        }
    }

    private CategoryInfo[] getCategoriesInfo(SearchService searchService, UserTemperPrefer userInfo) {
        CategoryInfo[] categoryInfo = initCategoryInfo(userInfo, searchService);

        for (int i = 0; i < categoryInfo.length; ++i) {
            ARIMA arima = new ARIMA(categoryInfo[i].E);
            categoryInfo[i].Exp = arima.getReulst();
        }

        return categoryInfo;
    }

    @SuppressWarnings("unchecked")
    public CategoryInfo[] initCategoryInfo(UserTemperPrefer userInfo, SearchService searchService) {
        CategoryInfo[] categoryInfo = new CategoryInfo[ProductCategoryManager.categoryNum];
        CategoryIndex categoryIndex = searchService.getCategoryIndex();
        CategoryMarketWeeklyHistory categoryMarketWeeklyHistory = searchService.getCategoryMarket();
        List<CategoryRtrWeeklyHistory> categoryRtrWeeklyHistory = searchService.getCategoryRtrWeeklyHistory(CategoryInfo.historyNum);
        double Min_Inurance = userInfo.getInsuranceAmount().doubleValue();
        List<Category> categoryList = ProductCategoryManager.getCategoryList();

        for (int i  = 0; i < ProductCategoryManager.categoryNum; ++i) {
            //sign
            //TODO:set choose
            if (categoryInfo[i].category.equals(ProductCategoryManager.categoryInsurance)) {
                categoryInfo[i].chosen = Min_Inurance > 0;
            }
            else {
                categoryInfo[i].chosen = true;
            }
            //category
            categoryInfo[i].category = categoryList.get(i);
            //LC
            categoryInfo[i].LC = 0.5;

            if (categoryInfo[i].chosen) {
                //return rate sequence
                categoryInfo[i].E = getHistoryReturnRateSequence(categoryInfo[i].category, categoryRtrWeeklyHistory);
                if (categoryInfo[i].E.length == 0) {
                    return new CategoryInfo[0];
                }

                //Market value
                try {
                    categoryInfo[i].W = getMarketValue(categoryInfo[i].category.getCategoryName(), categoryMarketWeeklyHistory);
                }
                catch (NullPointerException n) {
                    n.printStackTrace();
                    categoryInfo[i].W = 0;
                }
            }
            else {
                categoryInfo[i].E = new double[CategoryInfo.historyNum];
            }

            ++i;
        }

        return categoryInfo;
    }

    private Double getMarketValue(String category, CategoryMarketWeeklyHistory categoryIndex) {
        try {
            Method getter = MethodUtils.getGetter(CategoryMarketWeeklyHistory.class, category);
            return ((BigDecimal)getter.invoke(categoryIndex)).doubleValue();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private double[][] getHS_300() {
        double[][] ret;

        double[] hs300 = searchService.getHS_300ByTime();
        if (hs300.length > 0) {
            ret = new double[1][];
            ret[0] = hs300;
            return ret;
        }
        else {
            return new double[0][];
        }
    }

    //TODO:sequence 周年化收益率
    private double[] getHistoryReturnRateSequence(Category category, List<CategoryRtrWeeklyHistory> list) {
        return null;
    }

    private class CategoryInfo {
        //TODO:change according to database
        static final int historyNum = 30;

        Category category;  //资产类型
        boolean chosen;  //是否选中
        double E[];      //资产的历史收益率序列
        //double Er;        //预期年化收益率
        double  W;     //资产的市值
        double  Exp;     //专家预期收益观点
        double  LC;      //观点的置信度
        double  flowCapital; //流动资金
        double  freeCapital; //固定投资资金
    }

    static public void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        int categoryNum = 4;
        boolean[] sign = new boolean[categoryNum];
        sign[0] = true;sign[1] = true;sign[2] = true;sign[3] = true;
        double[][] E = new double[10][4];
        double[][] W = new double[1][4];
        double[][] Exp = new double[1][4];
        double[][] LC = new double[1][4];
        for (int i =0; i < 4; ++i) {
            for (int j = 0; j < 10; ++j) {
                E[j][i] = Math.random();
            }
        }
        double[][] E_hs = new double[1][4];

        for (int i = 0; i < 4; ++i) {
            W[0][i] = Math.random();
            Exp[0][i] = Math.random();
            LC[0][i] = Math.random();
            E_hs[0][i] = Math.random();
        }
        double rf = 0.03;
        double money_start = 100;
        double volatility_min = 0;
        double volatility_max = 0.2;
        double volatility = (volatility_max + volatility_min) / 2;
        double[] money_flexble = new double[1];
        money_flexble[0] = 90;
        int k = 4;




        try {
            ClassProcess3 process3 = new ClassProcess3();
            MWLogicalArray Msign = new MWLogicalArray(sign);
            MWNumericArray Mk = new MWNumericArray(k, MWClassID.DOUBLE);
            MWNumericArray ME = new MWNumericArray(E, MWClassID.DOUBLE);
            MWNumericArray ME_hs = new MWNumericArray(E_hs, MWClassID.DOUBLE);
            MWNumericArray MW = new MWNumericArray(W, MWClassID.DOUBLE);
            MWNumericArray MExp = new MWNumericArray(Exp, MWClassID.DOUBLE);
            MWNumericArray MLC = new MWNumericArray(LC, MWClassID.DOUBLE);
            MWNumericArray Mrf = new MWNumericArray(rf, MWClassID.DOUBLE);
            MWNumericArray Mmoney_start = new MWNumericArray(money_start, MWClassID.DOUBLE);
            MWNumericArray Mmoney_flexble = new MWNumericArray(money_flexble, MWClassID.DOUBLE);
            MWNumericArray Mvolatility = new MWNumericArray(volatility, MWClassID.DOUBLE);

            Object[] result = process3.process3(1, Msign, Mk, ME, ME_hs, MW, MExp, MLC, Mrf, Mmoney_start, Mmoney_flexble, Mvolatility);
            MWNumericArray temp = (MWNumericArray)result[0];
            System.out.print(temp);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
