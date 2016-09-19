package nju.financecity_android.model;

/**
 * Created by coral on 16-9-3.
 */
public class    ProductBank extends BaseProduct {

    public ProductBank(String productId) {
        super(productId);
    }

    @Override
    protected void processData() {
        String chanpinmingcheng="";
        String chanpinqici="";
        String guanlijigou="";
        String tuoguanjigou="";
        double yujinianhuashouyilv=0;
        int shouyileixing=0;
        int fengxiandengji=0;
        int shifoujingzhixing=0;
        int shifoufengbi=0;
        double shengoufeilv=0;
        double shuhuifeilv=0;
        double guangyiguanlifeilv=0;
        int qigoujine=0;
        int dizenggoumaizuixiaodanwei=0;
        String mujikaishiri="";
        String mujijiezhiri="";
        String qixiri="";
        String kaifangri="";
        int qixian=0;
        int shuhuisudu=0;
        int licaibizhong=0;
        String touzifanwei="";
        String touzibili="";
        String chanpinbianma="";
        String dengjibianma="";
        String xiaoshouquyu="";
        int yunxingguimoshangxian=0;
        String licaibenjinjishouyizhifu="";
        String faxingduixiang="";

        try{chanpinmingcheng = mRawData.get("name").toString();}catch(Exception e){}
        try{chanpinqici = mRawData.get("session").toString();}catch(Exception e){}
        try{guanlijigou = mRawData.get("institutionManage").toString();}catch(Exception e){}
        try{tuoguanjigou = mRawData.get("custodian").toString();}catch(Exception e){}
        try{yujinianhuashouyilv = Double.valueOf(mRawData.get("NAV").toString());}catch(Exception e){}
        try{shouyileixing = Integer.parseInt(mRawData.get("incomeType").toString());}catch(Exception e){}
        try{fengxiandengji = Integer.parseInt(mRawData.get("riskLevel").toString());}catch(Exception e){}
        try{shifoujingzhixing = Integer.parseInt(mRawData.get("ifNAVType").toString());}catch(Exception e){}
        try{shifoufengbi = Integer.parseInt(mRawData.get("ifClose").toString());}catch(Exception e){}
        try{shengoufeilv = Double.parseDouble(mRawData.get("ratePurchase").toString());}catch(Exception e){}
        try{shuhuifeilv = Double.parseDouble(mRawData.get("rateRedem").toString());}catch(Exception e){}
        try{guangyiguanlifeilv = Double.parseDouble(mRawData.get("rateManage").toString());}catch(Exception e){}
        try{qigoujine = Integer.parseInt(mRawData.get("purchaseThreshold").toString());}catch(Exception e){}
        try{dizenggoumaizuixiaodanwei = Integer.parseInt(mRawData.get("increasingUnit").toString());}catch(Exception e){}
        try{mujikaishiri = mRawData.get("onPurchaseDate").toString();}catch(Exception e){}
        try{mujijiezhiri = mRawData.get("offPurchaseDate").toString();}catch(Exception e){}
        try{qixiri = mRawData.get("startInterestDate").toString();}catch(Exception e){}
        try{kaifangri = mRawData.get("onRedemptionDate").toString();}catch(Exception e){}
        try{qixian = Integer.parseInt(mRawData.get("length").toString());}catch(Exception e){}
        try{shuhuisudu = Integer.parseInt(mRawData.get("redemSpeed").toString());}catch(Exception e){}
        try{licaibizhong = Integer.parseInt(mRawData.get("currency").toString());}catch(Exception e){}
        try{touzifanwei = mRawData.get("investField").toString();}catch(Exception e){}
        try{touzibili = mRawData.get("investRatio").toString();}catch(Exception e){}
        try{chanpinbianma = mRawData.get("productCode").toString();}catch(Exception e){}
        try{dengjibianma = mRawData.get("registerCode").toString();}catch(Exception e){}
        try{xiaoshouquyu = mRawData.get("salesTerritory").toString();}catch(Exception e){}
        try{yunxingguimoshangxian = Integer.parseInt(mRawData.get("sizeUpperLimit").toString());}catch(Exception e){}
        try{licaibenjinjishouyizhifu = mRawData.get("payType").toString();}catch(Exception e){}
        try{faxingduixiang = mRawData.get("objectOriented").toString();}catch(Exception e){}

        mData.put("产品名称", chanpinmingcheng);
        mData.put("产品期次", chanpinqici);
        mData.put("管理机构", guanlijigou);
        mData.put("托管机构", tuoguanjigou);
        String strYjnhsyl = String.valueOf(yujinianhuashouyilv * 100);
        if (strYjnhsyl.length() > 4) strYjnhsyl = strYjnhsyl.substring(0, 4);
        strYjnhsyl += "%";
        mData.put("预计年化收益率", strYjnhsyl);
        switch (shouyileixing) {
            case 0:
                mData.put("收益类型", "保本收益型");
                break;
            case 1:
                mData.put("收益类型", "保本浮动收益型");
                break;
            case 2:
                mData.put("收益类型", "非保本浮动收益型");
                break;
            default:
                mData.put("收益类型", "--");
        }
        mData.put("风险等级", fengxiandengji + "级");
        switch (shifoujingzhixing) {
            case 0:
                mData.put("是否净值型", "否");
                break;
            case 1:
                mData.put("是否净值型", "是");
                break;
            default:
                mData.put("是否净值型", "--");
        }
        switch (shifoufengbi) {
            case 0:
                mData.put("是否封闭", "否");
                break;
            case 1:
                mData.put("是否封闭", "是");
                break;
            default:
                mData.put("是否封闭", "--");
        }
        mData.put("申购费率", String.valueOf(shengoufeilv * 100) + "%");
        mData.put("赎回费率", String.valueOf(shuhuifeilv * 100) + "%");
        mData.put("广义管理费率", String.valueOf(guangyiguanlifeilv * 100) + "%");
        mData.put("起购金额", "￥" + qigoujine);
        mData.put("递增购买最小单位", "￥" + dizenggoumaizuixiaodanwei);
        mData.put("募集开始日", mujikaishiri);
        mData.put("募集截止日", mujijiezhiri);
        mData.put("起息日", qixiri);
        mData.put("开放日", kaifangri);
        mData.put("期限", qixian + "天");
        mData.put("赎回速度", shuhuisudu + "天");
        mData.put("理财币种", licaibizhong);
        mData.put("投资范围", touzifanwei);
        mData.put("投资比例", touzibili);
        mData.put("登记编码", dengjibianma);
        mData.put("产品编码", chanpinbianma);
        mData.put("销售区域", xiaoshouquyu);
        mData.put("运行规模上限", yunxingguimoshangxian + "亿元");
        mData.put("理财本金及收益支付", licaibenjinjishouyizhifu);
        mData.put("发行对象", faxingduixiang);
        if (shifoujingzhixing == 1)
            mData.put("历史净值", mRawData.get("history"));
    }
}
