package nju.financecity_android.model;

/**
 * Created by coral on 16-9-4.
 */
public class ProductInsurance extends BaseProduct {

    public ProductInsurance(String productId) {
        super(productId);
    }

    @Override
    protected void processData() {
        String xianzhongmingcheng = mRawData.get("name").toString();
        String chanpinfaxinggongsi = mRawData.get("institutionManage").toString();
        int peichangjine = Integer.parseInt(mRawData.get("indemnity").toString());
        int baoxianchanpinmiane = Integer.parseInt(mRawData.get("denomination").toString());
        String danweijinepeichang = mRawData.get("indemnityPerUnit").toString();
        double nianhuashouyilv = Double.parseDouble(mRawData.get("yearRate").toString());
        double nianjiesuanlilv = Double.parseDouble(mRawData.get("expectedRate").toString());
        double baozhenglilv = Double.parseDouble(mRawData.get("guaranteedRate").toString());
        double yuqililv = Double.parseDouble(mRawData.get("expectedRate").toString());
        double rijiesuanlilv = Double.parseDouble(mRawData.get("dayRate").toString());
        int jiaofeifangshi = Integer.parseInt(mRawData.get("payType").toString());
        int baozhangnianxian = Integer.parseInt(mRawData.get("warrantyPeriod").toString());
        String goumairi = "--";
        String daoqiri = "--";
        int qixian = Integer.parseInt(mRawData.get("length").toString());

        mData.put("险种名称", xianzhongmingcheng);
        mData.put("产品发行公司", chanpinfaxinggongsi);
        mData.put("赔偿金额", "￥" + peichangjine);
        mData.put("保险产品面额", "￥" + baoxianchanpinmiane);
        mData.put("单位金额赔偿", "￥" + danweijinepeichang);
        String strnianhuashouyilv = String.valueOf(nianhuashouyilv * 100);
        if (strnianhuashouyilv.length() > 4) strnianhuashouyilv = strnianhuashouyilv.substring(0, 4);
        strnianhuashouyilv += "%";
        mData.put("年化收益率", strnianhuashouyilv);
        String strnianjiesuanlilv = String.valueOf(nianjiesuanlilv * 100);
        if (strnianjiesuanlilv.length() > 4) strnianjiesuanlilv = strnianjiesuanlilv.substring(0, 4);
        strnianjiesuanlilv += "%";
        mData.put("年结算利率", strnianjiesuanlilv);
        String strbaozhenglilv = String.valueOf(baozhenglilv * 100);
        if (strbaozhenglilv.length() > 4) strbaozhenglilv = strbaozhenglilv.substring(0, 4);
        strbaozhenglilv += "%";
        mData.put("保证利率", strbaozhenglilv);
        String stryuqililv = String.valueOf(yuqililv * 100);
        if (stryuqililv.length() > 4) stryuqililv = stryuqililv.substring(0, 4);
        stryuqililv += "%";
        mData.put("预期利率", stryuqililv);
        String strrijiesuanlilv = String.valueOf(rijiesuanlilv * 100);
        if (strrijiesuanlilv.length() > 4) strrijiesuanlilv = strrijiesuanlilv.substring(0, 4);
        strrijiesuanlilv += "%";
        mData.put("日结算利率", strrijiesuanlilv);
        switch (jiaofeifangshi) {
            case 0:
                mData.put("缴费方式", "一次缴清");
                break;
            case 1:
                mData.put("缴费方式", "分期缴清");
                break;
            default:
                mData.put("缴费方式", "--");
        }
        mData.put("保障年限", baozhangnianxian + "年");
        mData.put("购买日", goumairi);
        mData.put("到期日", daoqiri);
        mData.put("期限", qixian + "年");
    }
}
