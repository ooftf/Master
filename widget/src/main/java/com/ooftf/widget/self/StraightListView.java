package com.ooftf.widget.self;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class StraightListView extends LinearLayout {
    RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    };

    public StraightListView(Context context) {
        super(context);
    }

    public StraightListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StraightListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StraightListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    RecyclerView.Adapter adapter;

    void setAdapter(RecyclerView.Adapter adapter) {
        if (this.adapter != null) {
            this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        this.adapter = adapter;
        this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
        reLayoutChild();
    }

    private void reLayoutChild() {
        while (adapter.getItemCount()<getChildCount()){
            removeViewAt(getChildCount()-1);
        }
        while (adapter.getItemCount()>getChildCount()){
            addView(adapter.createViewHolder(this,ViewType).itemView);
        }
    }
    int ViewType = 0;
}
