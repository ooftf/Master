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
    VerticalTransformer transformer = new  VerticalTransformer();
    public VerticalViewPager(@NonNull Context context) {
        super(context);
        setPageTransformer(false, transformer);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, transformer);
    }

    float lastY;
    float distanceTop;
    float distanceBottom;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("evY",""+ev.getY());
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                distanceTop = 0;
                distanceBottom = 0;
                intercept =false;
                break;
            case MotionEvent.ACTION_MOVE:
                EdgeListener edgeListener;
                edgeListener = (EdgeListener) transformer.showing;

                if (edgeListener.isTop()) {//顶部
                    if (ev.getY() - lastY >= 0) {
                        distanceTop += ev.getY() - lastY;
                    } else {
                        distanceTop = 0;
                    }
                    Log.e("dispatchTouchEvent", "顶部::" + distanceTop);
                    if (distanceTop > 20) {
                        intercept = true;
                    }
                } else {
                    distanceTop = 0;
                }
                if (edgeListener.isBottom()) {//底部

                    if (ev.getY() - lastY <= 0) {
                        distanceBottom += ev.getY() - lastY;
                    } else {
                        distanceBottom = 0;
                    }
                    Log.e("dispatchTouchEvent", "底部::" + distanceTop);
                    if (distanceBottom < -20) {
                        intercept = true;
                    }
                } else {
                    distanceBottom = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                onTouchEvent(ev);
                break;
        }
        lastY = ev.getY();
        super.onInterceptTouchEvent(swapTouchEvent(ev));
        //If not intercept, touch event should not be swapped.
        swapTouchEvent(ev);
        Log.e("onInterceptTouchEvent",""+intercept);
        return intercept;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("onTouchEvent","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("onTouchEvent","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("onTouchEvent","ACTION_UP");
                break;
        }
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
