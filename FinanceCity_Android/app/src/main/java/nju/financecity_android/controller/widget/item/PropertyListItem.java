package nju.financecity_android.controller.widget.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.vo.PropertyVO;

/**
 * Created by coral on 16-9-10.
 */
public class PropertyListItem implements ICommonItem {
    public PropertyListItem(Context context, View convertView, PropertyVO data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = (convertView == null) ? mInflater.inflate(R.layout.simple_property, null) : convertView;
        this.mData = data;
        initComponents();
    }

    public View getView() {
        return mView;
    }

    @Override
    public View findViewById(int resId) {
        return null;
    }

    public void initComponents() {
        txtProperty = (TextView) mView.findViewById(R.id.txtPropertyName);
        txtValue = (TextView) mView.findViewById(R.id.txtValue);

        txtProperty.setText(mData.propertyName);
        txtValue.setText((mData.value == null) ? "" : String.valueOf(mData.value));
        if (mData.pcolor != 0) {
            txtProperty.setTextColor(mView.getResources().getColor(mData.pcolor));
        }
        if (mData.vcolor != 0) {
            txtValue.setTextColor(mView.getResources().getColor(mData.vcolor));
        }
    }

    @Override
    public void setItemOnClickListener(View.OnClickListener listener) { }

    @Override
    public void setItemOnLongClickListener(View.OnLongClickListener listener) { }

    private LayoutInflater mInflater;
    private Context mContext;
    private View mView;
    private PropertyVO mData;
    private TextView txtProperty, txtValue;
}
