package com.ooftf.pulltorefresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ooftf.kit.utils.DensityUtil;
import com.ooftf.pulltorefresh.R;

import static com.ooftf.kit.utils.DensityUtil.dip2px;

/**
 * Created by master on 2016/9/22.
 */

public class PullToLoadMoreFooter extends LinearLayout {


    ProgressBar progressBar;
    private AbsListView.OnScrollListener scrollListener;

    public PullToLoadMoreFooter(Context context) {
        super(context);
        init();
    }


    public PullToLoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        fillLayout();
        scrollListener = new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /*if (SCROLL_STATE_IDLE == scrollState && state == LOADING_STATE) {
                    onLoadingMoreState();
                }*/
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && state == STATE_HIDE) {
                    onLoadingMoreState();
                }
            }
        };
    }

    TextView mTextDesc;

    private void fillLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_load_more_footer, this, true);

        setGravity(Gravity.CENTER);
        mTextDesc = (TextView) findViewById(R.id.text_desc);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
    int state;
    int STATE_HIDE = 0;//没有显示的状态
    int STATE_LOADING = 2; // 正在加载状态
    int STATE_NO_MORE = 3;
    ListView listView;

    /**
     * 在setAdapter 之前调用
     *
     * @param listView
     */
    public void setListView(ListView listView) {
        this.listView = listView;
        listView.setOnScrollListener(scrollListener);
        listView.addFooterView(this);
        noMore();
    }

    private void onHideState() {
        Log.e("", "onHideState");
        state = STATE_HIDE;
        mTextDesc.setText("上拉加载");
    }

    private void onLoadingMoreState() {
        Log.e("", "onLoadingMoreState");
        state = STATE_LOADING;
        if (loadingMoreListener != null) {
            loadingMoreListener.OnLoadingMore();
        }
        mTextDesc.setText("正在加载");
    }

    public void loadingComplete(boolean hasMore) {
        if (hasMore) {
            hasMore();
        } else {
            noMore();
        }
    }

    public void noMore() {
        //setHeight(0);
        for(int i = 0;i<getChildCount();i++){
            getChildAt(i).setVisibility(GONE);
        }
        state = STATE_NO_MORE;
    }
    public void hasMore() {
        for(int i =0;i<getChildCount();i++){
            getChildAt(i).setVisibility(VISIBLE);
        }
        onHideState();
    }

    OnLoadingMoreListener loadingMoreListener;

    public void setOnLoadingMoreListener(OnLoadingMoreListener listener) {
        this.loadingMoreListener = listener;
    }

    public interface OnLoadingMoreListener {
        void OnLoadingMore();
    }
}
