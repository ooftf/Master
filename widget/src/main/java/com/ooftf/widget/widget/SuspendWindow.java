package com.ooftf.widget.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.ActivityManager;
import com.ooftf.service.utils.JLog;
import com.ooftf.service.utils.MathUtil;
import com.ooftf.widget.R;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/6 0006
 */
public class SuspendWindow {
    public SuspendWindow() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //FLAG_NOT_FOCUSABLE只有控件部分有焦点，FLAG_FORCE_NOT_FULLSCREEN，FLAG_FULLSCREEN整个屏幕的焦点
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.x = (int) (ScreenUtils.getScreenWidth() / 6f * 5);
        layoutParams.y = (int) (ScreenUtils.getScreenHeight() / 6f * 4);
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.format = PixelFormat.RGBA_8888;
        LayoutInflater layoutInflater = LayoutInflater.from(BaseApplication.instance);
        View view = layoutInflater.inflate(R.layout.window_suspend, null);
        WindowManager windowManager = (WindowManager) BaseApplication.instance.getSystemService(Context.WINDOW_SERVICE);
        view.setOnClickListener(v -> {
            Activity topActivity = ActivityManager.INSTANCE.getTopActivity();
            if (topActivity != null) {
                ViewGroup viewGroup = (ViewGroup) topActivity.getWindow().getDecorView();
                if(viewGroup.getChildCount()>0){
                    Snackbar.make(viewGroup.getChildAt(0), topActivity.getClass().getName(), Snackbar.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(BaseApplication.instance, topActivity.getClass().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            float cX = 0;
            float cy = 0;
            boolean move = false;
            final int DISTANCE = 10;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                JLog.e("onTouch", 10, event.getRawX(), event.getRawY());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    move = false;
                    cX = event.getRawX();
                    cy = event.getRawY();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                    if (!move && MathUtil.INSTANCE.distance(cX, cy, event.getRawX(), event.getRawY()) > DISTANCE) {
                        move = true;
                    }
                    if (move) {
                        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) v.getLayoutParams();
                        lp.x = (int) event.getRawX() - v.getWidth() / 2;
                        lp.y = (int) event.getRawY() - v.getHeight() / 2;
                        windowManager.updateViewLayout(v, lp);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!move) {
                        v.performClick();
                    }
                }
                return true;
            }
        });
        try {
            windowManager.addView(view, layoutParams);
            ActivityManager.INSTANCE.registerBackgroundObserver(() -> {
                if (view.getParent() != null) {
                    windowManager.removeView(view);
                }
                return null;
            });
            ActivityManager.INSTANCE.registerForegroundObserver(() -> {
                if (view.getParent() == null) {
                    windowManager.addView(view, layoutParams);
                }
                return null;
            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

}
