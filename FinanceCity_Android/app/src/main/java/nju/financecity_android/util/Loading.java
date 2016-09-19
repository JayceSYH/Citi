package nju.financecity_android.util;

import android.app.ProgressDialog;
import android.content.Context;

import nju.financecity_android.R;

/**
 * Created by Administrator on 2016/9/15.
 */
public class Loading {
    private static ProgressDialog processDia;
    public static void showLoadingDialog(Context context, String message, boolean isCancelable) {
        if (processDia == null) {
            processDia= new ProgressDialog(context, R.style.dialog);
            //点击提示框外面是否取消提示框
            processDia.setCanceledOnTouchOutside(false);
            //点击返回键是否取消提示框
            processDia.setCancelable(isCancelable);
            processDia.setIndeterminate(true);
            processDia.setMessage(message);
            processDia.show();
        }
    }

    public static void closeLoadingDialog() {
        if (processDia != null) {
            if (processDia.isShowing()) {
                processDia.cancel();
            }
            processDia = null;
        }
    }
}
