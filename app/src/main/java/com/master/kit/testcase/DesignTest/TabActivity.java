package com.master.kit.testcase.DesignTest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import tf.oof.com.service.base.BaseActivity;
import com.master.kit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends BaseActivity {

    @BindView(R.id.main_appbar)
    Toolbar appbar;
    @BindView(R.id.main_TabLayout)
    android.support.design.widget.TabLayout TabLayout;
    @BindView(R.id.main_CoordinatorLayout)
    android.support.design.widget.CoordinatorLayout CoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        System.out.println(TabLayout);
       /* TabLayout.addTab(TabLayout.newTab().setText("TAB_1"));
        TabLayout.addTab(TabLayout.newTab().setText("TAB_2"));
        TabLayout.addTab(TabLayout.newTab().setText("TAB_3"));*/
    }

    @OnClick(R.id.main_appbar)
    public void onClick() {

    }
}
