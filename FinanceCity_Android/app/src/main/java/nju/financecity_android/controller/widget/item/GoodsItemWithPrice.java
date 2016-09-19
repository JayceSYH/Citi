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
public class GoodsItemWithPrice extends Observable implements ICommonItem {

    public GoodsItemWithPrice(Context context, View convertView, GoodsInfo data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = mInflater.inflate(R.layout.goods_list_item_bank, null);
        this.mData = data;
        initComponents();
        addObserver((OrderConfirmActivity) mContext);
    }

    public void initComponents() {
        mainPane = (RelativeLayout) findViewById(R.id.mainPane);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        if (mData.type.equals("Bank")) {
            txtPrice.setText(String.valueOf("￥" + mData.increasingUnit));
        } else {
            txtPrice.setText(String.valueOf("￥" + mData.price));
        }
        btMinus = (TextView) findViewById(R.id.btMinus);
        txtInitialAmount = (TextView) findViewById(R.id.txtInitialAmount);
        btAdd = (TextView) findViewById(R.id.btAdd);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtAmount.setText(String.valueOf(mData.amount));
        txtSimpleType = (TextView) findViewById(R.id.txtSimpleType);
        if (mData.type.equals("Bond")) {
            master="债券";
            txtSimpleType.setBackgroundResource(R.drawable.bond_background);
            txtSimpleType.setText("债");
        } else if (mData.type.equals("Bank")) {
            master="银行理财";
            txtSimpleType.setBackgroundResource(R.drawable.bank_background);
            txtSimpleType.setText("理");
        }
        txtCatogeries = (TextView) findViewById(R.id.txtCategories);
        if (mData.subType != null && !mData.subType.equals("")) {
            txtCatogeries.setText(String.format("%s-%s", master, mData.subType));
        } else {
            txtCatogeries.setText(master);
        }
        txtGoodsName = (TextView) findViewById(R.id.txtGoodName);
        txtGoodsName.setText(mData.goodsName);
        if (mData.initialAmount > 0) {
            txtInitialAmount.setVisibility(View.VISIBLE);
            txtInitialAmount.setText(String.format("￥%d起购", mData.initialAmount));
        } else {
            txtInitialAmount.setVisibility(View.INVISIBLE);
        }

        if (mData.type.equals("Bank")) {
            txtAmount.setText(String.valueOf(mData.initialAmount));
        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int incUnit = (mData.increasingUnit == 0) ? 1 : mData.increasingUnit;
                int amount = Integer.parseInt(txtAmount.getText().toString().trim());
                amount += incUnit;
                txtAmount.setText(amount + "");
            }
        });
        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int incUnit = (mData.increasingUnit == 0) ? 1 : mData.increasingUnit;
                int amount = Integer.parseInt(txtAmount.getText().toString().trim());
                if (amount - incUnit >= mData.initialAmount)
                    amount -= incUnit;
                txtAmount.setText(amount + "");
            }
        });
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
    private View mView;
    private TextView txtPrice, txtSimpleType, txtCatogeries, txtGoodsName;
    private TextView btMinus, btAdd;
    private EditText txtAmount;
    private GoodsInfo mData;
    private TextView txtInitialAmount;
    private RelativeLayout mainPane;
    private int position;
    private String master="";
}
