package com.ooftf.service.engine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/13 0013
 */
public class LazyFragmentProxy<T extends Fragment & LazyFragmentProxy.LazyFragmentOwner> {
    T fragment;

    public LazyFragmentProxy(T fragment) {
        this.fragment = fragment;
    }

    private boolean isLoaded = false;

    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragment.lazyEnabled()) {
            if (fragment.getView() == null) {
                isLoaded = false;
                return inflater.inflate(fragment.getLayoutId(), container, false);
            }
            return fragment.getView();
        } else {
            if (fragment.getView() == null) {
                return inflater.inflate(fragment.getLayoutId(), container, false);
            }
            return fragment.getView();
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        } else {
            fragment.onLoad();
        }
    }

    private void loadJudgment() {
        if (fragment.getView() != null && fragment.getUserVisibleHint() && !isLoaded) {
            isLoaded = true;
            fragment.onLoad();
        }
    }

    public void setUserVisibleHint(boolean visibleHint) {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        }

    }


    public interface LazyFragmentOwner {
        /**
         * fragment 的布局文件
         *
         * @return
         */
        int getLayoutId();

        /**
         * 初始化界面
         */

        void onLoad();

        boolean lazyEnabled();
    }
}
