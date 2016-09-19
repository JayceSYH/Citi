package nju.financecity_android.model;

import nju.financecity_android.util.DataParser;

/**
 * Created by coral on 16-9-3.
 */
public class ProductFund extends BaseProduct {

    public ProductFund(String productId) {
        super(productId);
    }

    public static String getFundType(int type) {
        switch (type) {
            case 0:
                return "股票型";
            case 1:
                return "债券型";
            case 2:
                return "货币型";
            case 3:
                return "混合型";
            case 4:
                return "eft";
            case 5:
                return "lof";
            case 6:
                return "fof";
            case 7:
                return "QDII";
            case 8:
                return "指数型";
            case 9:
                return "保本型";
            default:
                return "--";
        }
    }

    public static String getFundState(int state) {
        switch (state) {
            case 0:
                return "申购中";
            case 1:
                return "认购中";
            case 2:
                return "已关闭";
            default:
                return "--";
        }
    }

    public static String getYesNo(int yesno) {
        switch (yesno) {
            case 0:
                return "否";
            case 1:
                return "是";
            default:
                return "";
        }
    }

    @Override
    protected void processData() {
        String fadingmingcheng;
        String jijinbianhao;
        String guanlijigou;
        double jinyinianshouyilv;
        int zuixinjinzhi;
        int jijintouzimubiaoleixing;
        String yejibijiaojizhun;
        String genzongbiaodi;
        String fengxiandengji;
        int zhuangtai;
        int shifoufengbi;
        double shengoufeilv;
        double rengoufeilv;
        double shuhuifeilv;
        double guangyiguanlifeilv;
        int qigoujine;
        int dizenggoumaizuixiaodanwei;
        String mujikaishiri;
        String mujijiezhiri;
        String qixiri;
        String kaifangri;
        int qixian;
        int rengousudu;
        int shuhuisudu;
        String jijinjingli;
        double jijingguimo;
        double feneguimo;
        fadingmingcheng = mRawData.get("name") + "";
        jijinbianhao = mRawData.get("productCode") + "";
        guanlijigou = mRawData.get("institutionManage") + "";
        jinyinianshouyilv = DataParser.parseDouble(mRawData.get("yearlyRtnRate") + "");
        zuixinjinzhi = DataParser.parseInt(mRawData.get("nav") + "");
        jijintouzimubiaoleixing = DataParser.parseInt(mRawData.get("category") + "");
        yejibijiaojizhun = mRawData.get("perfBenchmark") + "";
        genzongbiaodi = mRawData.get("targetID") + "";
        fengxiandengji = mRawData.get("riskLevel") + "";
        zhuangtai = DataParser.parseInt(mRawData.get("state") + "");
        shifoufengbi = DataParser.parseInt(mRawData.get("operationMode") + "");
        shengoufeilv = DataParser.parseDouble(mRawData.get("ratePurchase") + "");
        rengoufeilv = DataParser.parseDouble(mRawData.get("rateSubscribe") + "");
        shuhuifeilv = DataParser.parseDouble(mRawData.get("rateRedem") + "");
        guangyiguanlifeilv = DataParser.parseDouble(mRawData.get("rateManage") + "");
        qigoujine = DataParser.parseInt(mRawData.get("purchaseThreshold") + "");
        dizenggoumaizuixiaodanwei = DataParser.parseInt(mRawData.get("increasingUnit") + "");
        mujikaishiri = mRawData.get("onPurchaseDay") + "";
        mujijiezhiri = mRawData.get("offPurchaseDay") + "";
        qixiri = mRawData.get("firstAccrDate") + "";
        kaifangri = mRawData.get("onRedemptionDate") + "";
        qixian = DataParser.parseInt(mRawData.get("length") + "");
        rengousudu = DataParser.parseInt(mRawData.get("subscribeSpeed") + "");
        shuhuisudu = DataParser.parseInt(mRawData.get("redemptionSpeed") + "");
        jijinjingli = mRawData.get("managerName") + "";
        jijingguimo = DataParser.parseDouble(mRawData.get("fundSize") + "");
        feneguimo = DataParser.parseDouble(mRawData.get("shareSize") + "");

        mData.put("法定名称", fadingmingcheng);
        mData.put("基金编号", jijinbianhao);
        mData.put("管理机构", guanlijigou);
        mData.put("近一年收益率", jinyinianshouyilv);
        mData.put("最新净值", zuixinjinzhi + "元");
        String strjijinleixing = getFundType(jijintouzimubiaoleixing);
        mData.put("基金投资目标类型", strjijinleixing);

        mData.put("业绩比较标准", yejibijiaojizhun);
        mData.put("跟踪标的", genzongbiaodi);
        mData.put("风险等级", fengxiandengji + "级");
        mData.put("状态", getFundState(zhuangtai));

        mData.put("是否封闭", getYesNo(shifoufengbi));

        mData.put("申购费率", String.valueOf(shengoufeilv * 100) + "%");
        mData.put("赎回费率", String.valueOf(shuhuifeilv * 100) + "%");
        mData.put("认购费率", String.valueOf(rengoufeilv * 100) + "%");
        mData.put("广义管理费率", String.valueOf(guangyiguanlifeilv * 100) + "%");
        mData.put("起购金额", "￥" + qigoujine);
        mData.put("递增购买最小单位", "￥" + dizenggoumaizuixiaodanwei);
        mData.put("募集开始日", mujikaishiri);
        mData.put("募集截止日", mujijiezhiri);
        mData.put("起息日", qixiri);
        mData.put("开放日", kaifangri);
        mData.put("期限", qixian + "天");
        mData.put("认购速度", rengousudu + "天");
        mData.put("赎回速度", shuhuisudu + "天");
        mData.put("基金经理", jijinjingli);
        mData.put("基金规模", "￥" + jijingguimo);
        mData.put("份额规模", "￥" + feneguimo);
        mData.put("历史净值", mRawData.get("history"));
    }
}
