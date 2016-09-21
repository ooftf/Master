package com.master.kit.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.master.kit.utils.ActivitySet;
import com.master.kit.utils.LogUtil;
import com.master.kit.widget.slidingframelayout.SlidingFrameLayout;

/**
 * Created by master on 2016/4/1.
 */
public abstract class BaseActivity  extends AppCompatActivity {


    public void startActivity(Class cla) {
        startActivity(new Intent(this,cla));
    }

    @Override
    public void setContentView(int layoutResID) {
        SlidingFrameLayout slidingFrameLayout = new SlidingFrameLayout(this);
        View inflate = View.inflate(this, layoutResID,slidingFrameLayout);
        super.setContentView(inflate);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e(this.getClass().getSimpleName(),"onCreate");
        ActivitySet.getInstance().add(this);
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
        ActivitySet.getInstance().remove(this);
        super.onDestroy();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        LogUtil.e(this.getClass().getSimpleName(),"onNewIntent");
        super.onNewIntent(intent);
    }
}
