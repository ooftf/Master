package com.ooftf.service.widget.dialog;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ooftf.service.R;
import com.ooftf.service.base.adapter.BaseViewHolder;
import com.ooftf.service.databinding.DialogGridPanelBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter;

public class GridPanelDialog<T> extends BottomDialog {
    private DialogGridPanelBinding binding;
    private TheAdapter adapter;
    private DialogItemAdapter<T> itemAdapter;

    public GridPanelDialog(@NotNull Activity activity) {
        super(activity);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_grid_panel, (ViewGroup) getWindow().getDecorView(), true);
        setWidthPercent(1);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapter = new TheAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    public GridPanelDialog<T> setItemAdapter(DialogItemAdapter<T> adapter) {
        this.itemAdapter = adapter;
        return this;
    }

    public GridPanelDialog<T> setList(List<T> data) {
        adapter.setList(data);
        adapter.notifyDataSetChanged();
        return this;
    }
    private class TheAdapter extends BaseRecyclerAdapter<T, TheViewHolder> {


        @NonNull
        @Override
        public TheViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new TheViewHolder(R.layout.item_dialog_grid_panel, viewGroup);
        }

        @Override
        public void onBindViewHolder_(@NonNull TheViewHolder viewHolder, int position) {
            if (itemAdapter != null) {
                itemAdapter.onBindView(getItem(position), position, viewHolder.imageView, viewHolder.textView, viewHolder.itemView, GridPanelDialog.this);
            }
        }
    }

    private static class TheViewHolder extends BaseViewHolder {
        ImageView imageView;
        TextView textView;

        public TheViewHolder(int layoutId, ViewGroup parent) {
            super(layoutId, parent);
            imageView = getItemView().findViewById(R.id.image);
            textView = getItemView().findViewById(R.id.text);
        }

    }

    public interface DialogItemAdapter<T> {
        void onBindView(T item, int position, ImageView icon, TextView textView, View itemRoot, GridPanelDialog dialog);
    }

}
