package com.master.kit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.kit.R;
import com.master.kit.adapter.MainRecyclerAdapter;
import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseFragment;
import com.master.kit.testcase.DialogDemo;
import com.master.kit.testcase.KeyBoardActivity;
import com.master.kit.testcase.NewInstanceActivity;
import com.master.kit.testcase.listview.ListViewActivity;
import com.master.kit.testcase.net.NetActivity;
import com.master.kit.testcase.pulltorefresh.PullToRefreshActivity;
import com.master.kit.testcase.touchevent.TouchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/26 0026.
 */

public class DebugFragment extends BaseHomeFragment {

    public static DebugFragment newInstance() {
        Bundle args = new Bundle();
        DebugFragment fragment = new DebugFragment();
        fragment.setArguments(args);
        return fragment;
    }

    protected void initData() {
        adapter.add(NetActivity.class);
        adapter.add(NewInstanceActivity.class);
        adapter.add(DialogDemo.class);
        adapter.add(PullToRefreshActivity.class);
        adapter.add(KeyBoardActivity.class);
        adapter.add(ListViewActivity.class);
        adapter.add(TouchActivity.class);
        adapter.notifyDataSetChanged();
    }
}
