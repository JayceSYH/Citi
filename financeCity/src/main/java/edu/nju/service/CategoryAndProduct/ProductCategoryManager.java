package edu.nju.service.CategoryAndProduct;

import edu.nju.model.ProductBank;
import edu.nju.model.ProductBond;
import edu.nju.model.ProductFund;
import edu.nju.model.ProductInsurance;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/8/14.
            */
    @Service
    public class ProductCategoryManager {
    static public final String categoryFund = "Fund";
    static public final String categoryBond = "Bond";
    static public final String categoryInsurance = "Insurance";
    static public final String categoryBank = "Bank";
    static private int fundBase = 4;
    static public final int categoryNum = 13;
    static private List<Category> categoryList;
    static private final int serialNumberSize = 10000000;
    static private final String[] bondTypes = {
            "记账国债",
            "凭证国债",
            "电子式国债",
            "金融债",
            "企业债",
            "其他债"
    };//TODO：国债都放到电子式国债
    static private final String[] fundTypEn = {
            "stock",
            "bond",
            "currency",
            "blend",
            "etf",
            "lof",
            "QDll",
            "index"
    };
    static private final String[] fundTypeCH = {
            "股票型",
            "债券型",
            "货币型",
            "混合型",
            "ETF",
            "LOF",
            "QDll",
            "指数型",
    };
    static private final String[] bondInterestType = {
        "零息债", "附息债", "贴现债"
    };//TODO：固定利率和附息债

    static private final String[] bondStateType = {
        "发行中", "已售罄", "未发行"
    };

    static private final String[] fundStateType = {
        "申购中", "认购中", "已关闭"
    };

    static private final String[] bankYieldType = {
        "保证收益型", "保本浮动收益型", "非保本浮动收益型"
    };

    static {
        //init
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryImpl("Bond", null, null, "债券"));
        categoryList.add(new CategoryImpl("Bank", null, null, "理财产品"));
        categoryList.add(new CategoryImpl("Insurance", null, null, "保险"));
        categoryList.add(new CategoryImpl("Fund", null, null, "基金"));
        for (int i = 0; i < fundTypEn.length; ++i) {
            categoryList.add(new CategoryImpl(fundTypEn[i] + "Fund", categoryList.get(3), i, fundTypeCH[i] + "基金"));
        }
    }

    static public List<Category> getCategoryList() {
        return categoryList;
    }

    static public List<String> getBankYieldType() {
        return Arrays.asList(bankYieldType);
    }

    static public Category getCategoryByID(Integer ID) {
        int index = ID / serialNumberSize;
        if (index >= 0 && index < categoryList.size()) {
            return categoryList.get(index);
        }

        return null;
    }

    static public Category getCategoryByName(String categoryName) {
        for (Category category : categoryList) {
            if (category.equals(categoryName)) {
                return category;
            }
        }

        return null;
    }

    static public Integer getProductItemIndex(Integer ID) {
        return ID % serialNumberSize;
    }

    static public Integer generateProductID(int itemId, String categoryName) {
        for (int i = 0; i < categoryList.size(); ++i) {
            if (categoryList.get(i).equals(categoryName)) {
                return i * serialNumberSize + itemId;
            }
        }

        return null;
    }

    static public Integer generateProductID(Object product) {
       Category category = getProductCategory(product);

        try {
            Class cls = product.getClass();
            Method method = cls.getMethod("getId");
            int id = (Integer)method.invoke(product);
            return generateProductID(id, category.getCategoryName());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public String getBondType(Product product) {
        if (!product.getCategory().belongTo(categoryBond)) {
            return null;
        }

        ProductBond productBond = (ProductBond) product.getProduct();
        return bondTypes[productBond.getType()];
    }

    public static List<String> getBondStateType() {
        return Arrays.asList(bondStateType);
    }

    static public String getBondInterestType(Product product) {
        if (!product.getCategory().belongTo(categoryBond)) {
            return null;
        }

        ProductBond productBond = (ProductBond) product.getProduct();
        return bondInterestType[productBond.getCouponType()];
    }

    static public List<String> getBondInterestTypeList() {
        return Arrays.asList(bondInterestType);
    }



    static public String getBondType(ProductBond productBond) {
        return bondTypes[productBond.getType()];
    }

    static public String getBondInterestType(ProductBond productBond) {
        return bondInterestType[productBond.getCouponType()];
    }

    static public String getUnit(String category) {
        if (belongTo(category, ProductCategoryManager.categoryBank)) {
            return "元";
        }
        else if (belongTo(category, ProductCategoryManager.categoryBond)) {
            return "手";
        }
        else if (belongTo(category, ProductCategoryManager.categoryFund)) {
            return "元";
        }
        else if (belongTo(category, ProductCategoryManager.categoryInsurance)) {
            return "份";
        }

        return null;
    }

    static public String getBankIncomeTypeInChinese(ProductBank bank) {
        if (bank.getIncomeType() == null) {
            return null;
        }
        switch (bank.getIncomeType()) {
            case 0:
                return "保本收益型";
            case 1:
                return "保本浮动收益型";
            case 2:
                return "非保本浮动收益型";
            default:
                return "";
        }
    }

    static public String getBankType(ProductBank bank) {
        if (bank.getIfClose() == null) {
            return null;
        }
        return (ifClosedBankProduct(bank) ? "封闭式" : "开放式") + (ifNetBankProduct(bank) ? "净值型" : "非净值型");
    }

    static public boolean ifNetBankProduct(ProductBank bank) {
        if (bank.getIfNavType() == null) {
            return false;
        }
        return bank.getIfNavType() == 1;
    }

    static public boolean ifClosedBankProduct(ProductBank bank) {
        if (bank.getIfClose() == null) {
            return false;
        }
        return bank.getIfClose() == 1;
    }

    static public boolean ifCloseFundProduct(ProductFund fund) {
        if (fund.getOperationMode() == null) {
            return false;
        }
        return fund.getOperationMode() == 1;
    }

    static public String getProductRedeemDate(Product product) {
        if (product.getCategory().belongTo(ProductCategoryManager.categoryBond)){
            return "可以赎回";
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryBank)) {
            if (ifClosedBankProduct((ProductBank)product.getProduct())) {
                return "不能赎回";
            }
            else {
                return "可以赎回";
            }
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryFund)) {
            if (ifCloseFundProduct((ProductFund)product.getProduct())) {
                return "不能赎回";
            }
            else {
                return "可以赎回";
            }
        }
        else if (product.getCategory().belongTo(ProductCategoryManager.categoryInsurance)) {
            return "不能赎回";
        }
        else {
            return null;
        }
    }

    static public Category getFundCategory(Byte fund_index) {
        try {
            if (fund_index == null) {
                fund_index = -1;
            }

            return getCategoryList().get(fundBase + fund_index);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getFundStateType() {
        return Arrays.asList(fundStateType);
    }

    static public Integer getFundTypeIndex(String category) {
        for (int i = 0; i < fundTypEn.length; ++i) {
            if (category.startsWith(fundTypEn[i])) {
                return i;
            }
        }

        return null;
    }

    public static List<String> getFundTypeCH() {
        return Arrays.asList(fundTypeCH);
    }

    static public Category getProductCategory(Object product) {
        Category category;

        try {
            if (product instanceof ProductFund) {
                ProductFund productFund = (ProductFund)product;

                Byte fund_index = productFund.getCategory();
                if (fund_index == null) {
                    fund_index = -1;
                }

                category = getCategoryList().get(fundBase + fund_index);
            } else if (product instanceof ProductBank) {
                category = getCategoryByName(ProductCategoryManager.categoryBank);
            } else if (product instanceof ProductBond) {
                category = getCategoryByName(ProductCategoryManager.categoryBond);
            } else if (product instanceof ProductInsurance) {
                category = getCategoryByName(ProductCategoryManager.categoryInsurance);
            } else {
                throw new InvalidParametersException("generateProductID");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            category = null;
        }

        return category;
    }

    static public String getFundState(ProductFund productFund) {
        if (productFund.getState() == null) {
            return null;
        }

        switch (productFund.getState()) {
            case 0:
                return "申购中";
            case 1:
                return "认购中";
            case 2:
                return "已关闭";
            default:
                return null;
        }
    }

    static public String getInsurancePayType(ProductInsurance productInsurance) {
        Byte type = productInsurance.getPayType();
        if (type == null) {
            type = 0;
        }

        switch (type) {
            case 0:
                return "一次缴清";
            case 1:
                return "分期缴清";
            default:
                return "";
        }
    }

    static public boolean subCategoryOf(Category category1, String category2) {
        return category1.getCategoryName().endsWith(category2) && !(category1.equals(category2));
    }

    static public boolean subCategoryOf(Category category1, Category category2) {
        return category1.getCategoryName().endsWith(category2.getCategoryName()) && !category1.equals(category2);
    }

    static public boolean belongTo(String category1, String category2) {
        return category1.endsWith(category2);
    }

    static public String getChineseName(String category) {
        return getCategoryByName(category).getChineseName();
    }
}
