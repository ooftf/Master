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
import com.master.kit.base.BaseActivity;
import com.master.kit.base.BaseFragment;
import com.master.kit.testcase.AlbumActivity;
import com.master.kit.testcase.CameraActivity;
import com.master.kit.testcase.annotation.AnnotationActivity;
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/26 0026.
 */

public class OtherFragment extends BaseFragment {
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
    public static OtherFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initData() {
        adapter.add(AlbumActivity.class);
        adapter.add(AnnotationActivity.class);
        adapter.add(CameraActivity.class);
        adapter.add(SoftKeyboardActivity.class);
        adapter.notifyDataSetChanged();
    }
}
