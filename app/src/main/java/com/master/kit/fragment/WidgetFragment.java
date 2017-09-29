package com.master.kit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.kit.R;
import com.master.kit.adapter.MainRecyclerAdapter;
import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseFragment;
import com.master.kit.testcase.AAEditTextActivity;
import com.master.kit.testcase.CalendarActivity;
import com.master.kit.testcase.GesturePasswordActivity;
import com.master.kit.testcase.PageLayoutActivity;
import com.master.kit.testcase.banner.BannerActivity;
import com.master.kit.testcase.cpb.CPBActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/26 0026.
 */

public class WidgetFragment extends BaseHomeFragment {
    public static WidgetFragment newInstance() {

        Bundle args = new Bundle();

        WidgetFragment fragment = new WidgetFragment();
        fragment.setArguments(args);
        return fragment;
    }
    protected void initData() {
        adapter.add(CalendarActivity.class);
        adapter.add(PageLayoutActivity.class);
        adapter.add(CPBActivity.class);
        adapter.add(GesturePasswordActivity.class);
        adapter.add(BannerActivity.class);
        adapter.add(AAEditTextActivity.class);
        adapter.notifyDataSetChanged();
    }
}
