package com.ooftf.service.engine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/13 0013
 */
public class LazyFragmentProxy<T extends Fragment & LazyFragmentProxy.LazyFragmentOwner> {
    T fragment;
    WeakReference<View> rootViewReference;

    public LazyFragmentProxy(T fragment) {
        this.fragment = fragment;
    }

    private boolean isLoaded = false;

    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragment.lazyEnabled()) {
            View view = null;
            if (rootViewReference != null) {
                view = rootViewReference.get();
            }
            if (view == null) {
                view = fragment.getView();
                rootViewReference = new WeakReference<>(view);
            }
            if (view == null) {
                isLoaded = false;
                view = fragment.getContentView(inflater, container);
                rootViewReference = new WeakReference<>(view);
            }
            return view;
        } else {
            if (fragment.getView() == null) {
                return fragment.getContentView(inflater, container);
            }
            return fragment.getView();
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        } else {
            fragment.onLoad(view);
        }
    }

    private void loadJudgment() {
        if (fragment.getView() != null && fragment.getUserVisibleHint() && !isLoaded && !fragment.isHidden() && fragment.isShowing()) {
            isLoaded = true;
            fragment.onLoad(fragment.getView());
        }
    }

    public void setUserVisibleHint(boolean visibleHint) {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        }

    }

    public void onStart() {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        }
    }

    public void onHiddenChanged() {
        if (fragment.lazyEnabled()) {
            loadJudgment();
        }
    }

    public void onDetach() {
        rootViewReference = null;
    }


    public interface LazyFragmentOwner {

        View getContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container);

        /**
         * 初始化界面
         */

        void onLoad(@NotNull View rootView);

        boolean lazyEnabled();

        boolean isShowing();
    }
}
