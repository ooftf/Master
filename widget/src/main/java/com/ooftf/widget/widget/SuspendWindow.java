package com.ooftf.widget.widget;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.ActivityManager;
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
        layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.format = PixelFormat.TRANSPARENT;
        LayoutInflater layoutInflater = LayoutInflater.from(BaseApplication.instance);
        View view = layoutInflater.inflate(R.layout.window_suspend, null);
        WindowManager windowManager = (WindowManager) BaseApplication.instance.getSystemService(Context.WINDOW_SERVICE);
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
