package nju.financecity_android.controller.widget.item.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import nju.financecity_android.controller.activity.OrderConfirmActivity;
import nju.financecity_android.controller.widget.item.GoodsItemInsurance;
import nju.financecity_android.controller.widget.item.ICommonItem;
import nju.financecity_android.controller.widget.item.GoodsItemWithPrice;
import nju.financecity_android.controller.widget.item.GoodsItemWithoutPrice;
import nju.financecity_android.vo.GoodsInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by coral on 16-9-9.
 */
public class ListGoodsAdapter extends BaseAdapter {

    public ListGoodsAdapter(Context context, List<GoodsInfo> data) {
        mData = data;
        mInflater = LayoutInflater.from(context);
        mContext = context;
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
        return Long.parseLong(mData.get(position).goodsId);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        final GoodsInfo goodsInfo = (GoodsInfo) getItem(position);
        ICommonItem item = null;
        if (goodsInfo.type.equals("Fund")) {
            item = new GoodsItemWithoutPrice(mContext, convertView, (GoodsInfo) getItem(position));
        } else if (goodsInfo.type.equals("Bank") || goodsInfo.type.equals("Bond")) {
            item = new GoodsItemWithPrice(mContext, convertView, (GoodsInfo) getItem(position));
        } else if (goodsInfo.type.equals("Insurance")) {
            item = new GoodsItemInsurance(mContext, convertView, (GoodsInfo) getItem(position));
        }
        item.setItemOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("确定要删除 " + goodsInfo.goodsName + " 吗？");
                builder.setTitle("删除");
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData.remove(position);
                        ListGoodsAdapter.this.notifyDataSetChanged();
                        ((OrderConfirmActivity) mContext).update(null, null);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
                return false;
            }
        });
        view = item.getView();
        return view;
    }

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GoodsInfo> mData;
}