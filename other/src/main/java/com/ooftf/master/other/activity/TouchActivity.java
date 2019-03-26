package com.ooftf.master.other.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.ooftf.master.other.R;
import com.ooftf.master.other.widget.BaseViewGroup;
import com.ooftf.service.base.BaseSlidingActivity;
import com.ooftf.service.constant.RouterPath;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

@Route(path = RouterPath.OTHER_ACTIVITY_TOUCH)
public class TouchActivity extends BaseSlidingActivity {

    BaseViewGroup first;
    BaseViewGroup second;
    BaseViewGroup third;
    NavigationView navigationView;
    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean checked) {
            int i = buttonView.getId();
            if (i == R.id.sb_first_dispatchTouchEvent) {
                first.dispatchTouchEvent = checked;

            } else if (i == R.id.sb_first_onInterceptTouchEvent) {
                first.onInterceptTouchEvent = checked;

            } else if (i == R.id.sb_first_onTouchEvent) {
                first.onTouchEvent = checked;

            } else if (i == R.id.sb_second_dispatchTouchEvent) {
                second.dispatchTouchEvent = checked;

            } else if (i == R.id.sb_second_onInterceptTouchEvent) {
                second.onInterceptTouchEvent = checked;

            } else if (i == R.id.sb_second_onTouchEvent) {
                second.onTouchEvent = checked;

            } else if (i == R.id.sb_third_dispatchTouchEvent) {
                third.dispatchTouchEvent = checked;

            } else if (i == R.id.sb_third_onInterceptTouchEvent) {
                third.onInterceptTouchEvent = checked;

            } else if (i == R.id.sb_third_onTouchEvent) {
                third.onTouchEvent = checked;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        initViewGroup();
        initClick();
    }

    private void initViewGroup() {
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
    }

    private void initClick() {
        ((Switch) findViewById(R.id.sb_first_dispatchTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_first_onInterceptTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_first_onTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_second_dispatchTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_second_onInterceptTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_second_onTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_third_dispatchTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_third_onInterceptTouchEvent)).setOnCheckedChangeListener(listener);
        ((Switch) findViewById(R.id.sb_third_onTouchEvent)).setOnCheckedChangeListener(listener);
        //同步
        ((Switch) findViewById(R.id.sb_first_dispatchTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_first_onInterceptTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_first_onTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_second_dispatchTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_second_onInterceptTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_second_onTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_third_dispatchTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_third_onInterceptTouchEvent)).setChecked(false);
        ((Switch) findViewById(R.id.sb_third_onTouchEvent)).setChecked(false);

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
}
