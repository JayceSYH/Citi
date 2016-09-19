package nju.financecity_android.controller.widget.item.adapter;

/**
 * Created by coral on 16-9-9.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import nju.financecity_android.R;
import nju.financecity_android.controller.activity.BankDetailActivity;
import nju.financecity_android.controller.activity.BondDetailActivity;
import nju.financecity_android.controller.activity.FundDetailActivity;
import nju.financecity_android.controller.activity.InsuranceDetailActivity;
import nju.financecity_android.controller.widget.item.InvestmentListItem;
import nju.financecity_android.vo.ProductInfo;

import java.util.List;

/**
 * Created by coral on 16-8-26.
 */
public class InvstPrdtAdapter extends BaseAdapter {

    public InvstPrdtAdapter(Context context, List<ProductInfo> data) {
        this.mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InvestmentListItem item = new InvestmentListItem(mContext, convertView, mData.get(position));
        final int po=position;
        item.setItemOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //生成一个Intent对象
                Intent intent = new Intent();
                //在Intent对象当中添加一个键值对
                intent.putExtra("productId", mData.get(po).productId);
                //设置Intent对象要启动的Activity
                Log.i("invest","type "+mData.get(po).type);
                if(mData.get(po).type.contains("理财")) {
                    intent.setClass(mContext, BankDetailActivity.class);
                }
                else if(mData.get(po).type.contains("基金"))
                {
                    intent.setClass(mContext, FundDetailActivity.class);
                }
                else if(mData.get(po).type.contains("债券")) {
                    intent.setClass(mContext, BondDetailActivity.class);
                }
                else if(mData.get(po).type.contains("保险"))
                {
                    intent.setClass(mContext,InsuranceDetailActivity.class);
                }

                //通过Intent对象启动另外一个Activity
                mContext.startActivity(intent);
            }
        });
        return item.getView();
    }

    private Context mContext;
    private List<ProductInfo> mData;
}