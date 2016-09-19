package nju.financecity_android.controller.widget.item;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import nju.financecity_android.R;
import nju.financecity_android.controller.activity.OrderConfirmActivity;
import nju.financecity_android.vo.GoodsInfo;

import java.util.Observable;

/**
 * Created by coral on 16-9-9.
 */
public class GoodsItemWithoutPrice extends Observable implements ICommonItem {

    public GoodsItemWithoutPrice(Context context, View convertView, GoodsInfo data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = mInflater.inflate(R.layout.goods_list_item_fund, null);
        this.mData = data;
        initComponents();
        addObserver((OrderConfirmActivity) mContext);
    }

    @Override
    public void initComponents() {
        mainPane = (RelativeLayout) findViewById(R.id.mainPane);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.setText(mData.amount + "");
        txtSimpleType = (TextView) findViewById(R.id.txtSimpleType);
        txtCatogeries = (TextView) findViewById(R.id.txtCategories);
        txtInitialAmount = (TextView) findViewById(R.id.txtInitialAmount);
        if (mData.initialAmount > 0) {
            txtInitialAmount.setVisibility(View.VISIBLE);
            txtInitialAmount.setText(String.format("￥%d起购", mData.initialAmount));
        } else {
            txtInitialAmount.setVisibility(View.INVISIBLE);
        }
        if (mData.subType != null && !mData.subType.equals("")) {
            txtCatogeries.setText(String.format("%s-%s", "基金", mData.subType));
        } else {
            txtCatogeries.setText(mData.type);
        }
        txtGoodsName = (TextView) findViewById(R.id.txtGoodName);
        txtGoodsName.setText(mData.goodsName);

        txtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String valueStr = txtAmount.getText().toString().trim();
                if (valueStr.matches("[-]?[0-9]+")) {
                    mData.amount = Integer.parseInt(txtAmount.getText().toString().trim());
                    if (mData.amount < mData.initialAmount) mData.amount = mData.initialAmount;
                    setChanged();
                    notifyObservers();
                }
            }
        });
        txtAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String valueStr = txtAmount.getText().toString().trim();
                    if (!valueStr.matches("[0-9]+")) {
                        mData.amount = mData.initialAmount;
                        txtAmount.setText(String.valueOf(mData.initialAmount));
                    }
                }
            }
        });
    }

    @Override
    public void setItemOnClickListener(View.OnClickListener listener) {
        mainPane.setOnClickListener(listener);
    }

    @Override
    public void setItemOnLongClickListener(View.OnLongClickListener listener) {
        mainPane.setOnLongClickListener(listener);
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public View findViewById(int resId) {
        return mView.findViewById(resId);
    }

    private LayoutInflater mInflater;
    private Context mContext;
    private RelativeLayout mainPane;
    private View mView;
    private TextView txtInitialAmount;
    private TextView txtSimpleType, txtCatogeries, txtGoodsName;
    private GoodsInfo mData;
    private EditText txtAmount;
    private int position;
}
