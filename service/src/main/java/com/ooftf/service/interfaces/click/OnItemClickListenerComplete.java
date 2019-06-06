package com.ooftf.service.interfaces.click;

public interface OnItemClickListenerComplete<V, T> {
    void onItemClick(V view, int position, T data);
}
