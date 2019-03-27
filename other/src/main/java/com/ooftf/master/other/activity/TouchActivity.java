package com.ooftf.master.other.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.ooftf.master.other.R;
import com.ooftf.master.other.R2;
import com.ooftf.master.other.widget.BaseViewGroup;
import com.ooftf.service.base.BaseSlidingActivity;
import com.ooftf.service.constant.RouterPath;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterPath.OTHER_ACTIVITY_TOUCH)
public class TouchActivity extends BaseSlidingActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R2.id.first)
    BaseViewGroup first;
    @BindView(R2.id.second)
    BaseViewGroup second;
    @BindView(R2.id.third)
    BaseViewGroup third;
    @BindView(R2.id.nav_view)
    NavigationView navigationView;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R2.id.fab)
    FloatingActionButton fab;
    @BindView(R2.id.sb_first_dispatchTouchEvent)
    Switch sbFirstDispatchTouchEvent;
    @BindView(R2.id.sb_first_onInterceptTouchEvent)
    Switch sbFirstOnInterceptTouchEvent;
    @BindView(R2.id.sb_first_onTouchEvent)
    Switch sbFirstOnTouchEvent;
    @BindView(R2.id.sb_second_dispatchTouchEvent)
    Switch sbSecondDispatchTouchEvent;
    @BindView(R2.id.sb_second_onInterceptTouchEvent)
    Switch sbSecondOnInterceptTouchEvent;
    @BindView(R2.id.sb_second_onTouchEvent)
    Switch sbSecondOnTouchEvent;
    @BindView(R2.id.sb_third_dispatchTouchEvent)
    Switch sbThirdDispatchTouchEvent;
    @BindView(R2.id.sb_third_onInterceptTouchEvent)
    Switch sbThirdOnInterceptTouchEvent;
    @BindView(R2.id.sb_third_onTouchEvent)
    Switch sbThirdOnTouchEvent;
    @BindView(R2.id.first_isModify)
    Switch firstIsModify;
    @BindView(R2.id.second_isModify)
    Switch secondIsModify;
    @BindView(R2.id.third_isModify)
    Switch thirdIsModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        ButterKnife.bind(this);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        initClick();
    }


    private void initClick() {
        firstIsModify.setOnCheckedChangeListener(this);
        sbFirstDispatchTouchEvent.setOnCheckedChangeListener(this);
        sbFirstOnInterceptTouchEvent.setOnCheckedChangeListener(this);
        sbFirstOnTouchEvent.setOnCheckedChangeListener(this);
        secondIsModify.setOnCheckedChangeListener(this);
        sbSecondDispatchTouchEvent.setOnCheckedChangeListener(this);
        sbSecondOnInterceptTouchEvent.setOnCheckedChangeListener(this);
        sbSecondOnTouchEvent.setOnCheckedChangeListener(this);
        thirdIsModify.setOnCheckedChangeListener(this);
        sbThirdDispatchTouchEvent.setOnCheckedChangeListener(this);
        sbThirdOnInterceptTouchEvent.setOnCheckedChangeListener(this);
        sbThirdOnTouchEvent.setOnCheckedChangeListener(this);
        //同步
        firstIsModify.setChecked(false);
        secondIsModify.setChecked(false);
        thirdIsModify.setChecked(false);
        sbFirstDispatchTouchEvent.setChecked(false);
        sbFirstOnInterceptTouchEvent.setChecked(false);
        sbFirstOnTouchEvent.setChecked(false);
        sbSecondDispatchTouchEvent.setChecked(false);
        sbSecondOnInterceptTouchEvent.setChecked(false);
        sbSecondOnTouchEvent.setChecked(false);
        sbThirdDispatchTouchEvent.setChecked(false);
        sbThirdOnInterceptTouchEvent.setChecked(false);
        sbThirdOnTouchEvent.setChecked(false);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int i = buttonView.getId();
        if (i == R.id.sb_first_dispatchTouchEvent) {
            first.dispatchTouchEvent = isChecked;

        } else if (i == R.id.sb_first_onInterceptTouchEvent) {
            first.onInterceptTouchEvent = isChecked;

        } else if (i == R.id.sb_first_onTouchEvent) {
            first.onTouchEvent = isChecked;

        } else if (i == R.id.sb_second_dispatchTouchEvent) {
            second.dispatchTouchEvent = isChecked;

        } else if (i == R.id.sb_second_onInterceptTouchEvent) {
            second.onInterceptTouchEvent = isChecked;

        } else if (i == R.id.sb_second_onTouchEvent) {
            second.onTouchEvent = isChecked;

        } else if (i == R.id.sb_third_dispatchTouchEvent) {
            third.dispatchTouchEvent = isChecked;

        } else if (i == R.id.sb_third_onInterceptTouchEvent) {
            third.onInterceptTouchEvent = isChecked;
        } else if (i == R.id.sb_third_onTouchEvent) {
            third.onTouchEvent = isChecked;
        } else if (i == R.id.first_isModify) {
            first.isModifyResult = isChecked;
        } else if (i == R.id.second_isModify) {
            second.isModifyResult = isChecked;
        } else if (i == R.id.third_isModify) {
            third.isModifyResult = isChecked;
        }
    }
}
