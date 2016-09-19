package nju.financecity_android.model;

/**
 * Created by coral on 16-9-4.
 */
public class ProductBond extends BaseProduct {

    public ProductBond(String productId) {
        super(productId);
    }

    @Override
    protected void processData() {

        int zhaiquanfenlei;
        zhaiquanfenlei = Integer.parseInt(mRawData.get("type").toString());

        if (zhaiquanfenlei == 1) {
            // 储蓄式
            String zhaiquanmingcheng="";
            String zhaiquanjiancheng="";
            String zhaiquandaima="";
            String faxingdanwei="";
            double piaomianlilv=0;
            double tiaozhenghounianlilv=0;
            int jixifuxifangshi=0;
            int fuxipinlv=0;
            int qixian=0;
            String faxingqishiri="";
            String faxingjiezhiri="";
            int zhuangtai=0;
            String daoqiri="";
            String qixiri="";
            String tiqianduiqukaishiriqi="";
            String tiqianduiqujixikaishiriqi="";
            int mianzhi=0;
            int faxingjia=0;
            zhaiquanmingcheng = mRawData.get("name").toString();
            zhaiquanjiancheng = mRawData.get("abbrName").toString();
            zhaiquandaima = mRawData.get("productCode").toString();
            faxingdanwei = mRawData.get("institutionIssue").toString();
            piaomianlilv = Double.parseDouble(mRawData.get("coupon").toString());
            tiaozhenghounianlilv = Double.parseDouble(mRawData.get("adjustYearlyRate").toString());
            jixifuxifangshi = Integer.parseInt(mRawData.get("couponType").toString());
            fuxipinlv = Integer.parseInt(mRawData.get("couponFreq").toString());
            qixian = Integer.parseInt(mRawData.get("length").toString());
            faxingqishiri = mRawData.get("onIssueDate").toString();
            faxingjiezhiri = mRawData.get("offIssueDate").toString();
            zhuangtai = Integer.parseInt(mRawData.get("state").toString());
            daoqiri = mRawData.get("maturityDate").toString();
            qixiri = mRawData.get("firstAccrDate").toString();
            tiqianduiqukaishiriqi = mRawData.get("advanceRedeemDate").toString();
            tiqianduiqujixikaishiriqi = mRawData.get("advanceRedeemInterest_date").toString();
            mianzhi = Integer.parseInt(mRawData.get("par").toString());
            faxingjia = Integer.parseInt(mRawData.get("issuePrice").toString());

            mData.put("债券名称", zhaiquanmingcheng);
            mData.put("债券简称", zhaiquanjiancheng);
            mData.put("债券代码", zhaiquandaima);
            mData.put("债券分类", "储蓄式");
            mData.put("发行单位", faxingdanwei);
            String strPiaoMianLiLv = String.valueOf(piaomianlilv * 100);
            if (strPiaoMianLiLv.length() > 4) strPiaoMianLiLv = strPiaoMianLiLv.substring(0, 4);
            strPiaoMianLiLv += "%";
            mData.put("票面利率", strPiaoMianLiLv);
            String strtiaozhenghounianlilv = String.valueOf(tiaozhenghounianlilv * 100);
            if (strtiaozhenghounianlilv.length() > 4) strtiaozhenghounianlilv = strtiaozhenghounianlilv.substring(0, 4);
            strtiaozhenghounianlilv += "%";
            mData.put("调整后利率", strtiaozhenghounianlilv);
            switch (jixifuxifangshi) {
                case 0:
                    mData.put("计息、付息方式", "附息");
                    break;
                case 1:
                    mData.put("计息、付息方式", "零息");
                    break;
                case 2:
                    mData.put("计息、付息方式", "贴现");
                    break;
                default:
                    mData.put("计息、付息方式", "--");
            }
            mData.put("付息频率", fuxipinlv + "次/年");
            mData.put("期限", qixian + "年");
            mData.put("发行起始日", faxingqishiri);
            mData.put("发行截止日", faxingjiezhiri);
            mData.put("起息日", qixiri);
            switch (zhuangtai) {
                case 0:
                    mData.put("状态", "发行中");
                    break;
                case 1:
                    mData.put("状态", "已售罄");
                    break;
                case 2:
                    mData.put("状态", "未发行");
                    break;
                default:
                    mData.put("状态", "--");
            }
            mData.put("到期日", daoqiri);
            mData.put("提前兑取开始日起", tiqianduiqukaishiriqi);
            mData.put("提前兑取计息开始日起", tiqianduiqujixikaishiriqi);
            mData.put("面值", "￥" + mianzhi);
            mData.put("发行价", "￥" + faxingjia);
        } else {
            String zhaiquanmingcheng="";
            String zhaiquanjiancheng="";
            String zhaiquandaima="";
            String faxingdanwei="";
            double piaomianlilv=0;
            double tiaozhenghounianlilv=0;
            int jixifuxifangshi=0;
            int fuxipinlv=0;
            int qixian=0;
            String faxingqishiri="";
            String faxingjiezhiri="";
            int zhuangtai=0;
            String daoqiri="";
            String qixiri="";
            int mianzhi=0;
            int faxingjia=0;
            String shangshidi="";
            String fabushijian="";
            String shangshiliutongri="";
            int faxinge=0;
            int duitaojia=0;
            String rengouduixiang="";
            int zhaiquanjiazhi=0;
            int xinyongjibie=0;
            String faxingfangshi="";
            String faxingduixiang="";
            String zhuchengxiaojigou="";
            int shuishouzhuangkuang=0;

            try{zhaiquanmingcheng = mRawData.get("name").toString();}catch(Exception e){}
            try{zhaiquanjiancheng = mRawData.get("abbrName").toString();}catch(Exception e){}
            try{zhaiquandaima = mRawData.get("productCode").toString();}catch(Exception e){}
            try{faxingdanwei = mRawData.get("institutionIssue").toString();}catch(Exception e){}
            try{piaomianlilv = Double.parseDouble(mRawData.get("coupon").toString());}catch(Exception e){}
            try{tiaozhenghounianlilv = Double.parseDouble(mRawData.get("adjustYearlyRate").toString());}catch(Exception e){}
            try{jixifuxifangshi = Integer.parseInt(mRawData.get("couponType").toString());}catch(Exception e){}
            try{fuxipinlv = Integer.parseInt(mRawData.get("couponFreq").toString());}catch(Exception e){}
            try{qixian = Integer.parseInt(mRawData.get("length").toString());}catch(Exception e){}
            try{faxingqishiri = mRawData.get("onIssueDate").toString();}catch(Exception e){}
            try{faxingjiezhiri = mRawData.get("offIssueDate").toString();}catch(Exception e){}
            try{zhuangtai = Integer.parseInt(mRawData.get("state").toString());}catch(Exception e){}
            try{daoqiri = mRawData.get("maturityDate").toString();}catch(Exception e){}
            try{qixiri = mRawData.get("firstAccrDate").toString();}catch(Exception e){}
            try{mianzhi = Integer.parseInt(mRawData.get("par").toString());}catch(Exception e){}
            try{faxingjia = Integer.parseInt(mRawData.get("institutionIssue").toString());}catch(Exception e){}
//            try{zhaiquanjiazhi = Integer.parseInt(mRawData.get("bondValue").toString());}catch(Exception e){}
            try{xinyongjibie = Integer.parseInt(mRawData.get("riskGrade").toString());}catch(Exception e){}
            try{faxingfangshi = mRawData.get("releaseWay").toString();}catch(Exception e){}
            try{faxingduixiang = mRawData.get("objectOriented").toString();}catch(Exception e){}
            try{zhuchengxiaojigou = mRawData.get("institutionUnderwriter").toString();}catch(Exception e){}
            try{shuishouzhuangkuang = Integer.parseInt(mRawData.get("taxState").toString());}catch(Exception e){}

            mData.put("债券名称", zhaiquanmingcheng);
            mData.put("债券简称", zhaiquanjiancheng);
            mData.put("债券代码", zhaiquandaima);
            mData.put("债券分类", "记账式");
            mData.put("发型单位", faxingdanwei);
            String strPiaoMianLiLv = String.valueOf(piaomianlilv * 100);
            if (strPiaoMianLiLv.length() > 4) strPiaoMianLiLv = strPiaoMianLiLv.substring(0, 4);
            strPiaoMianLiLv += "%";
            mData.put("票面利率", strPiaoMianLiLv);
            String strtiaozhenghounianlilv = String.valueOf(tiaozhenghounianlilv * 100);
            if (strtiaozhenghounianlilv.length() > 4) strtiaozhenghounianlilv = strtiaozhenghounianlilv.substring(0, 4);
            strtiaozhenghounianlilv += "%";
            mData.put("调整后利率", strtiaozhenghounianlilv);
            switch (jixifuxifangshi) {
                case 0:
                    mData.put("计息、付息方式", "附息");
                    break;
                case 1:
                    mData.put("计息、付息方式", "零息");
                    break;
                case 2:
                    mData.put("计息、付息方式", "贴现");
                    break;
                default:
                    mData.put("计息、付息方式", "--");
            }
            mData.put("付息频率", fuxipinlv + "次/年");
            mData.put("期限", qixian + "年");
            mData.put("发行起始日", faxingqishiri);
            mData.put("发行截止日", faxingjiezhiri);
            mData.put("起息日", qixiri);
            switch (zhuangtai) {
                case 0:
                    mData.put("状态", "发行中");
                    break;
                case 1:
                    mData.put("状态", "已售罄");
                    break;
                case 2:
                    mData.put("状态", "未发行");
                    break;
                default:
                    mData.put("状态", "--");
            }
            mData.put("到期日", daoqiri);
            mData.put("面值", "￥" + mianzhi);
            mData.put("发行价", "￥" + faxingjia);
//            mData.put("债券价值", zhaiquanjiazhi + "元");
            mData.put("信用级别", xinyongjibie + "级");
            mData.put("发行方式", faxingfangshi);
            mData.put("发行对象", faxingduixiang);
            mData.put("主承销机构", zhuchengxiaojigou);
            mData.put("税收状况", shuishouzhuangkuang);
        }
    }
}
