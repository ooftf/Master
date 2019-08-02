package com.ooftf.service.widget.longclick;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/1 0001
 */
public interface OnLongClickListener {
    void onLongClickStart(View view, RecyclerView.Adapter adapter, int position);

    void onLongClickEnd(View view, RecyclerView.Adapter adapter, int position);
}
