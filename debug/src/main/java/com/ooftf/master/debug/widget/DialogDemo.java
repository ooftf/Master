package com.ooftf.master.debug.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import com.ooftf.master.debug.R;

import androidx.fragment.app.FragmentActivity;

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

    /**
     * 用定时器打印 activity 在finish之后  的生命周期方法
     */
    public void logLeak() {
        Log.e("isShowing", isShowing() + "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.e("isDestroyed", activity.isDestroyed() + "");
        }
        Log.e("FragmentIsDestroyed", activity.getSupportFragmentManager().isDestroyed() + "");
        Log.e("isFinishing", activity.isFinishing() + ".............");
    }
}
