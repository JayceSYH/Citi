package nju.financecity_android.controller.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.Banner;
import nju.financecity_android.controller.widget.item.adapter.RecommendListAdapter;
import nju.financecity_android.model.FCRecommend;
import nju.financecity_android.model.ProductBank;
import nju.financecity_android.model.ProductBond;
import nju.financecity_android.model.ProductFund;
import nju.financecity_android.model.ProductInsurance;
import nju.financecity_android.util.DataParser;
import nju.financecity_android.util.Loading;
import nju.financecity_android.vo.GoodsInfo;
import nju.financecity_android.vo.RecommendCombVO;
import nju.financecity_android.vo.RecommendSingleVO;
import nju.financecity_android.vo.RecommendVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        initComponents();

    }

    private void initComponents() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        txtSum = (TextView) findViewById(R.id.txtSum);
        txtYield=(TextView) findViewById(R.id.yield_score);
        txtRisk=(TextView) findViewById(R.id.risk_score);
        txtFlow=(TextView) findViewById(R.id.flow_score);
        txtLength=(TextView) findViewById(R.id.length_score);
        btBuy=(Button)findViewById(R.id.btBuy);

        reclist = (ListView) findViewById(R.id.reclist);
        banner = (Banner) findViewById(R.id.banner);
        banner.setDisplayText("推荐购买");
        banner.setBtBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendActivity.this.finish();
            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                combVO=RecommendActivity.this.vo.combinationList.get(Integer.parseInt(s));
                Log.i("recommend","tab "+s);
                Log.i("recommend","combVO "+combVO.toString());
                setCombData();
                tabHost.setCurrentTabByTag(s);
            }
        });


        Log.i("recommend","start get recommend");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("recommend","getRecommend");
                final RecommendVO vo = FCRecommend.getRecommend();
                Log.i("recommend","recommendVO received");
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        RecommendActivity.this.vo=vo;
                        setRecommendData();
                        Log.i("recommend","recommendvo "+vo.toString());
                        RecommendActivity.this.combVO=vo.combinationList.get(0);
                        tabHost.setCurrentTab(1);
                        tabHost.setCurrentTab(0);//wield~
                        setCombData();
                        loading.closeLoadingDialog();
                    }
                });
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                loading.showLoadingDialog(RecommendActivity.this,"loading",false);
                Looper.loop();
            }
        }).start();

        setRecommendData();

        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Looper.prepare();
//                        loading.showLoadingDialog(RecommendActivity.this,"loading",false);
//                        Looper.loop();
//                    }
//                }).start();
                counter=0;
                final Intent intent = new Intent(RecommendActivity.this,OrderConfirmActivity.class);
                final Bundle bundle=new Bundle();
                for(int i=0;i<combVO.products.size();i++) {
                    final RecommendSingleVO single=combVO.products.get(i);
                    final GoodsInfo info = new GoodsInfo();
                    info.type=single.productType;
                    switch(single.productType)
                    {
                        case "Bank":
                            Thread bathread=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mData=new ProductBank(single.id+"").getRawData();
                                    Log.i("order","mDataBank "+mData);
                                    info.goodsId=mData.get("id").toString();
                                    info.goodsName=mData.get("name").toString();
                                    info.increasingUnit = info.price = Integer.parseInt(mData.get("increasingUnit").toString());//递增最小购买单位
                                    info.amount=info.initialAmount=Integer.parseInt(mData.get("purchaseThreshold").toString());//起购金额
//                                    myHandler.post(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            counter++;
                                            bundle.putSerializable(counter+"", info);
                                            Log.i("order","bundle put in "+counter+" "+info.toString());
                                            if(counter==combVO.products.size())
                                            {
                                                intent.putExtras(bundle);
//                                                loading.closeLoadingDialog();
                                                RecommendActivity.this.startActivity(intent);
                                            }
//                                        }
//                                    });
                                }
                            });
                            bathread.start();
                            try{bathread.join();}catch(Exception e){}
                            break;
                        case "Bond":
                            Thread bothread=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mData=new ProductBond(single.id+"").getRawData();
                                    Log.i("order","mDataBond "+mData);
                                    info.goodsId=mData.get("id").toString();
                                    info.goodsName=mData.get("name").toString();
                                    info.increasingUnit=1;
                                    info.price=1;
                                    try{info.price = Integer.parseInt(mData.get("").toString())*10;}catch(Exception e){}//单价*10
                                    info.amount=(int)single.amount/info.price;//数量
//                                    myHandler.post(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            counter++;
                                            bundle.putSerializable(counter+"", info);
                                            Log.i("order","bundle put in "+counter+" "+info.toString());
                                            if(counter==combVO.products.size())
                                            {
                                                intent.putExtras(bundle);
//                                                loading.closeLoadingDialog();
                                                RecommendActivity.this.startActivity(intent);
                                            }
//                                        }
//                                    });
                                }
                            });
                            bothread.start();
                            try{bothread.join();}catch(Exception e){}
                            break;
                        case "Fund":
                            Thread fthread=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mData=(new ProductFund(single.id+"")).getProperties();
                                    Log.i("order","mDataFund "+mData);
                                    info.goodsId=mData.get("id").toString();
                                    info.goodsName=mData.get("name").toString();
                                    info.amount=info.initialAmount= DataParser.parseInt(mData.get("purchaseThreshold") + "");//起购金额
                                    info.increasingUnit = info.price = Integer.parseInt(mData.get("increasingUnit").toString());//递增最小购买单位
//                                    myHandler.post(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            counter++;
                                            bundle.putSerializable(counter+"", info);
                                            Log.i("order","bundle put in "+counter+" "+info.toString());
                                            if(counter==combVO.products.size())
                                            {
                                                intent.putExtras(bundle);
//                                                loading.closeLoadingDialog();
                                                RecommendActivity.this.startActivity(intent);
                                            }
//                                        }
//                                    });
                                }
                            });
                            fthread.start();
                            try{fthread.join();}catch(Exception e){}
                            break;
                        case "Insurance":
                            Thread ithread=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mData=(new ProductInsurance(single.id+"")).getProperties();
                                    Log.i("order","mDataInsurance "+mData);
                                    info.goodsId=mData.get("id").toString();
                                    info.goodsName=mData.get("name").toString();
                                    info.increasingUnit=1;
                                    info.price =Integer.parseInt(mData.get("denomination").toString());//价格
                                    info.amount=(int)single.amount/info.price;//数量
//                                    myHandler.post(new Runnable() {
//                                        @Override
//                                        public void run() {
                                            counter++;
                                            bundle.putSerializable(counter+"", info);
                                            Log.i("order","bundle put in "+counter+" "+info.toString());
                                            if(counter==combVO.products.size())
                                            {
                                                intent.putExtras(bundle);
//                                                loading.closeLoadingDialog();
                                                RecommendActivity.this.startActivity(intent);
                                            }
//                                        }
//                                    });
                                }
                            });
                            ithread.start();
                            try{ithread.join();}catch(Exception e){}
                            break;
                    }
                }
            }
        });
    }

    public void setCurrPage(int id) {
        tabHost.setCurrentTab(id);
    }

    private void setCombData()
    {
        txtSum.setText("￥"+combVO.total_amount+"");
        txtYield.setText(combVO.yield_score+"");
        txtRisk.setText(combVO.risk_score+"");
        txtFlow.setText(combVO.flow_score+"");
        txtLength.setText(combVO.length_score+"");
        RecommendListAdapter adapter = new RecommendListAdapter(this, combVO);
        reclist.setAdapter(adapter);
        reclist.setVisibility(View.VISIBLE);
        Log.i("recommend","change tab content");
    }
    private void setRecommendData() {
        for(int i=0;i<vo.combinationList.size();i++)
        {
            tabHost.addTab(tabHost.newTabSpec(i+"").setIndicator("推荐组合"+(i+1)).setContent(R.id.recPane));
        }
    }

    private ListView reclist;
    private TextView txtSum,txtYield,txtRisk,txtLength,txtFlow;
    private TabHost tabHost;
    private Button btBuy;
    private Banner banner;
    private Handler myHandler=new Handler();
    private RecommendCombVO combVO=new RecommendCombVO();
    private RecommendVO vo=new RecommendVO();
    private Loading loading=new Loading();
    private Map mData = null;
    private int counter=0;
}
