package nju.financecity_android.controller.widget.item.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import nju.financecity_android.controller.widget.item.PropertyListItem;
import nju.financecity_android.vo.PropertyVO;

import java.util.List;

/**
 * Created by coral on 16-9-10.
 */
public class PropertyListAdapter extends BaseAdapter {

    public PropertyListAdapter(Context context, List<PropertyVO> mData) {
        this.mData = mData;
        this.mContext = context;
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
        PropertyListItem item = new PropertyListItem(mContext, convertView, mData.get(position));
        return item.getView();
    }

    private Context mContext;
    private List<PropertyVO> mData;
}