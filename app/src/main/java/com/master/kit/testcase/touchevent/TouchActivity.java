package com.master.kit.testcase.touchevent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.kit.R;
import com.master.kit.widget.switchbutton.SwitchButton;
import com.ooftf.service.base.BaseSlidingActivity;

public class TouchActivity extends BaseSlidingActivity {

    BaseViewGroup first;
    BaseViewGroup second;
    BaseViewGroup third;
    NavigationView navigationView;
    SwitchButton.BeforeStateChangedListener listener = new SwitchButton.BeforeStateChangedListener() {
        @Override
        public Boolean beforeStateChanged(SwitchButton view, boolean going) {
            switch (view.getId()) {
                case R.id.sb_first_dispatchTouchEvent:
                    first.dispatchTouchEvent = !going;
                    break;
                case R.id.sb_first_onInterceptTouchEvent:
                    first.onInterceptTouchEvent = !going;
                    break;
                case R.id.sb_first_onTouchEvent:
                    first.onTouchEvent = !going;
                    break;
                case R.id.sb_second_dispatchTouchEvent:
                    second.dispatchTouchEvent = !going;
                    break;
                case R.id.sb_second_onInterceptTouchEvent:
                    second.onInterceptTouchEvent = !going;
                    break;
                case R.id.sb_second_onTouchEvent:
                    second.onTouchEvent = !going;
                    break;
                case R.id.sb_third_dispatchTouchEvent:
                    third.dispatchTouchEvent = !going;
                    break;
                case R.id.sb_third_onInterceptTouchEvent:
                    third.onInterceptTouchEvent = !going;
                    break;
                case R.id.sb_third_onTouchEvent:
                    third.onTouchEvent = !going;
                    break;
            }
            return !going;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView =  findViewById(R.id.nav_view);
        initViewGroup();
        initClick();
    }

    private void initViewGroup() {
        first =  findViewById(R.id.first);
        second =  findViewById(R.id.second);
        third =  findViewById(R.id.third);
        /*third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick","over");
            }
        });*/
    }

    private void initClick() {
        ((SwitchButton)findViewById(R.id.sb_first_dispatchTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_first_onInterceptTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_first_onTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_second_dispatchTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_second_onInterceptTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_second_onTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_third_dispatchTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_third_onInterceptTouchEvent)).setBeforeStateChangedListener(listener);
        ((SwitchButton)findViewById(R.id.sb_third_onTouchEvent)).setBeforeStateChangedListener(listener);
        //同步
        ((SwitchButton)findViewById(R.id.sb_first_dispatchTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_first_onInterceptTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_first_onTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_second_dispatchTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_second_onInterceptTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_second_onTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_third_dispatchTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_third_onInterceptTouchEvent)).setOpen(false);
        ((SwitchButton)findViewById(R.id.sb_third_onTouchEvent)).setOpen(false);

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
