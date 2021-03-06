package com.ooftf.widget.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseListActivity;
import com.ooftf.service.base.adapter.ActivityIntentListAdapter;
import com.ooftf.service.bean.ActivityItemBean;
import com.ooftf.widget.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author ooftf
 */
@Route(path = "/widget/activity/slidingList")
public class SlidingListActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setListData(@NotNull ActivityIntentListAdapter adapter) {
        adapter.add(new ActivityItemBean("/widget/activity/slidingTextView", "折叠布局-TextView", "用于查看SlidingLayout在TextView下面的表现", R.drawable.logo_legacy, false));
        adapter.add(new ActivityItemBean("/widget/activity/slidingRecycleView", "折叠布局-RecycleView", "用于查看SlidingLayout在RecycleView下面的表现", R.drawable.logo_legacy, false));
    }
}
