package com.ooftf.pulltorefresh.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import com.ooftf.pulltorefresh.R;

/**
 * 存在问题：下拉刷新的时候，因为ListView接受到了down事件所以item会比那成pressed状态，但是up事件被拦截下来，所以pressed状态不会消失
 * 临时解决方案：去掉item的pressed状态
 *
 *
 * Created by master on 2016/9/20.
 */
public class PullToRefreshHeader extends LinearLayout {
    public PullToRefreshHeader(Context context) {
        super(context);
        init();

    }
    public PullToRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public PullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullToRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    int state;
    public int INITIAL_STATE = 0;
    public int PULL_DOWN_FEFRESH_STATE = 2;
    public int RELEASE_REFRESH_STATE = 3;
    public int REFRESHING_STATE = 4;
    public int LISTVIEW_STATE = 5;
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

        onListView();
    }
    TextView mTextDesc;
    private void fillLayout(){
        setOrientation(VERTICAL);
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh_header,this);
        mTextDesc = (TextView) findViewById(R.id.text_desc);
        post(new Runnable() {
            @Override
            public void run() {
                heigthPx = getHeight();
                setBottomPadding(-heigthPx);
            }
        });
    }
    private void setBottomPadding(int padding){
        setPadding(getPaddingLeft(),getPaddingTop(),getPaddingRight(),padding);
    }
    IPullToRefreshable  mOwner;
    public void setListView(IPullToRefreshable refreshable){
        mOwner = refreshable;
        mOwner.addHeaderView(this);
        InitialStateLayout();
    }
    private boolean isFrsitItme(ListView listView){
        return listView.getFirstVisiblePosition() == 1;
    }
    float startY = 0;
    float currentY = 0;
    public boolean onContentTouchEvent(MotionEvent ev){
        boolean result = true;
        if(MotionEvent.ACTION_DOWN == ev.getAction())
        {
            startY = ev.getY();
        }
        if(state == LISTVIEW_STATE){
            ListviewStateHandler(ev);
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

    private void ListviewStateHandler(MotionEvent ev){
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
                    onReleaseRefresh();
                }
                setBottomPadding(newPadding);
                break;
            case MotionEvent.ACTION_UP:

                    onListView();
                    paddingAnimation(getPaddingBottom(),-heigthPx);
                /*if( getPaddingBottom() == -heigthPx){
                    state = LISTVIEW_STATE;
                }else{
                }*/
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
                onRefreshing();
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
        mTextDesc.setText("下拉刷新");
    }
    void onReleaseRefresh(){
        state = RELEASE_REFRESH_STATE;
        mTextDesc.setText("释放刷新");
    }
    void onRefreshing(){
        state = REFRESHING_STATE;
        mTextDesc.setText("正在刷新");
        if(listener!=null){
            listener.onRefreshing();
        }
    }
    void onListView(){
        state = LISTVIEW_STATE;
        mTextDesc.setText("下拉刷新");
    }
    public void onRefreshComplete(){
        state = LISTVIEW_STATE;
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
