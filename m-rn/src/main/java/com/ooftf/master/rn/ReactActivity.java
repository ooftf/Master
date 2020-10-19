package com.ooftf.master.rn;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.ooftf.master.rn.packages.CalendarViewReactPackage;
import com.ooftf.master.rn.packages.LogReactPackage;
import com.ooftf.arch.frame.mvvm.activity.BaseActivity;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.tbruyelle.rxpermissions3.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/12/17 0017
 */
@Route(path = "/rn/activity/React")
public class ReactActivity extends BaseActivity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this).request(Manifest.permission.SYSTEM_ALERT_WINDOW).subscribe(aBoolean -> {
            mReactRootView = new RNGestureHandlerEnabledRootView(ReactActivity.this);
            mReactInstanceManager = ReactInstanceManager.builder()
                    .setApplication(getApplication())
                    .setCurrentActivity(this)
                    // 首页的文件路径对应index.js
                    .setJSMainModulePath("index")
                    //对应assets下jsBundle的名字
                    .setBundleAssetName("index.android.bundle")
                    //设置android本地功能组件
                    .addPackage(new RNGestureHandlerPackage())
                    .addPackage(new MainReactPackage())
                    .addPackage(new LogReactPackage())
                    .addPackage(new CalendarViewReactPackage())
                    .setUseDeveloperSupport(BuildConfig.DEBUG)
                    .setInitialLifecycleState(LifecycleState.RESUMED)
                    .build();
            // 注意这里的MyReactNativeApp必须对应“index.js”中的
            // “AppRegistry.registerComponent()”的第一个参数
            mReactRootView.startReactApplication(mReactInstanceManager, "AwesomeProject", null);

            setContentView(mReactRootView);
        });

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
