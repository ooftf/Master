package com.ooftf.widget.self.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 存在问题：下拉刷新的时候，因为ListView接受到了down事件所以item会比那成pressed状态，但是up事件被拦截下来，所以pressed状态不会消失
 * 临时解决方案：去掉item的pressed状态
 * <p>
 * <p>
 *
 * @author master
 * @date 2016/9/20
 */
public abstract class AbstractPullToRefreshHeader extends LinearLayout {
    int heightPx;
    Scroller scroller;
    Handler handler;

    public AbstractPullToRefreshHeader(Context context) {
        super(context);
        init();

    }

    public AbstractPullToRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public AbstractPullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbstractPullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init() {
        scroller = new Scroller(getContext());
        handler = new Handler();
        //设置  与子节点 有关系的属性
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView();
        //初始的时候隐藏自己
        hideSelf();
        //设置和父控件有关的属性
        ViewGroup.LayoutParams params = new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

    }

    void hideSelf() {
        post(new Runnable() {
            @Override
            public void run() {
                heightPx = getHeight();
                setBottomPadding(-heightPx);
            }
        });
    }

    abstract void setContentView();

    public void setBottomPadding(int padding) {
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), padding);
    }

    public void paddingAnimation(int end) {
        int start = getPaddingBottom();
        scroller.startScroll(0, start, 0, end - start);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacksAndMessages(null);
                if (scroller.computeScrollOffset()) {
                    int currY = scroller.getCurrY();
                    setBottomPadding(currY);
                    postDelayed(this, 1000 / 60);
                }
            }
        }, 1000 / 60);
    }

    public int getHeightPx() {
        return heightPx;
    }

    abstract void onPullDownRefreshView();

    abstract void onReleaseRefreshView();

    abstract void onRefreshingView();

    abstract void onScrollView();

}
