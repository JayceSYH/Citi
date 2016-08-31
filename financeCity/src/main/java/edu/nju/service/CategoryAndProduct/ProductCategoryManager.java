package edu.nju.service.CategoryAndProduct;

import edu.nju.model.ProductBank;
import edu.nju.model.ProductBond;
import edu.nju.model.ProductFund;
import org.python.antlr.ast.Str;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    };
    static private final String[] fundTypEn = {
            "stock",
            "bond",
            "currency",
            "blend",
            "etf",
            "lof",
            "fof",
            "QDll",
            "index",
            "guaranteed"
    };
    static private final String[] fundTypeCH = {
            "股票型",
            "债券型",
            "货币型",
            "混合型",
            "ETF",
            "LOF",
            "FOF",
            "QDll",
            "指数型",
            "保本型"
    };
    static private final String[] bondInterestType = {
        "附息债", "零息债", "贴现债"
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

   static public Integer generateProductID(Object product, String category) {
        try {
            Class cls = Class.forName("Product" + category);
            Field field = cls.getField("id");
            int id = field.getInt(product);
            return generateProductID(id, category);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public String getBondType(Product product) {
        if (!product.getCategory().getBiggerCategory().equals("Bond")) {
            return null;
        }

        ProductBond productBond = (ProductBond) product.getProduct();
        return bondTypes[productBond.getType()];
    }

    static public String getBondInterestType(Product product) {
        if (!product.getCategory().getBiggerCategory().equals("Bond")) {
            return null;
        }

        ProductBond productBond = (ProductBond) product.getProduct();
        return bondInterestType[productBond.getCouponType()];
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
            return "份";
        }
        else if (belongTo(category, ProductCategoryManager.categoryInsurance)) {
            return "份";
        }

        return null;
    }

    static public boolean ifNetBankProduct(ProductBank bank) {
        byte word = bank.getType();
        return  (((word >> 1) & 1) == 1);
    }

    static public boolean ifClosedBankProduct(ProductBank bank) {
        byte word = bank.getType();
        return (word & 1) == 1;
    }

    static private String getFundType(Product product) {
        if (!product.getCategory().getBiggerCategory().equals(categoryFund)) {
            return null;
        }

        ProductFund productFund = (ProductFund)product.getProduct();
        return categoryFund + fundTypEn[productFund.getCategory()];
    }

    static public Integer getFundTypeIndex(String category) {
        for (int i = 0; i < fundTypEn.length; ++i) {
            if (category.startsWith(fundTypEn[i])) {
                return i;
            }
        }

        return null;
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
