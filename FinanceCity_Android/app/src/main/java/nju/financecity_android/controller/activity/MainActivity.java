package nju.financecity_android.controller.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import nju.financecity_android.R;
import nju.financecity_android.controller.widget.Banner;
import nju.financecity_android.controller.widget.Footer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MainActivity extends Activity {

    public static final int PAGE_SEARCH = 0;
    public static final int PAGE_ASSET_TOP = 1;
    public static final int PAGE_PERSON = 2;
    public static final int PAGE_ASSET = 3;
    public static final int PAGE_INVESTMENT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.main);
        initComponents();
        fragmentManager = getFragmentManager();
        pages.add(PAGE_SEARCH, new ProductSearch());
        pages.add(PAGE_ASSET_TOP, new AssetsTop());
        pages.add(PAGE_PERSON, new Persons());
        pages.add(PAGE_ASSET, new Assets());
        pages.add(PAGE_INVESTMENT, new Investment());
        headers.add(PAGE_SEARCH, "产品信息");
        headers.add(PAGE_ASSET_TOP, "个人资产");
        headers.add(PAGE_PERSON, "个人信息");
        headers.add(PAGE_ASSET, "资产配置");
        headers.add(PAGE_INVESTMENT, "资产概览");
        setDisplayPage(PAGE_SEARCH);
    }

    private void initComponents() {

        main_mid_layout = (LinearLayout) findViewById(R.id.main_mid_layout);
        footer = (Footer) findViewById(R.id.footer);

        for (int i = 0; i < 3; i++) {
            final int tempi = i;
            footer.setOnClickListener(i, new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                }
            });
        }
        banner = (Banner) findViewById(R.id.banner);
        banner.setBtBackOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
            }
        });
        for (int i = 0; i < 3; i++) {
            final int tmpi = i;
            footer.setOnClickListener(i, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDisplayPage(tmpi);
                    footer.setSelectedById(tmpi);
                }
            });
        }
    }

    public void setDisplayPage(int pageId) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_mid_layout, pages.get(pageId));
        fragmentTransaction.addToBackStack(null);   // add to fragment stack
        fragmentTransaction.commit();
        banner.setDisplayText(headers.get(pageId));
        if (pageId < 4) {
            banner.setBtBackVisibility(View.INVISIBLE);
        } else {
            banner.setBtBackVisibility(View.VISIBLE);
        }
    }

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LinearLayout main_mid_layout;
    private Footer footer;
    private Banner banner;
    private List<Fragment> pages = new ArrayList<>();
    private List<String> headers = new ArrayList<>();
}
