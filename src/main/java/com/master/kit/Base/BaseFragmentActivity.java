package com.master.kit.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.master.kit.utils.ActivityUtil;
import com.master.kit.utils.LogUtil;

import java.util.ArrayList;

/**
 * Created by master on 2016/3/3.
 */
public class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e(this.getClass().getSimpleName(),"onCreate");
        ActivityUtil.sActivityList.add(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        LogUtil.e(this.getClass().getSimpleName(),"onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtil.e(this.getClass().getSimpleName(),"onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogUtil.e(this.getClass().getSimpleName(),"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtil.e(this.getClass().getSimpleName(),"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtil.e(this.getClass().getSimpleName(),"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtil.e(this.getClass().getSimpleName(),"onDestroy");
        ActivityUtil.sActivityList.remove(this);
        super.onDestroy();
    }



}
