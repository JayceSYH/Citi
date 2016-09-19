package nju.financecity_android.controller.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RunnableFuture;

import nju.financecity_android.R;
import nju.financecity_android.controller.widget.AllSearch;
import nju.financecity_android.controller.widget.BankSearch;
import nju.financecity_android.controller.widget.Bar;
import nju.financecity_android.controller.widget.BondSearch;
import nju.financecity_android.controller.widget.FundSearch;
import nju.financecity_android.controller.widget.InsuranceSearch;
import nju.financecity_android.controller.widget.SearchResult;
import nju.financecity_android.controller.widget.SingleSearchResult;
import nju.financecity_android.dao.SearchDao;
import nju.financecity_android.model.SearchAgent;
import nju.financecity_android.model.SearchProduct;
import nju.financecity_android.util.Loading;
import nju.financecity_android.vo.ProductVO;

/**
 * Created by Administrator on 2016/9/3.
 */
public class ProductSearch extends Fragment{
    /**产品搜索体*/
    private RelativeLayout product_search_body;
    private SearchResult searchResult;
    private Loading loading=new Loading();

    /**搜索按钮*/
    private static Button product_search_filter_button;

    // 实例化一个handler
    Handler myHandler = new Handler() {
        //接收到消息后处理
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    product_search_body.removeAllViews();
                    product_search_body.addView(searchResult);
                    loading.closeLoadingDialog();
                    Log.i("search","get message");
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_search, container, false);

        return view;
    }

    @TargetApi(19)
    @Override
    public void onStart() {
        super.onStart();
        this.product_search_body=(RelativeLayout)getView().findViewById(R.id.product_search_body);

        List<ProductVO> list=new ArrayList<ProductVO>();
        final ArrayList<HashMap<String,Object>> maps=new ArrayList<HashMap<String,Object>>();

        Thread brunchThread=new Thread(new Runnable() {
            @Override
            public void run() {JSONObject jAll=new JSONObject();
                try {
                    jAll.put("type", "all");
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                maps.clear();
                maps.addAll(SearchProduct.analyse(jAll));
                searchResult = new SearchResult(getActivity(), search(maps));

                Message message = new Message();
                message.what = 1;
                //发送消息
                myHandler.sendMessage(message);
                Log.i("search","send message");
            }
        });
        brunchThread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                loading.showLoadingDialog(getActivity(),"loading",false);
                Looper.loop();
            }
        }).start();

        searchResult=new SearchResult(getActivity(),list);
        product_search_body.removeAllViews();
        product_search_body.addView(searchResult);
        //关键字搜索框
        final EditText key=(EditText)getView().findViewById(R.id.key);
        Log.i("key",key.getText().toString());
        //主筛选框
        final Spinner master=(Spinner)getView().findViewById(R.id.master_spinner);
        ArrayAdapter<String> master_adapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_element,getResources().getStringArray(R.array.master_destination));
        master.setAdapter(master_adapter);
        //副筛选框
        final Spinner slavery=(Spinner)getView().findViewById(R.id.slavery_spinner);
        final ArrayAdapter<String> slavery_adapter=new ArrayAdapter<String>(getActivity(),R.layout.spinner_element,getResources().getStringArray(R.array.slavery_all));
        slavery.setAdapter(slavery_adapter);
        final ArrayAdapter<String>[] adapter=new ArrayAdapter[]{slavery_adapter};

        final int[] selector={0,0};//两个spinner
        final ArrayList<String[]> content=new ArrayList<String[]>();
        content.add(getResources().getStringArray(R.array.slavery_all));
        content.add(getResources().getStringArray(R.array.slavery_bank));
        content.add(getResources().getStringArray(R.array.slavery_bond));
        content.add(getResources().getStringArray(R.array.slavery_fund));
        content.add(getResources().getStringArray(R.array.slavery_insurance));
        final View[] filter=new View[]{new AllSearch(getActivity())};
        final AllSearch all = new AllSearch(getActivity());
        final BankSearch bank = new BankSearch(getActivity());
        final BondSearch bond = new BondSearch(getActivity());
        final FundSearch fund = new FundSearch(getActivity());
        final InsuranceSearch insurance = new InsuranceSearch(getActivity());
        final boolean[] isPicked={false};

        master.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> av, View v, int pos, long arg3)
                    {
                        selector[0]=pos;
                        //修改slavery的内容
                        adapter[0]=new ArrayAdapter<String>(getActivity(),R.layout.spinner_element,content.get(pos));
                        slavery.setAdapter(adapter[0]);
                        switch (selector[0]) {
                            case 0:
                                all.setInit();
                                filter[0]=all;
                                break;
                            case 1:
                                bank.setInit();
                                filter[0]=bank;
                                break;
                            case 2:
                                bond.setInit();
                                filter[0]=bond;
                                break;
                            case 3:
                                fund.setInit();
                                filter[0]=fund;
                                break;
                            case 4:
                                insurance.setInit();
                                filter[0]=insurance;
                                break;
                        }
                        if(isPicked[0])
                        {
                            //已经在筛选则直接更改页面
                            product_search_body.removeAllViews();
                            product_search_body.addView(filter[0]);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        //nothing
                    }
                }
        );

        slavery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selector[1]=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //搜索按钮
        Button search=(Button)getView().findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 关键字和条件一起搜索得到产品信息列表
                    /*=======================================================================================*/

                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] types={"all","Bank","Bond","Fund","Insurance"};
                        JSONObject masterJObject=new JSONObject();
                        try {
                            if(key.getText()!=null&&!key.getText().toString().equals(""))
                            {
                                masterJObject.put("keyword", key.getText().toString());
                            }
                            else
                            {
                                Log.i("test","key is blank");
                            }
                            masterJObject.put("type",types[selector[0]]);
                        }catch(Exception e){Log.e("test","condition to json exception1");e.printStackTrace();}
                        JSONObject optionJObject=new JSONObject();
                        try {
                            switch (selector[0]) {
                                case 0:
                                    AllSearch allSearch = (AllSearch) filter[0];
                                    float[] all_yearly_income_rate = {allSearch.getYearMin(), allSearch.getYearMax()};
                                    float[] all_expiration = {allSearch.getLimitMin(), allSearch.getLimitMax()};
                                    int all_is_close_ended = 0;//0表示不封闭
                                    if (allSearch.getCloseChecked()) {
                                        all_is_close_ended = 1;
                                    }
                                    optionJObject.put("yearly_income_rate", new JSONArray(all_yearly_income_rate).toString());
                                    optionJObject.put("expiration",new JSONArray(all_expiration).toString());
                                    optionJObject.put("is_close_ended",all_is_close_ended);
                                    break;
                                case 1:
                                    BankSearch bankSearch = (BankSearch) filter[0];
                                    float[] bank_yearly_income_rate = {bankSearch.getYearMin(), bankSearch.getYearMax()};
                                    float[] bank_expiration = {bankSearch.getLimitMin(), bankSearch.getLimitMax()};
                                    float[] bank_initial_amount = {bankSearch.getStartMin(), bankSearch.getStartMax()};
                                    String bank_institution_manage=bankSearch.getAgent();
                                    String bank_income_type=bank.getIncometype();
                                    int bank_is_close_ended = 0;//0表示不封闭
                                    if (bankSearch.getCloseChecked()) {
                                        bank_is_close_ended = 1;
                                    }
                                    optionJObject.put("yearly_income_rate", new JSONArray(bank_yearly_income_rate).toString());
                                    optionJObject.put("expiration",new JSONArray(bank_expiration).toString());
                                    optionJObject.put("is_close_ended",bank_is_close_ended);
                                    optionJObject.put("initial_amount",new JSONArray(bank_initial_amount).toString());
                                    if(!bank_institution_manage.equals("所有"))
                                    {
                                        optionJObject.put("institution_manage",bank_institution_manage);//TODO
                                    }
                                    if(!bank_income_type.equals("所有"))
                                    {
                                        optionJObject.put("income_type", bank_income_type);//TODO
                                    }
                                    break;
                                case 2:
                                    BondSearch bondSearch = (BondSearch) filter[0];
                                    float[] bond_yearly_income_rate = {bondSearch.getYearMin(), bondSearch.getYearMax()};
                                    float[] bond_expiration = {bondSearch.getLimitMin(), bondSearch.getLimitMax()};
                                    String bond_expiration_date=bondSearch.getDdl()+""+"-12-31";//TODO
                                    String bond_state=bondSearch.getState();
                                    optionJObject.put("yearly_income_rate", new JSONArray(bond_yearly_income_rate));
                                    optionJObject.put("expiration",new JSONArray(bond_expiration));
                                    optionJObject.put("expiration_date",bond_expiration_date);
                                    if(!bond_state.equals("所有")) {
                                        optionJObject.put("state", bond_state);
                                    }
                                    break;
                                case 3:
                                    FundSearch fundSearch=(FundSearch) filter[0];
                                    String fund_institution_manage=fundSearch.getAgent();
                                    String fund_type=slavery.getAdapter().getItem(selector[1]).toString();
                                    String fund_state=fundSearch.getState();
                                    float[] fund_net_value={fundSearch.getLatestMin(),fundSearch.getLatestMax()};
                                    int fund_is_close_ended=0;
                                    if(fundSearch.getCloseChecked()){fund_is_close_ended=1;}
                                    int fund_sort_type=fundSearch.getSort_type_item();
                                    if(fund_sort_type==2){fund_sort_type=-1;}
//                                    int fund_expiration=fundSearch.getSortYear();//TODO
                                    if(!fund_institution_manage.equals("所有"))
                                    {
                                        optionJObject.put("institution_manage",fund_institution_manage);
                                    }
                                    if(!fund_type.equals("所有")) {
                                        optionJObject.put("type", fund_type);
                                    }
                                    if(!fund_state.equals("所有")) {
                                        optionJObject.put("state", fund_state);
                                    }
                                    optionJObject.put("net_value",new JSONArray(fund_net_value).toString());
                                    optionJObject.put("is_close_ended",fund_is_close_ended+"");
                                    optionJObject.put("sort_type",fund_sort_type);
//                                    if(fund_expiration!=-1)
//                                        optionJObject.put("expiration",fund_expiration);
                                    break;
                                case 4:
                                    InsuranceSearch insuranceSearch=(InsuranceSearch)filter[0];
                                    String insurance_length_of_years=insuranceSearch.getLimit();
                                    if(insurance_length_of_years.equals("终身"))
                                    {
                                        insurance_length_of_years="100";
                                    }
                                    float[] insurance_income_rate={insuranceSearch.getYearMin(),insuranceSearch.getYearMax()};
                                    String insurance_distributor=insuranceSearch.getAgent();
                                    float[] insurance_price={insuranceSearch.getValueMin(),insuranceSearch.getValueMax()};
                                    optionJObject.put("length_of_years",insurance_length_of_years);
                                    optionJObject.put("income_rate",new JSONArray(insurance_income_rate).toString());
                                    if(!insurance_distributor.equals("所有"))
                                    {
                                        optionJObject.put("institution_manage",insurance_distributor);
                                    }
                                    optionJObject.put("price",new JSONArray(insurance_price).toString());
                                    break;
                            }
                            if(isPicked[0])
                            {
                                masterJObject.put("options",optionJObject);
                            }
                            Log.i("test","optionObject="+optionJObject.toString());
                            Log.i("test","masterObject="+masterJObject.toString());
                        }catch(Exception e){Log.e("test","condition to json exception2");e.printStackTrace();}
                        maps.clear();
                        maps.addAll(SearchProduct.analyse(masterJObject));
                        searchResult = new SearchResult(getActivity(), search(maps));
                        Message message = new Message();
                        message.what = 1;
                        //发送消息
                        myHandler.sendMessage(message);
                        Log.i("search","send message");
                    }
                });
                thread.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        loading.showLoadingDialog(getActivity(),"loading",false);
                        Looper.loop();
                    }
                }).start();
//                try{thread.join();}catch(Exception e){};

                /*=======================================================================================*/

                product_search_body.removeAllViews();
                product_search_body.addView(searchResult);
                isPicked[0] = false;
            }
        });
        //筛选按钮
        Button pick=(Button)getView().findViewById(R.id.pick);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPicked[0]=!isPicked[0];

                product_search_body.removeAllViews();
                product_search_body.addView(filter[0]);
            }
        });
    }
    private ArrayList<ProductVO> search(ArrayList<HashMap<String,Object>> maps)
    {

        ArrayList<ProductVO> list = new ArrayList<ProductVO>();
        String pid="";
        String sid="";
        String name="";
        String type="";
        double year=0;
        String top="";
        String middle="";
        String bottom="";
        String state="";
        for(int i=0;i<maps.size();i++) {//TODO top middle bottom
            HashMap<String, Object> map = maps.get(i);
            pid=map.get("pid").toString();
            switch((String)map.get("productType"))
            {
                case "bank":
                    sid = (String) map.get("sid");
                    name = (String) map.get("name");
                    type = (String) map.get("productType");
                    try {
                        year = (double) map.get("expected_income_rate");
                    }catch(Exception e){}
                    state = (String) map.get("product_type");
                    list.add(new ProductVO(pid,sid, name, type, year, state,top,middle,bottom));
                    break;
                case "bond":
                    sid = (String) map.get("sid");
                    name = (String) map.get("name");
                    type = (String) map.get("productType");
                    try {
                        year = (double) map.get("expected_income_rate");
                    }catch(Exception e){}
                    state = (String) map.get("product_type");
                    list.add(new ProductVO(pid,sid, name, type, year, state,top,middle,bottom));
                    break;
                case "fund":
                    sid = (String) map.get("sid");
                    name = (String) map.get("name");
                    type = (String) map.get("productType");
                    top = "产品状态 ";
                    middle="基金募集日";
                    bottom="管理费率";
                    try {
                        year = (double) map.get("expected_income_rate");
                    }catch(Exception e){}
                    try {
                        top+=(String) map.get("state");
                    }catch(Exception e){}
                    try {
                        middle +=(String) map.get("est_date");
                    }catch(Exception e){}
                    try {
                        bottom +=(String) map.get("mng_charge_rate");
                    }catch(Exception e){}
                    state = (String) map.get("product_type");
                    list.add(new ProductVO(pid,sid, name, type, year, state,top,middle,bottom));
                    break;
                case "insurance":
                    sid = (String) map.get("sid");
                    name = (String) map.get("name");
                    type = (String) map.get("productType");
                    try {
                        year = (double) map.get("expected_income_rate");
                    }catch(Exception e){}
                    state = (String) map.get("product_type");
                    list.add(new ProductVO(pid,sid, name, type, year, state,top,middle,bottom));
                    break;
            }
        }
        return list;
    }

}
