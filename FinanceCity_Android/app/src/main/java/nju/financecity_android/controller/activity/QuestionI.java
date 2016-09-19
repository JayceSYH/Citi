package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import nju.financecity_android.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by coral on 16-9-12.
 */
public class QuestionI extends Fragment implements IObservableQuestion {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_i, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        initComponents();
    }

    public View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    public Fragment setObserver(IQuestionObserver observer) {
        this.mObserver = observer;
        return this;
    }

    private void initComponents() {
        txtInvestmentAmount = (EditText) findViewById(R.id.txtInvestmentAmount);
        txtExpiration = (EditText) findViewById(R.id.txtExpiration);
        txtFetchBack = (EditText) findViewById(R.id.txtFetchBack);
        rbOutcomeYes = (RadioButton) findViewById(R.id.rbOutcomeYes);
        rbOutcomeNo = (RadioButton) findViewById(R.id.rbOutcomeNo);
        btCancle = (Button) findViewById(R.id.btCancel);
        btContinue = (Button) findViewById(R.id.btContinue);

        rbOutcomeYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbOutcomeNo.setChecked(false);
                }
            }
        });
        rbOutcomeNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbOutcomeYes.setChecked(false);
                }
            }
        });
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateResult();
                if (Boolean.valueOf(mObserver.getAnswer("ifPrepare").toString())) {
                    mObserver.showNextPage();
                } else {
                    mObserver.showNextPage(1);
                }
            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定要退出编辑投资偏好设置吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
    }

    public void generateResult() {
        int amount = 0;
        if (txtInvestmentAmount.getText().toString().trim().matches("[0-9]+")) {
            amount = Integer.parseInt(txtInvestmentAmount.getText().toString().trim());
        }
        String date, backDate;
        boolean ifPrepare = rbOutcomeYes.isChecked();
        date = txtExpiration.getText().toString();
        backDate = txtFetchBack.getText().toString();
        mObserver.putAnswer("amount", amount);
        mObserver.putAnswer("date", date);
        mObserver.putAnswer("backDate", backDate);
        mObserver.putAnswer("ifPrepare", ifPrepare);
    }

    private EditText txtInvestmentAmount, txtExpiration, txtFetchBack;
    private RadioButton rbOutcomeYes, rbOutcomeNo;
    private static IQuestionObserver mObserver;
    private Button btCancle, btContinue;
}