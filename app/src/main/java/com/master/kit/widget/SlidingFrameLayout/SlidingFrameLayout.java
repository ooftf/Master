package com.master.kit.widget.slidingframelayout;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 为了实现滑动返回而写的一个控件，将此控件作为根节点就可以实现内容的滑动。推荐添加方式如下，通过重写BaseActivity的setContentView()方法
 * {
 * public void setContentView(int layoutResID) {
 * SlidingFrameLayout slidingFrameLayout = new SlidingFrameLayout(this);
 * View inflate = View.inflate(this, layoutResID,slidingFrameLayout);
 * super.setContentView(inflate);
 * }
 * }
 * <p>
 * <p>
 * <p>
 * 需要在主题中设置如下属性
 * <item name="android:windowBackground">@color/transparent</item>
 * <item name="android:windowIsTranslucent">true</item>
 */
public class SlidingFrameLayout extends FrameLayout {


    public SlidingFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SlidingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingFrameLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    float currentY;
    float currentX;
    Scroller mScroller;
    Handler handler = new Handler();
    boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    float startX;
    long startTime;
    int duration = 500;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!enabled)
            return false;
        if (isRootActivity())
            return false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollX = (int) (getScrollX() - (event.getX() - currentX));
                if (scrollX > 0) {
                    scrollX = 0;
                }
                if (scrollX < -getWidth()) {
                    scrollX = -getWidth();
                }
                scrollTo(scrollX, getScrollY());
                break;
            case MotionEvent.ACTION_UP:
                float time = (System.currentTimeMillis() - startTime) / 1000f;
                float distance = event.getX() - startX;
                double speed = distance / time / getWidth();
                if (speed > 1) {
                    mScroller.startScroll(getScrollX(), getScrollY(), (int) (-getWidth() - getScrollX()), 0, duration);
                } else if (getScrollX() < -getWidth() / 2) {
                    mScroller.startScroll(getScrollX(), getScrollY(), (int) (-getWidth() - getScrollX()), 0, duration);

                } else {
                    mScroller.startScroll(getScrollX(), getScrollY(), (int) (0 - getScrollX()), 0, duration);
                }

                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (mScroller.computeScrollOffset()) {
                            handler.removeCallbacksAndMessages(null);
                            handler.postDelayed(this, 10);
                            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
                        } else {
                            if (mScroller.getCurrX() == -getWidth()) {
                                ((FragmentActivity) getContext()).finish();
                            }
                        }
                    }
                }, 10);
                break;
        }
        currentX = event.getX();
        currentY = event.getY();
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && enabled) {
            if (ev.getX() < getWidth() / 25)
                return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean isRootActivity() {
        ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        int i = runningTaskInfos.get(0).numActivities;
        if (i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {
        //(0到-getWidth())
        super.scrollTo(x, y);
        float progress = x / (float) -getWidth();
        int value = (int) (200 * (1 - progress));
        setBackgroundColor(Color.argb(value, 20, 20, 20));
    }
}
