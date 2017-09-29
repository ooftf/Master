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
import com.master.kit.testcase.AAEditTextActivity;
import com.master.kit.testcase.CalendarActivity;
import com.master.kit.testcase.GesturePasswordActivity;
import com.master.kit.testcase.PageLayoutActivity;
import com.master.kit.testcase.banner.BannerActivity;
import com.master.kit.testcase.cpb.CPBActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseFragment;

/**
 * Created by master on 2017/9/26 0026.
 */

public class AppFragment extends BaseFragment {
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
    }
    public static AppFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AppFragment fragment = new AppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initData() {
        adapter.add(CalendarActivity.class);
        adapter.add(PageLayoutActivity.class);
        adapter.add(CPBActivity.class);
        adapter.add(GesturePasswordActivity.class);
        adapter.add(BannerActivity.class);
        adapter.add(AAEditTextActivity.class);
        adapter.notifyDataSetChanged();
    }
}
