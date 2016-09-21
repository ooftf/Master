package com.ooftf.pulltorefresh.widget;

import android.view.View;

/**
 * Created by master on 2016/9/21.
 */

public interface IPullToRefreshable {
    boolean isAtTop();
    void addHeaderView(View v);
    void setPressed(boolean pressed);
}

