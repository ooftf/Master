package com.master.kit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.master.kit.R;

/**
 * Created by 99474 on 2017/12/25 0025.
 */

public class ScrollEdgeInterceptorLayout extends FrameLayout implements EdgeListener{

    public ScrollEdgeInterceptorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainAttrs(attrs);

    }

    public ScrollEdgeInterceptorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttrs(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScrollEdgeInterceptorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        obtainAttrs(attrs);
    }
    ViewGroup scroll;
    int resourceId;
    void obtainAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ScrollEdgeInterceptorLayout);
        resourceId = typedArray.getResourceId(R.styleable.ScrollEdgeInterceptorLayout_scrollId, -1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        scroll = findViewById(resourceId);
    }
    public boolean isTop() {
        if(scroll instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView) scroll;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            return linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        }else {
            return scroll.getScrollY() == 0;
        }
    }
    public boolean isBottom(){
        if(scroll instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView) scroll;
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            return linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.getItemCount()-1;
        }else {
            return  scroll.getScrollY()+getHeight() == scroll.getChildAt(0).getHeight();
        }
    }
}
