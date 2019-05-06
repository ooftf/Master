package com.ooftf.widget.widget;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.material.snackbar.Snackbar;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.ActivityManager;
import com.ooftf.service.utils.JLog;
import com.ooftf.service.utils.MathUtil;
import com.ooftf.service.widget.dialog.ListDialog;
import com.ooftf.widget.R;

import java.util.ArrayList;

import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter;

/**
 * 首页开发者工具球
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/6 0006
 */
public class SuspendWindow {
    WindowManager windowManager;
    WindowManager.LayoutParams layoutParams;
    ValueAnimator valueAnimator;
    View view;
    public static SuspendWindow INSTANCE = new SuspendWindow();

    public static SuspendWindow getInstance() {
        return INSTANCE;
    }

    private SuspendWindow() {
        valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        windowManager = (WindowManager) BaseApplication.instance.getSystemService(Context.WINDOW_SERVICE);
        initLayoutParams();
        LayoutInflater layoutInflater = LayoutInflater.from(BaseApplication.instance);
        view = layoutInflater.inflate(R.layout.window_suspend, null);
        // 设置点击事件
        view.setOnClickListener(v -> {
            Activity topActivity = ActivityManager.INSTANCE.getTopActivity();
            if (topActivity != null) {
                new ListDialog(topActivity)
                        .setList(new ArrayList<String>() {
                            {
                                add("显示当前Activity名称");
                                add("切换网络环境");
                            }
                        })
                        .setOnItemClickListener((item, position, dialog) -> {
                            dialog.dismiss();
                            switch (position) {
                                case 0:
                                    ViewGroup viewGroup = (ViewGroup) topActivity.getWindow().getDecorView();
                                    if (viewGroup.getChildCount() > 0) {
                                        Snackbar.make(viewGroup.getChildAt(0), topActivity.getClass().getName(), Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BaseApplication.instance, topActivity.getClass().getName(), Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;

                            }
                        })
                        .show();
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            float cX = 0;
            float cy = 0;
            boolean move = false;
            final int DISTANCE = 10;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    move = false;
                    cX = event.getRawX();
                    cy = event.getRawY();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (!move && MathUtil.INSTANCE.distance(cX, cy, event.getRawX(), event.getRawY()) > DISTANCE) {
                        move = true;
                    }
                    if (move) {
                        layoutParams.x = (int) event.getRawX() - v.getWidth() / 2;
                        layoutParams.y = (int) event.getRawY() - v.getHeight() / 2;
                        windowManager.updateViewLayout(v, layoutParams);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (!move) {
                        v.performClick();
                    } else {
                        reviseLocation(view);
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

    private void initLayoutParams() {
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        //FLAG_NOT_FOCUSABLE只有控件部分有焦点，FLAG_FORCE_NOT_FULLSCREEN，FLAG_FULLSCREEN整个屏幕的焦点
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.x = ScreenUtils.getScreenWidth();
        layoutParams.y = (int) (ScreenUtils.getScreenHeight() / 6f * 4);
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.format = PixelFormat.RGBA_8888;
    }

    void reviseLocation(View view) {
        int right = ScreenUtils.getScreenWidth() - layoutParams.x - view.getWidth();
        int bottom = ScreenUtils.getScreenHeight() - layoutParams.y - view.getHeight();
        if (layoutParams.x < layoutParams.y && layoutParams.x < right && layoutParams.x < bottom) {
            moveTo(true, 0);
        } else if (layoutParams.y < layoutParams.x && layoutParams.y < right && layoutParams.y < bottom) {
            moveTo(false, 0);
        } else if (right < layoutParams.x && right < layoutParams.y && right < bottom) {
            moveTo(true, ScreenUtils.getScreenWidth() - view.getWidth());
        } else {
            moveTo(false, ScreenUtils.getScreenHeight() - view.getHeight());
        }
    }


    void moveTo(boolean isX, int to) {
        valueAnimator.removeAllUpdateListeners();
        if (isX) {
            valueAnimator.setIntValues(layoutParams.x, to);
            valueAnimator.addUpdateListener(animation -> {
                layoutParams.x = (int) animation.getAnimatedValue();
                windowManager.updateViewLayout(view, layoutParams);
            });
        } else {
            valueAnimator.setIntValues(layoutParams.y, to);
            valueAnimator.addUpdateListener(animation -> {
                layoutParams.y = (int) animation.getAnimatedValue();
                windowManager.updateViewLayout(view, layoutParams);
            });
        }
        valueAnimator.start();
    }


}
