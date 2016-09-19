package nju.financecity_android.controller.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import nju.financecity_android.R;
import com.appyvet.rangebar.RangeBar;

public class Bar extends RelativeLayout {
    private String bar_name="bar";
    private Float bar_start=0f;
    private Float bar_end=100f;
    private Float bar_interval=1f;
    private String bar_unit="%";
    private Float putin1_text=0f;
    private Float putin2_text=100f;

    private TextView bar_text;
    private EditText bar_putin1;
    private EditText bar_putin2;
	private RangeBar bar_rangebar;
    private TextView bar_unit_text;
	
    public Bar(Context context) {
        super(context);
        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.bar, this, true);
        onFinishInflate();
    }

    public Bar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载视图的布局
        LayoutInflater.from(context).inflate(R.layout.bar, this, true);

        //加载自定义的属性
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.Bar);
        bar_name=a.getString(R.styleable.Bar_bar_name);
        bar_start=a.getFloat(R.styleable.Bar_bar_start,0);
        bar_end=a.getFloat(R.styleable.Bar_bar_end,100);
        bar_interval=a.getFloat(R.styleable.Bar_bar_interval,1);
        bar_unit=a.getString(R.styleable.Bar_bar_unit);
        putin1_text=a.getFloat(R.styleable.Bar_putin1_text,0);
        putin2_text=a.getFloat(R.styleable.Bar_putin2_text,100);

        //回收资源，这一句必须调用
        a.recycle();

        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //获取子控件
        bar_text = (TextView) findViewById(R.id.bar_text);
        bar_putin1=(EditText) findViewById(R.id.bar_putin1);
        bar_putin2=(EditText) findViewById(R.id.bar_putin2);
        bar_rangebar= (RangeBar) findViewById(R.id.bar_rangebar);
        bar_unit_text =(TextView)findViewById(R.id.bar_unit);

        bar_text.setText(bar_name);
        bar_putin1.setText(putin1_text+"");
        bar_putin2.setText(putin2_text+"");
        bar_rangebar.setTickStart(bar_start);
        bar_rangebar.setTickEnd(bar_end);
        bar_rangebar.setTickInterval(bar_interval);
        bar_unit_text.setText(bar_unit);

        bar_rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                bar_putin1.setText(leftPinValue);
                bar_putin2.setText(rightPinValue);
            }
        });
        bar_putin1.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (bar_putin1.hasFocus() == false) {
                    float leftIntIndex=Float.parseFloat(bar_putin1.getText().toString());
                    float rightIntIndex=Float.parseFloat(bar_putin2.getText().toString());
                    Log.i("pin", "onFocusChange: left:"+leftIntIndex+" right:"+rightIntIndex);
                    int leftIndex=(int)(leftIntIndex/bar_rangebar.getTickInterval());
                    int rightIndex=(int)(rightIntIndex/bar_rangebar.getTickInterval());
                    Log.i("pin", "onFocusChange: left:"+leftIndex+" right:"+rightIndex);
                    bar_rangebar.setRangePinsByIndices(leftIndex,rightIndex);
                }
            }
        });
        bar_putin2.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (bar_putin2.hasFocus() == false) {
                    float leftIntIndex=Float.parseFloat(bar_putin1.getText().toString());
                    float rightIntIndex=Float.parseFloat(bar_putin2.getText().toString());
                    Log.i("pin", "onFocusChange: left:"+leftIntIndex+" right:"+rightIntIndex);
                    int leftIndex=(int)(leftIntIndex/bar_rangebar.getTickInterval());
                    int rightIndex=(int)(rightIntIndex/bar_rangebar.getTickInterval());
                    Log.i("pin", "onFocusChange: left:"+leftIndex+" right:"+rightIndex);
                    bar_rangebar.setRangePinsByIndices(leftIndex,rightIndex);
                }
            }
        });

    }

    public String getBar_text() {
        return bar_text.getText().toString();
    }
    public void setBar_text(String text) {
        bar_text.setText(text);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return bar_rangebar.onTouchEvent(event);
    }


    public void setStart(float start)
    {
        this.bar_rangebar.setTickStart(start);
        this.bar_putin1.setText(start+"");
    }
    public void setEnd(float end)
    {
        this.bar_rangebar.setTickEnd(end);
        this.bar_putin2.setText(end+"");
    }
    public void setInterval(float interval)
    {
        this.bar_rangebar.setTickInterval(interval);
    }
    public void setUnit(String unit)
    {
        this.bar_unit_text.setText(unit);
    }

    public float getMin()
    {
        return Float.parseFloat(bar_putin1.getText().toString());
    }
    public float getMax()
    {
        return Float.parseFloat(bar_putin2.getText().toString());
    }
}
