package com.ooftf.service.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/18 0018
 */
public class BaseViewHolder<V extends View> extends RecyclerView.ViewHolder {
    public BaseViewHolder(@LayoutRes int layoutId, ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    public BaseViewHolder(V view) {
        super(view);
    }

    public V getItemView() {
        return (V) itemView;
    }
}
