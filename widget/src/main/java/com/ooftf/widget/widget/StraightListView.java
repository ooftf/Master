package com.ooftf.widget.widget;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ooftf.widget.R;

/**
 * 内部只支持一种view
 */
public class StraightListView extends LinearLayout {
    RecyclerView.Adapter adapter;
    int ViewType = 0;
    RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            onDataSetChang();
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

    void setAdapter(RecyclerView.Adapter adapter) {
        if (this.adapter != null && this.adapter.hasObservers()) {
            this.adapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
        this.adapter = adapter;
        this.adapter.registerAdapterDataObserver(adapterDataObserver);
        removeAllViews();
        onDataSetChang();
    }

    void onDataSetChang() {
        while (adapter.getItemCount() < getChildCount()) {
            removeViewAt(getChildCount() - 1);
        }
        while (adapter.getItemCount() > getChildCount()) {
            RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(this, ViewType);
            viewHolder.itemView.setTag(R.id.tag_key_straight_holder, viewHolder);
            addView(viewHolder.itemView);
        }
        for (int i = 0; i < getChildCount(); i++) {
            adapter.onBindViewHolder((RecyclerView.ViewHolder) getChildAt(i).getTag(R.id.tag_key_straight_holder), i);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (adapter != null && adapter.hasObservers()) {
            adapter.unregisterAdapterDataObserver(adapterDataObserver);
        }
    }
}
