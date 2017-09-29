package com.master.kit.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseFragment;

/**
 * Created by master on 2017/9/29 0029.
 */

abstract public class BaseHomeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    MainRecyclerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_widget, container,false);
        unbinder = ButterKnife.bind(this,view );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    private void setupRecyclerView() {
        adapter = new MainRecyclerAdapter((BaseActivity) getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(new ColorDrawable(Color.GRAY));
        recyclerView.addItemDecoration(divider);
    }
    abstract protected void initData();
}
