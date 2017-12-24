package com.master.kit.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 99474 on 2017/12/24 0024.
 */

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        setPageTransformer(false, new VerticalTransformer());
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new VerticalTransformer());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = super.onInterceptTouchEvent(swapTouchEvent(ev));
        //If not intercept, touch event should not be swapped.
        swapTouchEvent(ev);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("onInterceptTouchEvent", getScrollX() + "");
        return super.onTouchEvent(swapTouchEvent(ev));
    }

    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;

        event.setLocation(swappedX, swappedY);

        return event;
    }
}
