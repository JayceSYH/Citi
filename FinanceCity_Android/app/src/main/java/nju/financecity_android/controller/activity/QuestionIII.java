package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RadioButton;
import nju.financecity_android.R;

/**
 * Created by sam on 16/9/12.
 */
public class QuestionIII extends Fragment implements IObservableQuestion {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_iii,container,false);
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
        btCancel = (Button) findViewById(R.id.btCancel);
        btContinue = (Button) findViewById(R.id.btContinue);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mObserver.showFormerPage();
            }
        });
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateData();
                mObserver.postAnswer();
            }
        });
        rbBank = (RadioButton) findViewById(R.id.rbBank);
        rbFund = (RadioButton) findViewById(R.id.rbFund);
        rbInsurance = (RadioButton) findViewById(R.id.rbInsurance);
        rbBond = (RadioButton) findViewById(R.id.rbBond);
        rbBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFund.setChecked(false);
                rbInsurance.setChecked(false);
                rbBond.setChecked(false);
            }
        });
        rbFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbBank.setChecked(false);
                rbInsurance.setChecked(false);
                rbBond.setChecked(false);
            }
        });
        rbInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbBank.setChecked(false);
                rbFund.setChecked(false);
                rbBond.setChecked(false);
            }
        });
        rbBond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbBank.setChecked(false);
                rbFund.setChecked(false);
                rbInsurance.setChecked(false);
            }
        });
    }

    private void generateData() {
        if (rbBank.isChecked()) mObserver.putAnswer("preferType", "bank");
        if (rbInsurance.isChecked()) mObserver.putAnswer("preferType", "insurance");
        if (rbFund.isChecked()) mObserver.putAnswer("preferType", "fund");
        if (rbBond.isChecked()) mObserver.putAnswer("preferType", "bond");
    }

    private RadioButton rbBank, rbFund, rbInsurance, rbBond;
    private static IQuestionObserver mObserver;
    private Button btCancel, btContinue;
}
