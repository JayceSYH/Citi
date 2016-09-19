package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.SeekBar;
import android.widget.TextView;
import nju.financecity_android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sam on 16/9/12.
 */
public class QuestionII extends Fragment implements IObservableQuestion {
    private EditText Insurance,GetMoney;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_ii,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initComponent();
    }

    public View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    public Fragment setObserver(IQuestionObserver observer) {
        this.mObserver = observer;
        return this;
    }

    private void initComponent(){
        sb1 = (SeekBar) findViewById(R.id.sb1);
        sb1.setProgress(2);
        sb2 = (SeekBar) findViewById(R.id.sb2);
        sb2.setProgress(5);
        sb3 = (SeekBar) findViewById(R.id.sb3);
        sb3.setProgress(10);
        sb4 = (SeekBar) findViewById(R.id.sb4);
        sb4.setProgress(20);
        txtValue1 = (TextView) findViewById(R.id.txtValue1);
        txtValue2 = (TextView) findViewById(R.id.txtValue2);
        txtValue3 = (TextView) findViewById(R.id.txtValue3);
        txtValue4 = (TextView) findViewById(R.id.txtValue4);
        txtValue1.setText(String.format(Locale.CHINESE, "%d%%", sb1.getProgress()));
        txtValue2.setText(String.format(Locale.CHINESE, "%d%%", sb2.getProgress()));
        txtValue3.setText(String.format(Locale.CHINESE, "%d%%", sb3.getProgress()));
        txtValue4.setText(String.format(Locale.CHINESE, "%d%%", sb4.getProgress()));
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtValue1.setText(String.format(Locale.CHINESE, "%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtValue2.setText(String.format(Locale.CHINESE, "%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtValue3.setText(String.format(Locale.CHINESE, "%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        sb4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtValue4.setText(String.format(Locale.CHINESE, "%d%%", progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        btCancel = (Button) findViewById(R.id.btCancel);
        btContinue = (Button) findViewById(R.id.btContinue);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Boolean.valueOf(mObserver.getAnswer("ifPrepare").toString())) {
                    mObserver.showFormerPage();
                } else {
                    mObserver.showFormerPage(1);
                }
            }
        });
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateData();
                mObserver.showNextPage();
            }
        });
    }

    private void generateData() {
        List<Integer> risk = new ArrayList<>();
        risk.add(sb1.getProgress());
        risk.add(sb2.getProgress());
        mObserver.putAnswer("risk", risk);
        List<Integer> income = new ArrayList<>();
        income.add(sb3.getProgress());
        income.add(sb4.getProgress());
        mObserver.putAnswer("income", income);
    }

    private static IQuestionObserver mObserver;
    private SeekBar sb1, sb2, sb3, sb4;
    private TextView txtValue1, txtValue2, txtValue3, txtValue4;
    private Button btCancel, btContinue;
}
