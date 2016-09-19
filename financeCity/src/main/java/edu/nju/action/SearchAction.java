package edu.nju.action;

import com.opensymphony.xwork2.ActionContext;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.ExceptionsAndError.ErrorManager;
import edu.nju.service.POJO.SharedInfo;
import edu.nju.service.SearchService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/4.
 */
@Controller
public class SearchAction extends BaseAction {
    @Autowired
    SearchService searchService;
    @Autowired
    SharedInfo sharedInfo;

    @SuppressWarnings("unchecked")
    public String getInstitutionList() {
        //TODO:set category
        ActionContext context = ActionContext.getContext();

        List<String> bankYieldList = sharedInfo.getBankYieldList();
        List<String> bondYieldList = sharedInfo.getBondYieldList();
        List<String> bondStateList = sharedInfo.getBondStateList();
        List<String> fundTargetList = sharedInfo.getFundTargetList();
        List<String> fundStateList = sharedInfo.getFundStateList();

        List<String> bankInstitutionList = sharedInfo.getBankInstitutionList();
        List<String> bondInstitutionList = sharedInfo.getBondInstitutionList();
        List<String> fundInstitutionList = sharedInfo.getFundInstitutionList();
        List<String> insuranceInstitutionList = sharedInfo.getInsuranceInstitutionList();

        context.put("bankInstitutionList", bankInstitutionList);
        context.put("bondInstitutionList", bondInstitutionList);
        context.put("fundInstitutionList", fundInstitutionList);
        context.put("insuranceInstitutionList", insuranceInstitutionList);

        System.out.println("bank:"+bankInstitutionList);
        System.out.println("bond:"+bondInstitutionList);
        System.out.println("fund:"+fundInstitutionList);
        System.out.println("insurance:"+insuranceInstitutionList);

        System.out.println("bankYieldList:"+bankYieldList);
        System.out.println("bondStateList:"+bondStateList);
        System.out.println("bondYieldList:"+bondYieldList);
        System.out.println("fundStateList:"+fundStateList);
        System.out.println("fundTargetList:"+fundTargetList);

        context.put("bankYieldList", bankYieldList);
        context.put("bondStateList", bondStateList);
        context.put("bondYieldList", bondYieldList);
        context.put("fundStateList", fundStateList);
        context.put("fundTargetList", fundTargetList);

        return SUCCESS;
    }
}
