package nju.financecity_android.controller.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.financecity_android.R;
import nju.financecity_android.controller.activity.BankDetailActivity;
import nju.financecity_android.controller.activity.BondDetailActivity;
import nju.financecity_android.controller.activity.FundDetailActivity;
import nju.financecity_android.controller.activity.InsuranceDetailActivity;
import nju.financecity_android.controller.activity.MainActivity;
import nju.financecity_android.vo.ProductVO;

/**
 * Created by Administrator on 2016/9/6.
 */
public class SearchResult extends ScrollView {
    private List<ProductVO> resultList=new ArrayList<ProductVO>();
    private LinearLayout listLayout;
    private String pid;
    private String type;

    public SearchResult(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.product_search_result, this, true);
        onFinishInflate();
    }

    public SearchResult(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.product_search_result, this, true);
        onFinishInflate();
    }
    public SearchResult(Context context,List<ProductVO> list) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.product_search_result, this, true);
        resultList=list;
        onFinishInflate();
    }
    public void setProductId(String pid)
    {
        this.pid=pid;
    }
    public void setProductType(String type)
    {
        this.type=type;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listLayout=(LinearLayout) findViewById(R.id.product_search_result_layout);
        for(int i=0;i<resultList.size();i++) {
            View single=new SingleSearchResult(getContext(),resultList.get(i));
            setProductId(resultList.get(i).getPid());
            setProductType(resultList.get(i).getType());
            single.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //生成一个Intent对象
                    Intent intent = new Intent();
                    //在Intent对象当中添加一个键值对
                    intent.putExtra("productId", pid);
                    //设置Intent对象要启动的Activity
                    switch(type)
                    {
                        case "Bank":
                            intent.setClass(SearchResult.this.getContext(),BankDetailActivity.class);
                            break;
                        case "Fund":
                            intent.setClass(SearchResult.this.getContext(),FundDetailActivity.class);
                            break;
                        case "Bond":
                            intent.setClass(SearchResult.this.getContext(),BondDetailActivity.class);
                            break;
                        case "Insurance":
                            intent.setClass(SearchResult.this.getContext(),InsuranceDetailActivity.class);
                            break;
                    }

                    //通过Intent对象启动另外一个Activity
                    SearchResult.this.getContext().startActivity(intent);
                }
            });
            listLayout.addView(single);
        }
    }
}
