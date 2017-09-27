package com.master.kit.testcase;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends BaseActivity {
    private static final String FRAGMENT_TAG_WIDGET_FRAGMENT = "Fragment_tag_Widget_Fragment";
    private static final String FRAGMENT_TAG_LOGIC_FRAGMENT = "Fragment_tag_Logic_Fragment";
    private static final String FRAGMENT_TAG_DEBUG_FRAGMENT = "Fragment_tag_Debug_Fragment";
    private static final String FRAGMENT_TAG_OTHER_FRAGMENT = "Fragment_tag_Other_Fragment";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.frame_fragment)
    FrameLayout frameFragment;
    private FragmentManager supportFragmentManager;
    OnTabSelectListener onTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            switch (tabId) {
                case R.id.tab_widget:
                    switchFragment(FRAGMENT_TAG_WIDGET_FRAGMENT);
                    break;
                case R.id.tab_logic:
                    switchFragment(FRAGMENT_TAG_LOGIC_FRAGMENT);
                    break;
                case R.id.tab_debug:
                    switchFragment(FRAGMENT_TAG_DEBUG_FRAGMENT);
                    break;
                case R.id.tab_other:
                    switchFragment(FRAGMENT_TAG_OTHER_FRAGMENT);
                    break;
            }
        }
    };
    void switchFragment(String tag){
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Fragment fragment;
        if((fragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_WIDGET_FRAGMENT)) != null){
            fragmentTransaction.hide(fragment);
        }
        if((fragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_LOGIC_FRAGMENT)) != null){
            fragmentTransaction.hide(fragment);
        }
        if((fragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_DEBUG_FRAGMENT)) != null){
            fragmentTransaction.hide(fragment);
        }
        if((fragment=supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_OTHER_FRAGMENT)) != null){
            fragmentTransaction.hide(fragment);
        }
        fragment = supportFragmentManager.findFragmentByTag(tag);
        if (null == fragment) {
            fragment = fragmentCreater(tag);
            fragmentTransaction.add(R.id.frame_fragment,fragment, tag);
        }
        fragmentTransaction.show(fragment).commit();
    }
    Fragment fragmentCreater(String tag){
        if(tag == FRAGMENT_TAG_WIDGET_FRAGMENT){
            return WidgetFragment.newInstance();
        }else if(tag == FRAGMENT_TAG_LOGIC_FRAGMENT){
            return LogicFragment.newInstance();
        }else if(tag == FRAGMENT_TAG_DEBUG_FRAGMENT){
            return DebugFragment.newInstance();
        }else if(tag == FRAGMENT_TAG_OTHER_FRAGMENT){
            return OtherFragment.newInstance();
        }
        return  null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        supportFragmentManager = getSupportFragmentManager();
        setupBottomBar();
    }

    private void setupBottomBar() {
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
