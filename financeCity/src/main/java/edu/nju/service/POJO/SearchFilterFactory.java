package edu.nju.service.POJO;

import Jama.Matrix;
import edu.nju.model.ProductBank;
import edu.nju.model.ProductBond;
import edu.nju.model.ProductFund;
import edu.nju.model.ProductInsurance;
import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.CategoryAndProduct.ProductCategoryManager;
import edu.nju.service.ExceptionsAndError.InvalidParametersException;
import edu.nju.service.SearchService.ProductFilter;
import org.python.antlr.ast.Str;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/8/31.
 */
public class SearchFilterFactory {
    static public ProductFilter createFilter(String type, Map map) throws InvalidParametersException {
        System.out.println(type+" createFilter");
        try {
            if (type.equals("Bond")) {
                return createBondFilter(map);
            } else if (type.equals("Bank")) {
                return createBankFilter(map);
            } else if (ProductCategoryManager.belongTo(type, ProductCategoryManager.categoryFund)) {
                return createFundFilter(map);
            } else if (type.equals("Insurance")) {
                return createInsurance(map);
            } else if (type.equals("all")) {
                return createAllFilter(map);
            } else {
                throw new InvalidParametersException("createFilter(" + type + ")");
            }
        }
        catch (Exception e) {
            throw new InvalidParametersException("createFilter(" + type + ")");
        }
    }

    //TODO:check interest rate票面利率 实际利率 or something

    @SuppressWarnings("unchecked")
    static private ProductFilter createAllFilter(Map map) throws InvalidParametersException {
        double[] year_rate = new double[2];
        int[] expiration = new int[2];
        Boolean is_closed_ended;

        try {
            Map option = (Map)map.get("options");
            if (option == null) {
                option = new HashMap();
            }

            List<String> list = (List<String>)option.get("yearly_income_rate");
            init_arrary(list, year_rate);

            list = (List<String>)option.get("expiration");
            init_arrary(list, expiration);

            String if_close = (String)map.get("is_close_ended");
            if (if_close == null) {
                is_closed_ended = null;
            }
            else {
                is_closed_ended = Integer.valueOf(if_close) == 1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParametersException("createAllFilter");
        }


        ProductFilter productFilter = new ProductFilter() {
            double[] year_rate;
            int[] expiration;
            Boolean is_closed_ended;

            @Override
            public boolean isChosen(Object product) {
                if (product instanceof ProductBank) {
                    ProductBank productBank = (ProductBank)(product);
                    double exp_rate = getValue(productBank.getExpectedRate());
                    double length  = getValue(productBank.getLength()) / 30;

                    return  (((exp_rate >= year_rate[0] && exp_rate <= year_rate[1])) &&
                            length > expiration[0] && length < expiration[1] &&
                            (is_closed_ended == null || ProductCategoryManager.ifClosedBankProduct(productBank) == is_closed_ended));
                }
                else if (product instanceof ProductBond) {
                    ProductBond productBond = (ProductBond)(product);
                    double yearRate = getValue(productBond.getCoupon());
                    int length = getValue(productBond.getLength()) / 30;

                    return  (yearRate >= year_rate[0] && yearRate <= year_rate[1] &&
                            length >= expiration[0] && length <= expiration[1]);
                }
                else if (product instanceof ProductInsurance) {
                    ProductInsurance productInsurance = (ProductInsurance)(product);
                    double yearRate = getValue(productInsurance.getExpectedRate());
                    int length = getValue(productInsurance.getWarrantyPeriod());

                    return  (yearRate >= year_rate[0] && yearRate <= year_rate[1] &&
                            length >= expiration[0] && length <= expiration[1]);
                }
                else if (product instanceof ProductFund) {
                    ProductFund productFund = (ProductFund)(product);
                    double yearRate = getValue(productFund.getYearlyRtnRate());
                    int length = getValue(productFund.getLength());

                    return  (yearRate >= year_rate[0] && yearRate <= year_rate[1] &&
                            length >= expiration[0] && length <= expiration[1] &&
                            (is_closed_ended == null || productFund.getOperationMode() == null || (productFund.getOperationMode() == 1) == is_closed_ended));
                }
                else {
                    return false;
                }
            }

            @Override
            public List<String> getSearchScope() {
                return null;
            }

            private ProductFilter setParam(double[] year_rate, int[] expiration, Boolean is_closed_ended) {
                this.year_rate = year_rate;
                this.expiration = expiration;
                this.is_closed_ended = is_closed_ended;

                return this;
            }
        }.setParam(year_rate, expiration, is_closed_ended);

        return productFilter;
    }

    @SuppressWarnings("unchecked")
    static private ProductFilter createBondFilter(Map map) throws InvalidParametersException {
        double[] year_rate = new double[2];
        int[] expiration = new int[2];
        Date expiration_date;
        Byte state;

        try {
            Map option = (Map)map.get("options");
            if (option == null) {
                option = new HashMap();
            }

            List<String> list = (List<String>)option.get("yearly_income_rate");
            init_arrary(list, year_rate);

            list = (List<String>)option.get("expiration");
            init_arrary(list, expiration);

            String expiration_date_s = (String)option.get("expiration_date");
            if (expiration_date_s == null || expiration_date_s.equals("")) {
                expiration_date = null;
            }
            else {
                expiration_date = Date.valueOf(expiration_date_s);
            }

            String state_s = (String)option.get("state");
            if (state_s == null) {
                state = null;
            }
            else {
                state = Byte.valueOf(state_s);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParametersException("createBondFilter");
        }

        ProductFilter productFilter = new ProductFilter() {
            double[] year_rate = new double[2];
            int[] expiration = new int[2];
            Date expiration_date;
            Byte state;

            @Override
            public boolean isChosen(Object product) {
                if (!(product instanceof ProductBond)) {
                    return false;
                }

                ProductBond productBond = (ProductBond)(product);
                int length = getValue(productBond.getLength()) / 30;
                double yearRate = getValue(productBond.getAdjustYearlyRate());

                return (yearRate >= year_rate[0] && yearRate <=  year_rate[1] &&
                length >= expiration[0] && length <= expiration[1] &&
                        (expiration_date == null || productBond.getMaturityDate() == null || expiration_date.after(productBond.getMaturityDate())) &&
                        (state == null || productBond.getState() == null || state.equals(productBond.getState())));
            }

            @Override
            public List<String> getSearchScope() {
                List<String> list = new ArrayList<String>();
                list.add(ProductCategoryManager.categoryBond);

                return list;
            }

            private ProductFilter setParam(double[] year_rate, int[] expiration, Date expiration_date, Byte state) {
                this.year_rate = year_rate;
                this.expiration = expiration;
                this.expiration_date = expiration_date;
                this.state = state;

                return this;
            }
        }.setParam(year_rate, expiration, expiration_date, state);

        return productFilter;
    }

    @SuppressWarnings("unchecked")
    static private ProductFilter createFundFilter(Map map) throws InvalidParametersException {
        String institution_manage;
        Byte type;
        Byte state;
        int[] net_value = new int[2];
        Boolean is_close_ended;
        Byte sort_type;
        int[] expiration = new int[2];

        try {
            Map option = (Map)map.get("options");
            if (option == null) {
                option = new HashMap();
            }

            List<String> list = (List<String>)option.get("expiration");
            init_arrary(list, expiration);

            list = (List<String>)option.get("net_value");
            init_arrary(list, net_value);

            institution_manage = (String)option.get("institution_manage");
            if (institution_manage != null && institution_manage.equals("")) {
                institution_manage = null;
            }

            String type_s = (String)option.get("type");
            if (type_s == null) {
                type = null;
            }
            else {
                type = Byte.valueOf(type_s);
            }

            String state_s = (String)option.get("state");
            if (state_s == null) {
                state = null;
            }
            else {
                state = Byte.valueOf(state_s);
            }

            String sort_s = (String)option.get("sort_type");
            if (sort_s == null) {
                sort_type = null;
            }
            else {
                sort_type = Byte.valueOf(sort_s);
            }

            String if_close = (String)map.get("is_close_ended");
            if (if_close == null) {
                is_close_ended = null;
            }
            else {
                is_close_ended = Byte.valueOf(if_close) == 1;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParametersException("createFundFilter");
        }

        ProductFilter productFilter = new ProductFilter() {
            String institution_manage;
            Byte type;
            Byte state;
            int[] net_value = new int[2];
            Boolean is_close_ended;
            Byte sort_type = 0;
            int[] expiration = new int[2];

            @Override
            public boolean isChosen(Object product) {
                if (!(product instanceof ProductFund)) {
                    return false;
                }

                ProductFund productFund = (ProductFund)(product);
                double netValue = getValue(productFund.getNav());
                int length = getValue(productFund.getLength());
                return (institution_manage == null || productFund.getInstitutionManage() == null || institution_manage.equals(productFund.getInstitutionManage()) &&
                        (type == null || productFund.getCategory() == null || type.equals(productFund.getCategory())) &&
                        (state == null || productFund.getState() == null || state.equals(productFund.getState())) &&
                        (net_value[0] <= netValue && net_value[1] >= netValue) &&
                        (is_close_ended == null || is_close_ended.equals(productFund.getOperationMode() == 1)) &&
                        (expiration[0] <= length && expiration[1] >= length));
            }

            @Override
            public List<String> getSearchScope() {
                List<String> list = new ArrayList<>();
                list.add(ProductCategoryManager.categoryFund);

                return list;
            }

            private ProductFilter setParam(String institution_manage, Byte type, Byte state, int[] net_value,
                                           Boolean is_close_ended, Byte sort_type, int[] expiration) {
                this.institution_manage = institution_manage;
                this.state = state;
                this.net_value = net_value;
                this.type = type;
                this.is_close_ended = is_close_ended;
                this.sort_type = sort_type;
                this.expiration = expiration;

                return  this;

            }
        }.setParam(institution_manage, type, state, net_value, is_close_ended, sort_type, expiration);

        return productFilter;
    }

    @SuppressWarnings("unchecked")
    static private ProductFilter createBankFilter(Map map) throws InvalidParametersException {
        double[] year_rate = new double[2];
        int[] init_amount = new int[2];
        int[] expiration = new int[2];
        String institution_manage;
        Byte income_type;
        Boolean is_close_ended;

        try {
            Map option = (Map)map.get("options");
            if (option == null) {
                option = new HashMap();
            }

            List<String> list = (List<String>)option.get("yearly_income_rate");
            init_arrary(list, year_rate);

            list = (List<String>)option.get("expiration");
            init_arrary(list, expiration);

            list = (List<String>)option.get("initial_amount");
            init_arrary(list, init_amount);

            String if_close = (String)map.get("is_close_ended");
            if (if_close == null) {
                is_close_ended = null;
            }
            else {
                is_close_ended = Integer.valueOf(if_close) == 1;
            }

            institution_manage = (String)option.get("institution_manage");
            if (institution_manage != null && institution_manage.equals("")) {
                institution_manage = null;
            }

            String income_t = (String)option.get("income_type");
            if (income_t == null) {
                income_type = null;
            }
            else {
                income_type = Byte.valueOf(income_t);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParametersException("createBankFilter");
        }

        ProductFilter productFilter = new ProductFilter() {
            double[] year_rate = new double[2];
            int[] init_amount = new int[2];
            int[] expiration = new int[2];
            String institution_manage;
            Byte income_type;
            Boolean is_close_ended;

            @Override
            public boolean isChosen(Object product) {
                if (!(product instanceof ProductBank)) {
                    return false;
                }

                ProductBank productBank = (ProductBank)(product);
                double exp_rate = getValue(productBank.getExpectedRate());
                int length = getValue(productBank.getLength()) / 30;
                int threshold = getValue(productBank.getPurchaseThreshold());

                return (exp_rate >= year_rate[0] && exp_rate <= year_rate[1] &&
                length >= expiration[0] && length <= expiration[1] &&
                threshold >= init_amount[0] && threshold <= init_amount[1] &&
                        (institution_manage == null || productBank.getInstitutionManage() == null || institution_manage.equals(productBank.getInstitutionManage())) &&
                        (income_type == null || productBank.getIncomeType() == null || income_type.equals(productBank.getIncomeType())) &&
                        (is_close_ended == null || is_close_ended.equals(ProductCategoryManager.ifClosedBankProduct(productBank))));
            }

            @Override
            public List<String> getSearchScope() {
                List<String> list = new ArrayList<>();
                list.add(ProductCategoryManager.categoryBank);
                return list;
            }

            private ProductFilter setParam(double[] year_rate, int[] init_amount, int[] expiration, String institution_manage,
                                  Byte income_type, Boolean is_closed_ended) {
                this.year_rate =year_rate;
                this.init_amount = init_amount;
                this.expiration = expiration;
                this.institution_manage = institution_manage;
                this.income_type = income_type;
                this.is_close_ended = is_closed_ended;

                return this;
            }
        }.setParam(year_rate, init_amount, expiration, institution_manage, income_type, is_close_ended);

        return productFilter;
    }

    @SuppressWarnings("unchecked")
    static private ProductFilter createInsurance(Map map) throws InvalidParametersException {
        int[] year_length = new int[2];
        double[] income_rate = new double[2];
        String distributor;
        int[] price = new int[2];

        try {
            Map option = (Map)map.get("options");
            if (option == null) {
                option = new HashMap();
            }
            List<String> list = (List<String>)option.get("length_of_years");
            init_arrary(list, year_length);

            list = (List<String>)option.get("income_rate");
            init_arrary(list, income_rate);

            distributor = (String)option.get("distributor");
            if (distributor != null && distributor.equals("")) {
                distributor = null;
            }

            list = (List<String>)option.get("price");
            init_arrary(list, price);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParametersException("createInsurance");
        }

        ProductFilter productFilter = new ProductFilter() {
            int[] year_length = new int[2];
            double[] income_rate = new double[2];
            String distributor;
            int[] price;

            @Override
            public boolean isChosen(Object product) {
                if (!(product instanceof ProductInsurance)) {
                    return false;
                }

                ProductInsurance productInsurance = (ProductInsurance)product;
                int length = getValue(productInsurance.getWarrantyPeriod());
                double yearRate = getValue(productInsurance.getYearRate());
                double denomination = (double)getValue(productInsurance.getDenomination()) / 10000;
                String institution = productInsurance.getInstitutionManage();

                return (length >= year_length[0] && length <= year_length[1] &&
                        yearRate >= income_rate[0] && yearRate <= income_rate[1] &&
                        (distributor == null ||institution == null || distributor.equals(institution)) &&
                        (price == null ||
                                (price[0] <= denomination
                                && price[1] >= denomination)));
            }

            @Override
            public List<String> getSearchScope() {
                List<String> list = new ArrayList<String>();
                list.add(ProductCategoryManager.categoryInsurance);

                return list;
            }

            private ProductFilter setParam(int[] year_length, double[] income_rate, String distributor, int[] price) {
                this.year_length = year_length;
                this.income_rate = income_rate;
                this.distributor = distributor;
                this.price = price;

                return this;
            }
        }.setParam(year_length, income_rate, distributor, price);

        return productFilter;
    }

    static private void init_arrary(List<String> list, double[] array) throws Exception {
        if (list == null || list.size() == 0) {
            array[0] = 0;
            array[1] = Double.MAX_VALUE;
        }
        else {
            array[0] = Double.valueOf(list.get(0));
            array[1] = Double.valueOf(list.get(1));
        }
    }

    static private void init_arrary(List<String> list, int[] array) throws Exception {
        if (list == null || list.size() == 0) {
            array[0] = 0;
            array[1] = Integer.MAX_VALUE;
        }
        else {
            array[0] = Integer.valueOf(list.get(0));
            array[1] = Integer.valueOf(list.get(1));
        }
    }

    static private double getValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        else {
            return bigDecimal.doubleValue();
        }
    }

    static private int getValue(Integer integer) {
        if (integer == null) {
            return 0;
        }
        else {
            return integer;
        }
    }
}
