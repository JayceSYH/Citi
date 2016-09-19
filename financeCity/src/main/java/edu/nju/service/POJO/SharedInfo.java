package edu.nju.service.POJO;

import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.SearchService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
@Component
public class SharedInfo {
    private List<String> bankYieldList;
    private List<String> bondYieldList;
    private List<String> bondStateList;
    private List<String> fundTargetList;
    private List<String> fundStateList;

    private List<String> bankInstitutionList;
    private List<String> bondInstitutionList;
    private List<String> fundInstitutionList;
    private List<String> insuranceInstitutionList;

    @Autowired
    SharedInfo(SearchService searchService) {
        bankYieldList = searchService.getBankYieldType();
        bondYieldList = searchService.getBondYieldType();
        bondStateList = searchService.getBondStateType();
        fundTargetList = searchService.getFundTargetType();
        fundStateList = searchService.getFundState();

        bankInstitutionList = searchService.getInstitutionNameList(ProductCategoryManager.categoryBank);
        bondInstitutionList = searchService.getInstitutionNameList(ProductCategoryManager.categoryBond);
        fundInstitutionList = searchService.getInstitutionNameList(ProductCategoryManager.categoryFund);
        insuranceInstitutionList = searchService.getInstitutionNameList(ProductCategoryManager.categoryInsurance);
    }

    public List<String> getBankYieldList() {
        return bankYieldList;
    }

    public List<String> getBondYieldList() {
        return bondYieldList;
    }

    public List<String> getBondStateList() {
        return bondStateList;
    }

    public List<String> getFundTargetList() {
        return fundTargetList;
    }

    public List<String> getFundStateList() {
        return fundStateList;
    }

    public List<String> getBankInstitutionList() {
        return bankInstitutionList;
    }

    public List<String> getBondInstitutionList() {
        return bondInstitutionList;
    }

    public List<String> getFundInstitutionList() {
        return fundInstitutionList;
    }

    public List<String> getInsuranceInstitutionList() {
        return insuranceInstitutionList;
    }
}
