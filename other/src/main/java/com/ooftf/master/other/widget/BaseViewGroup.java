package com.ooftf.master.other.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.ooftf.service.utils.JLog;

/**
 * Created by master on 2016/4/14.
 */
public class BaseViewGroup extends FrameLayout {
    public boolean dispatchTouchEvent;
    public boolean onInterceptTouchEvent;
    public boolean onTouchEvent;

    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        JLog.e(null, 20, this.getClass().getSimpleName(), "start", "dispatchTouchEvent", dispatchTouchEvent);
        boolean result = super.dispatchTouchEvent(ev);
        JLog.e(null, 20, this.getClass().getSimpleName(), "end", "dispatchTouchEvent", dispatchTouchEvent, "super", result);
        JLog.e("-----------------------------" + this.getClass().getSimpleName() + "：：一次事件结束--------------------------------------");
        return dispatchTouchEvent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        JLog.e(null, 20, this.getClass().getSimpleName(), "start", "onInterceptTouchEvent", onInterceptTouchEvent);
        boolean result = super.onInterceptTouchEvent(ev);
        JLog.e(null, 20, this.getClass().getSimpleName(), "end", "onInterceptTouchEvent", onInterceptTouchEvent, "super", result);
        return onInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        JLog.e(null, 20, this.getClass().getSimpleName(), "start", "onTouchEvent", onTouchEvent);
        boolean result = super.onTouchEvent(event);
        JLog.e(null, 20, this.getClass().getSimpleName(), "end", "onTouchEvent", onTouchEvent, "super", result);
        return onTouchEvent;
    }


}
