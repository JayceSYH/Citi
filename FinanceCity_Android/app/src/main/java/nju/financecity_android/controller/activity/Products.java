package nju.financecity_android.controller.activity;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import nju.financecity_android.R;

public class Products extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_products, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        thisView = getView();
    }

    private View findViewById(int resId) {
        return thisView.findViewById(resId);
    }

    private void initComponents() {

    }

    private View thisView;
}
