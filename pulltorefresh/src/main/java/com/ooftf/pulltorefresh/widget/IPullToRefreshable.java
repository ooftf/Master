package com.ooftf.pulltorefresh.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by master on 2016/9/21.
 */

public interface IPullToRefreshable {
    /**
     * 用户判断界面是否滑动到最顶端
     * @return
     */
    boolean isAtTop();
    void addHeaderView(View v);

    /**
     *
     * @return 获取一个header 父控件的LayoutParams实例
     */
    ViewGroup.LayoutParams getChildLayoutParams();
    void setOnRefreshListener(PullToRefreshHeader.OnRefreshListener listener);
    void onRefreshComplete();
}

