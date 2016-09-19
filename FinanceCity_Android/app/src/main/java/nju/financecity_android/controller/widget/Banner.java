package nju.financecity_android.controller.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appyvet.rangebar.RangeBar;
import nju.financecity_android.R;

/**
 * Created by coral on 16-8-22.
 */
public class Banner extends LinearLayout {

    public Banner(Context context) {
        super(context);
        init(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttributes(context, attrs);
    }

    //----------------------Interfaces------------------------

    public String getDisplayString() {
        return lblText.getText().toString();
    }

    public void setDisplayText(String displayText) {
        lblText.setText(displayText);
    }

    public void setBtBackVisibility(int visibility) {
        btBack.setVisibility(visibility);
    }

    public void setBtBackOnClickListener(OnClickListener listener) {
        btBack.setOnClickListener(listener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadComponents();
        lblText.setText(display_text);
    }

    private void loadComponents() {
        lblText = (TextView) findViewById(R.id.lblText);
        mainPane = (RelativeLayout) findViewById(R.id.mainPane);
        btBack = (TextView) findViewById(R.id.btBack);
        btBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        this.context = context;
    }

    protected void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attrset = context.obtainStyledAttributes(attrs, R.styleable.Bar);
        display_text = attrset.getString(R.styleable.Banner_display_text);
        show_search_button = attrset.getBoolean(R.styleable.Banner_show_search_button, true);
        attrset.recycle();
    }

    private TextView lblText;
    private Context context;
    private RelativeLayout mainPane;
    private TextView btBack;
    private String display_text = "";
    private boolean show_search_button;
}
