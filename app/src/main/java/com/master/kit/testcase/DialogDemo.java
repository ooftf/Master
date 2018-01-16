package com.master.kit.testcase;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.master.kit.R;

import tf.oof.com.service.engine.LoopTimer;

public class DialogDemo extends Dialog {
    FragmentActivity activity;

    public DialogDemo(Context context) {
        super(context, R.style.DialogTheme_Blank);
        activity = (FragmentActivity) context;
        setContentView(R.layout.dialog_authorization);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.8);
        getWindow().setGravity(Gravity.BOTTOM);
        setOwnerActivity(activity);
        Log.e("isShowing", isShowing() + "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.e("isDestroyed", activity.isDestroyed() + "");
        }
        Log.e("FragmentIsDestroyed", activity.getSupportFragmentManager().isDestroyed() + "");
        Log.e("isFinishing", activity.isFinishing() + "------------");
    }

    public void logLeak() {
        Log.e("isShowing", isShowing() + "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.e("isDestroyed", activity.isDestroyed() + "");
        }

        Log.e("FragmentIsDestroyed", activity.getSupportFragmentManager().isDestroyed() + "");
        Log.e("isFinishing", activity.isFinishing() + ".............");
        LoopTimer loopTimer = new LoopTimer(1000, 1000) {
            int i = 0;

            @Override
            public void onTrick() {
                i++;
                if (i > 20) cancel();
                Log.e("isShowing", isShowing() + "");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Log.e("isDestroyed", activity.isDestroyed() + "");
                }
                Log.e("FragmentIsDestroyed", activity.getSupportFragmentManager().isDestroyed() + "");
                Log.e("isFinishing", activity.isFinishing() + "");
            }
        };
        loopTimer.start();
    }
}
