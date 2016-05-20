package com.master.kit.TestActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.master.kit.R;
import com.master.kit.widget.SlidingFrameLayout.SlidingFrameLayout;

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
}
