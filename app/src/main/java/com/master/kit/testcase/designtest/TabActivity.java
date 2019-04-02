package com.master.kit.testcase.designtest;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.master.kit.R;
import com.ooftf.service.base.BaseSlidingActivity;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 99474
 */
public class TabActivity extends BaseSlidingActivity {

    @BindView(R.id.main_appbar)
    Toolbar appbar;
    @BindView(R.id.main_TabLayout)
    com.google.android.material.tabs.TabLayout TabLayout;
    @BindView(R.id.main_CoordinatorLayout)
    androidx.coordinatorlayout.widget.CoordinatorLayout CoordinatorLayout;

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
