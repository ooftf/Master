package com.ooftf.pulltorefresh.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ooftf.kit.utils.DensityUtil;
import com.ooftf.pulltorefresh.R;

/**
 * Created by master on 2016/9/22.
 */

public class PullToLoadMoreFooter extends RelativeLayout {

    private AbsListView.OnScrollListener scrollListener;

    public PullToLoadMoreFooter(Context context) {
        super(context);
        init();
    }


    public PullToLoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PullToLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    int heightPx;

    private void init() {
        heightPx = DensityUtil.dip2px(getContext(), 56);
        fillLayout();
        scrollListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (SCROLL_STATE_IDLE == scrollState && state == PULL_UP_TO_LOAD_STATE) {
                    onLoadingMoreState();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && state == HIDE_STATE) {
                    onPullUpToLoadState();
                }
                if (firstVisibleItem + visibleItemCount < totalItemCount && state == PULL_UP_TO_LOAD_STATE) {
                    onHideState();
                }
            }
        };
    }

    TextView mTextDesc;

    private void fillLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_load_more_footer, this, true);
        setGravity(Gravity.CENTER);
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
        mTextDesc = (TextView) findViewById(R.id.text_desc);
    }

    int state;
    int HIDE_STATE = 0;//没有显示的状态
    int PULL_UP_TO_LOAD_STATE = 1; //释放加载更多的状态
    int LOADING_STATE = 2; // 正在加载状态
    ListView listView;

    public void setListView(ListView listView) {
        this.listView = listView;
        listView.setOnScrollListener(scrollListener);
        listView.addFooterView(this);
        noMore();
    }

    private void onHideState() {
        Log.e("", "onHideState");
        state = HIDE_STATE;
        mTextDesc.setText("看不见我");
    }

    private void onPullUpToLoadState() {
        Log.e("", "onPullUpToLoadState");
        state = PULL_UP_TO_LOAD_STATE;
        mTextDesc.setText("松开加载更多");
    }

    private void onLoadingMoreState() {
        Log.e("", "onLoadingMoreState");
        state = LOADING_STATE;
        loadingMoreListener.OnLoadingMore();
        mTextDesc.setText("正在加载");
    }

    public void loadingComplete() {
        onHideState();
    }
    public void noMore(){
        listView.removeFooterView(this);
        listView.setOnScrollListener(null);

    }
    public void hasMore(){
        listView.removeFooterView(this);
        listView.addFooterView(this);
        listView.setOnScrollListener(scrollListener);
    }
    OnLoadingMoreListener loadingMoreListener;

    public void setOnLoadingMoreListener(OnLoadingMoreListener listener) {
        this.loadingMoreListener = listener;
    }

    public interface OnLoadingMoreListener {
        void OnLoadingMore();
    }
}
