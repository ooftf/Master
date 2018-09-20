package com.ooftf.master.debug.engine.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import hugo.weaving.DebugLog;

/**
 * @author ooftf
 * @date 2018/9/20 0020
 * @desc
 **/
@DebugLog
public class CustomLayoutManager extends RecyclerView.LayoutManager{
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

    }
}
