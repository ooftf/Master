package com.master.kit.base;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.kit.utils.LogUtil;

/**
 * Created by master on 2016/4/12.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        LogUtil.e(getClass().getSimpleName(),"onAttach");
        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.e(getClass().getSimpleName(),"onCreate          "+ savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e(getClass().getSimpleName(),"onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.e(getClass().getSimpleName(),"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogUtil.e(getClass().getSimpleName(),"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtil.e(getClass().getSimpleName(),"onResume");
        super.onResume();
    }
    @Override
    public void onPause() {
        LogUtil.e(getClass().getSimpleName(),"onPause");
        super.onPause();
    }
    @Override
    public void onStop() {
        LogUtil.e(getClass().getSimpleName(),"onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtil.e(getClass().getSimpleName(),"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtil.e(getClass().getSimpleName(),"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtil.e(getClass().getSimpleName(),"onDetach");
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtil.e(getClass().getSimpleName(),"onSaveInstanceState     "+outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.e(getClass().getSimpleName(),"onHiddenChanged     "+hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.e(getClass().getSimpleName(),"onConfigurationChanged     "+newConfig);
        super.onConfigurationChanged(newConfig);
    }
}
