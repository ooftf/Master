package com.ooftf.pulltorefresh.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;

/**
 * Created by master on 2016/9/27.
 */

public class PullToRefreshWebView extends WebView implements IPullToRefreshable{
    public PullToRefreshWebView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullToRefreshWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public PullToRefreshWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
        init();
    }
    PullToRefreshHeader header;
    boolean result = false;
    private void init() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if(scrollY == 0){
                        result = true;
                    }else{
                        result = false;
                    }
                }
            });
        }
        header  = new PullToRefreshHeader(getContext());
        header.setListView(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!header.onContentTouchEvent(event)){
            super.onTouchEvent(event);
        }
        return true;
    }

    @Override
    public boolean isAtTop() {
        return result;
    }

    @Override
    public void addHeaderView(View v) {
        addView(v,0);
    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams() {
        return new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0,0);
    }
    public void setOnRefreshListener(PullToRefreshHeader.OnRefreshListener listener){
        header.setOnRefreshListener(listener);

    }
    public void onRefreshComplete(){
        header.onRefreshComplete();
    }
}
