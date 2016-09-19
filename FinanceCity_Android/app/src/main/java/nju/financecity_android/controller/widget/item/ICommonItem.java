package nju.financecity_android.controller.widget.item;

import android.view.View;

/**
 * Created by coral on 16-9-10.
 */
public interface ICommonItem {

    void initComponents();

    void setItemOnClickListener(View.OnClickListener listener);

    void setItemOnLongClickListener(View.OnLongClickListener listener);

    View getView();

    View findViewById(int resId);
}
