package com.master.kit.testcase;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.master.kit.R;
import com.master.kit.base.BaseActivity;
import com.master.kit.fragment.DebugFragment;
import com.master.kit.fragment.LogicFragment;
import com.master.kit.fragment.OtherFragment;
import com.master.kit.fragment.WidgetFragment;
import com.master.kit.utils.FragmentSwitchManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.frame_fragment)
    FrameLayout frameFragment;
    FragmentSwitchManager switchManager;
    OnTabSelectListener onTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            switchManager.switchFragment(tabId);
        }
    };
    private FragmentManager supportFragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        supportFragmentManager = getSupportFragmentManager();
        setupBottomBar();
    }
    FragmentSwitchManager.FragmentSwitchListener fragmentCreater =new FragmentSwitchManager.FragmentSwitchListener() {
        @Override
        public Fragment createFragment(int id) {
            switch (id) {
                case R.id.tab_widget:
                    return WidgetFragment.newInstance();
                case R.id.tab_logic:
                    return LogicFragment.newInstance();
                case R.id.tab_debug:
                    return DebugFragment.newInstance();
                case R.id.tab_other:
                    return OtherFragment.newInstance();
            }
            return null;
        }

        @Override
        public void onSwitchFragment(int id, Fragment fragment) {

        }
    };
    private void setupBottomBar() {
        switchManager = new FragmentSwitchManager(fragmentCreater,
                supportFragmentManager,
                R.id.frame_fragment,
                R.id.tab_widget,
                R.id.tab_logic,
                R.id.tab_debug,
                R.id.tab_other);
        bottomBar.setOnTabSelectListener(onTabSelectListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                toast(System.currentTimeMillis() + "");
                break;
        }
        return true;
    }
}
