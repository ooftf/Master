package com.master.kit.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.master.kit.utils.ActivitySet;
import com.master.kit.widget.slidingframelayout.SlidingFrameLayout;
import com.ooftf.kit.utils.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by master on 2016/4/1.
 */
public abstract class BaseActivity extends AppCompatActivity {
    List<Runnable> refreshList = new ArrayList<>();
    private boolean isVisible = false;
    private boolean isTouchable = false;
    private boolean isAlive = false;

    public void startActivity(Class cla) {
        startActivity(new Intent(this, cla));
    }
    @Override
    public void setContentView(int layoutResID) {
        SlidingFrameLayout slidingFrameLayout = new SlidingFrameLayout(this);
        View inflate = View.inflate(this, layoutResID, slidingFrameLayout);
        super.setContentView(inflate);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e(this.getClass().getSimpleName(), "onCreate");
        ActivitySet.getInstance().add(this);
        super.onCreate(savedInstanceState);
        isAlive = true;
    }

    @Override
    protected void onStart() {
        isVisible = true;
        LogUtil.e(this.getClass().getSimpleName(), "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogUtil.e(this.getClass().getSimpleName(), "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        isTouchable = true;
        LogUtil.e(this.getClass().getSimpleName(), "onResume");
        super.onResume();
        doOnResume();
    }

    @Override
    protected void onPause() {
        isTouchable = false;
        LogUtil.e(this.getClass().getSimpleName(), "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        isVisible = false;
        LogUtil.e(this.getClass().getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        isAlive = false;
        LogUtil.e(this.getClass().getSimpleName(), "onDestroy");
        ActivitySet.getInstance().remove(this);
        super.onDestroy();
    }

    protected void doOnResume() {
        Iterator<Runnable> iterator = refreshList.iterator();
        while (iterator.hasNext()) {
            Runnable next = iterator.next();
            next.run();
            iterator.remove();
        }
    }

    public void postOnResume(Runnable doRefresh) {
        refreshList.add(doRefresh);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtil.e(this.getClass().getSimpleName(), "onNewIntent");
        super.onNewIntent(intent);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isTouchable() {
        return isTouchable;
    }

    public boolean isAlive() {
        return isAlive;
    }
    Toast mToast;
    public void toast(String content) {
        if (mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
