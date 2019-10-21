package com.ooftf.service.structure.mvvm.ui;

import androidx.databinding.BindingAdapter;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/10/15
 */
public class SmartLayoutDataBindingAdapter {
    @BindingAdapter(value = "refreshState", requireAll = false)
    public static void setRefreshState(SmartRefreshLayout smartRefreshLayout, int state) {
        if (state == 0) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.resetNoMoreData();
        }
    }

    @BindingAdapter(value = "loadMoreState", requireAll = false)
    public static void setLoadMoreState(SmartRefreshLayout smartRefreshLayout, int state) {
        if (state == UIEvent.SMART_LAYOUT_LOADMORE_FINISH) {
            smartRefreshLayout.finishLoadMore();
        } else if (state == UIEvent.SMART_LAYOUT_LOADMORE_FINISH_AND_NO_MORE) {
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }
}
