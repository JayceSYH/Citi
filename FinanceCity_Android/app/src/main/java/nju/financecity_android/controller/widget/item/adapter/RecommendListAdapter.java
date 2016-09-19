package nju.financecity_android.controller.widget.item.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import nju.financecity_android.controller.widget.item.RecommendItem;
import nju.financecity_android.vo.GoodsInfo;
import nju.financecity_android.vo.RecommendCombVO;
import nju.financecity_android.vo.RecommendSingleVO;
import nju.financecity_android.vo.RecommendVO;

import java.util.List;

/**
 * Created by coral on 16-9-15.
 */
public class RecommendListAdapter extends BaseAdapter {

    public RecommendListAdapter(Context context, RecommendCombVO data) {
        this.mContext = context;
        this.mData = data.products;
        Log.i("recommend","products "+mData.toString());
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
        return 0L;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendItem item = new RecommendItem(mContext, convertView,(RecommendSingleVO) getItem(position));
        item.setChartInfo((float)mData.get(position).amount,(float) mData.get(position).percentage);
        return item.getView();
    }

    private Context mContext;
    private List<RecommendSingleVO> mData;
}
