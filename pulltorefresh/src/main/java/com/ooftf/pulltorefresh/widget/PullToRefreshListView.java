package com.ooftf.pulltorefresh.widget;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by master on 2016/9/21.
 */
public class PullToRefreshListView extends ListView implements IPullToRefreshable{
    public PullToRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    PullToRefreshHeader header;
    void init(){
        header  = new PullToRefreshHeader(getContext());
        header.setListView(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!header.onContentTouchEvent(ev)){
            super.onTouchEvent(ev);
        }
        return true;
    }

    public void setOnRefreshListener(PullToRefreshHeader.OnRefreshListener listener){
        header.setOnRefreshListener(listener);

    }
    public void onRefreshComplete(){
        header.onRefreshComplete();
    }

    @Override
    public boolean isAtTop() {
        return getFirstVisiblePosition() == 0;

    }

    @Override
    public ViewGroup.LayoutParams getChildLayoutParams() {
        return new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
