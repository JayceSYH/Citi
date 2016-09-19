package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import nju.financecity_android.R;
import nju.financecity_android.util.StringUtil;

import java.util.Observer;

/**
 * Created by coral on 16-9-12.
 */
public class QuestionI_I extends Fragment implements IObservableQuestion {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_i_i, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        initComponents();
    }

    private void initComponents() {
        rbPrepareYes = (RadioButton) findViewById(R.id.rbPrepareYes);
        rbPrepareNo = (RadioButton) findViewById(R.id.rbPrepareNo);
        rbInsurance = (RadioButton) findViewById(R.id.rbInsurance);
        rbMassFund = (RadioButton) findViewById(R.id.rbMassFund);
        txtInsuranceAmount = (EditText) findViewById(R.id.txtInsuranceAmount);
        txtFetchAmount = (EditText) findViewById(R.id.txtFetchAmount);
        btCancle = (Button) findViewById(R.id.btCancel);
        btContinue = (Button) findViewById(R.id.btContinue);

        rbPrepareNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbPrepareYes.setChecked(false);
            }
        });
        rbPrepareYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbPrepareNo.setChecked(false);
            }
        });
        rbInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMassFund.setChecked(false);
                txtFetchAmount.setEnabled(false);
                txtInsuranceAmount.setEnabled(true);
            }
        });
        rbMassFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbInsurance.setChecked(false);
                txtFetchAmount.setEnabled(true);
                txtInsuranceAmount.setEnabled(false);
            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mObserver.showFormerPage();
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

    public View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    public Fragment setObserver(IQuestionObserver observer) {
        this.mObserver = observer;
        return this;
    }

    private void generateData() {
        byte type = 0;
        if (rbInsurance.isChecked()) type = 0;
        else if (rbInsurance.isChecked()) type = 1;
        int backAmount = StringUtil.parseInt(txtFetchAmount.getText().toString());
        int insuranceAmount = StringUtil.parseInt(txtInsuranceAmount.getText().toString());
        mObserver.putAnswer("type", type);
        mObserver.putAnswer("backAmount", backAmount);
        mObserver.putAnswer("insuranceAmount", insuranceAmount);
    }

    private RadioButton rbPrepareYes, rbPrepareNo, rbInsurance, rbMassFund;
    private EditText txtInsuranceAmount, txtFetchAmount;
    private static IQuestionObserver mObserver;
    private Button btCancle, btContinue;
}
