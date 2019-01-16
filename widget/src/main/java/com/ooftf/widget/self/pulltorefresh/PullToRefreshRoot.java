package com.ooftf.widget.self.pulltorefresh;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by master on 2017/2/8.
 */

public class PullToRefreshRoot extends LinearLayout {
    public static final int PULL_DOWN_REFRESH_STATE = 2;  //下拉刷新
    public static final int RELEASE_REFRESH_STATE = 3; //释放刷新
    public static final int REFRESHING_STATE = 4; //正在刷新
    public static final int SCROLL_STATE = 5; //target滚动
    View target;
    AbstractPullToRefreshHeader header;
    float downY;
    float currentY;
    int state = SCROLL_STATE;//用来标识当前的状态
    OnRefreshListener listener;

    public PullToRefreshRoot(Context context) {
        super(context);
        init();
    }

    public PullToRefreshRoot(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public PullToRefreshRoot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullToRefreshRoot(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        target = getChildAt(0);
        Log.e("target", target + "..");
        addHeader();
    }

    private void addHeader() {
        header = HeaderFactory.createHeader(getContext());
        addView(header, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                currentY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY() > currentY && isTargetOnTop()) {
                    currentY = ev.getY();
                    return true;
                }
                currentY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (MotionEvent.ACTION_DOWN == ev.getAction()) {
            downY = ev.getY();
        }
        if (state == SCROLL_STATE) {
            scrollStateHandler(ev);
            currentY = ev.getY();

        } else if (state == PULL_DOWN_REFRESH_STATE) {
            pullDownRefreshStateHandler(ev);
            currentY = ev.getY();

        } else if (state == RELEASE_REFRESH_STATE) {
            releaseRefreshStateHandler(ev);
            currentY = ev.getY();
        } else if (REFRESHING_STATE == state) {

        }
        currentY = ev.getY();
        return true;
    }

    /**
     * 判断target是否已经滑动到顶部
     *
     * @return
     */
    boolean isTargetOnTop() {
        target = getChildAt(1);
        return !target.canScrollVertically(-1);
    }

    /**
     * target滚动
     *
     * @param ev
     */
    private void scrollStateHandler(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (isTargetOnTop() && ev.getY() > currentY) {
                    onPullDownRefresh();
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
        }
    }

    /**
     * 下拉刷新
     *
     * @param ev
     */
    private void pullDownRefreshStateHandler(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int newPadding = header.getPaddingBottom() + (int) (ev.getY() - currentY);
                if (newPadding < -header.getHeightPx()) {
                    newPadding = -header.getHeightPx();
                }
                if (newPadding > 0) {
                    onReleaseRefreshState();
                }
                header.setBottomPadding(newPadding);
                break;
            case MotionEvent.ACTION_UP:
                onScrollState();
                header.paddingAnimation(-header.getHeightPx());
            default:
        }
    }

    /**
     * 释放刷新
     *
     * @param ev
     */
    private void releaseRefreshStateHandler(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (ev.getY() - currentY);
                //做一个越向下拉约“费力”的假象
                if (dx > 0) {
                    dx = (int) ((float) dx * header.getHeightPx() / (header.getHeightPx() + header.getPaddingBottom()));
                }
                Log.e("dx", dx + "");
                int newPadding = header.getPaddingBottom() + dx;
                if (newPadding <= 0) {

                    onPullDownRefresh();
                }
                header.setBottomPadding(newPadding);
                break;
            case MotionEvent.ACTION_UP:
                onRefreshingState();
                header.paddingAnimation(0);
            default:
        }
    }

    void onPullDownRefresh() {
        Log.e("onPullDownRefresh", "onPullDownRefresh");
        state = PULL_DOWN_REFRESH_STATE;
        header.onPullDownRefreshView();
    }

    void onReleaseRefreshState() {
        Log.e("onReleaseRefreshState", "onReleaseRefreshState");
        state = RELEASE_REFRESH_STATE;
        header.onReleaseRefreshView();
    }

    void onRefreshingState() {
        state = REFRESHING_STATE;
        if (listener != null) {
            listener.onRefreshing();
        }
        header.onRefreshingView();
    }

    void onScrollState() {
        state = SCROLL_STATE;
        header.onScrollView();
    }

    public void onRefreshComplete() {
        state = SCROLL_STATE;
        header.paddingAnimation(-header.getHeightPx());
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public interface OnRefreshListener {
        void onRefreshing();
    }
}
