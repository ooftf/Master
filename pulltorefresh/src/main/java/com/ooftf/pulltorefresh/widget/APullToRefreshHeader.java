package com.ooftf.pulltorefresh.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 存在问题：下拉刷新的时候，因为ListView接受到了down事件所以item会比那成pressed状态，但是up事件被拦截下来，所以pressed状态不会消失
 * 临时解决方案：去掉item的pressed状态
 *
 *
 * Created by master on 2016/9/20.
 */
public abstract class APullToRefreshHeader extends LinearLayout {
    public APullToRefreshHeader(Context context) {
        super(context);
        init();

    }
    public APullToRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public APullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public APullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    int state;
    public int PULL_DOWN_FEFRESH_STATE = 2;
    public int RELEASE_REFRESH_STATE = 3;
    public int REFRESHING_STATE = 4;
    public int SCROLL_STATE = 5;
    int heigthPx;
    Scroller scroller;
    private void init() {
        heigthPx = dip2px(getContext(),56);
        handler = new Handler();
        scroller = new Scroller(getContext());
        fillLayout();
        initLayout();
    }

    private void initLayout() {
    }
    private void InitialStateLayout(){
        onScrollState();
    }
    private void fillLayout(){
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        post(new Runnable() {
            @Override
            public void run() {
                heigthPx = getHeight();
                setBottomPadding(-heigthPx);
            }
        });
    }
    abstract void setContentView();
    private void setBottomPadding(int padding){
        setPadding(getPaddingLeft(),getPaddingTop(),getPaddingRight(),padding);
    }
    IPullToRefreshable  mOwner;
    public void setListView(IPullToRefreshable refreshable){
        mOwner = refreshable;
        mOwner.addHeaderView(this);
        ViewGroup.LayoutParams params = refreshable.getChildLayoutParams();
        params.width= LayoutParams.MATCH_PARENT;
        params.height= LayoutParams.WRAP_CONTENT;
        setLayoutParams(params);
        setContentView();
        InitialStateLayout();
    }
    float startY = 0;
    float currentY = 0;
    public boolean onContentTouchEvent(MotionEvent ev){
        boolean result = true;
        if(MotionEvent.ACTION_DOWN == ev.getAction())
        {
            startY = ev.getY();
        }
        if(state == SCROLL_STATE){
            scrollStateHandler(ev);
            currentY = ev.getY();
            result= false;
        }else if(state == PULL_DOWN_FEFRESH_STATE){
            pullDownRefreshStateHandler(ev);
            currentY = ev.getY();
            result= true;
        }else if(state == RELEASE_REFRESH_STATE){
            releaseRefreshStateHandler(ev);
            currentY = ev.getY();
            result= true;
        }else if(REFRESHING_STATE == state){
            result = false;
        }
        currentY = ev.getY();
        return result;
    }

    private void scrollStateHandler(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(mOwner.isAtTop()&&ev.getY()>currentY){
                    onPullDownRefresh();
                }
                break;
            case MotionEvent.ACTION_UP:
        }
    }
    private void pullDownRefreshStateHandler(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                int newPadding = getPaddingBottom()+(int) (ev.getY() - currentY);
                if(newPadding<-heigthPx){
                    newPadding=-heigthPx;
                }
                if(newPadding>0){
                    onReleaseRefreshState();
                }
                setBottomPadding(newPadding);
                break;
            case MotionEvent.ACTION_UP:
                    onScrollState();
                    paddingAnimation(getPaddingBottom(),-heigthPx);

        }
    }
    //释放刷新
    private void releaseRefreshStateHandler(MotionEvent ev){
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (ev.getY() - currentY);
                //做一个越向下拉约“费力”的假象
                if(dx>0){
                    dx = (int) ((float)dx*heigthPx/(heigthPx+getPaddingBottom()));
                }
                int newPadding = getPaddingBottom()+dx;
                if(newPadding<=0){
                    onPullDownRefresh();
                }
                setBottomPadding(newPadding);
                break;
            case MotionEvent.ACTION_UP:
                onRefreshingState();
                paddingAnimation(getPaddingBottom(),0);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     *  根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    Handler handler;
    private void paddingAnimation(int start, int end){

        scroller.startScroll(0,start,0,end-start);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacksAndMessages(null);
                if(scroller.computeScrollOffset()){
                    int currY = scroller.getCurrY();
                    setBottomPadding(currY);
                    postDelayed(this,1000/60);
                }
            }
        },1000/60);
    }
    void onPullDownRefresh(){
        Log.e("onPullDownRefresh","onPullDownRefresh");
        state = PULL_DOWN_FEFRESH_STATE;
        onPullDownRefreshView();
    }
    abstract void onPullDownRefreshView();
    void onReleaseRefreshState(){
        state = RELEASE_REFRESH_STATE;
        onReleaseRefreshView();
    }
    abstract void onReleaseRefreshView();
    void onRefreshingState(){
        state = REFRESHING_STATE;
        if(listener!=null){
            listener.onRefreshing();
        }
        onRefreshingView();
    }
    abstract void onRefreshingView();
    void onScrollState(){
        state = SCROLL_STATE;
        onScrollView();
    }
    abstract void onScrollView();
    public void onRefreshComplete(){
        state = SCROLL_STATE;
        paddingAnimation(getPaddingBottom(),-heigthPx);
    }
    OnRefreshListener listener;
    public void setOnRefreshListener(OnRefreshListener listener){
        this.listener =listener;
    }
    public interface  OnRefreshListener{
        void onRefreshing();
    }
}
