package com.ooftf.service.base.adapter;

/**
 * Created by master on 2017/10/18 0018.
 */

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by master on 2017/3/28.
 */

public abstract class SimpleAdapter<T> extends BaseAdapter {
    List<T> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        if (position < list.size()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<T> list) {
        this.list.addAll(list);
    }

    public void add(T item) {
        this.list.add(item);
    }

    public void clear() {
        list.clear();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
    }
}