package com.ooftf.service.widget.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ooftf.service.R;
import com.ooftf.service.R2;
import com.ooftf.service.base.adapter.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tf.ooftf.com.service.base.adapter.BaseRecyclerAdapter;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/17 0017
 */
public class ListDialog extends BottomDialog {
    @BindView(R2.id.cancel)
    TextView cancel;
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.cancel_group)
    View cancelGroup;
    InnerAdapter adapter;
    private DialogOnItemClickListener itemClickListener;

    public ListDialog(@NotNull Activity activity) {
        super(activity);
        setContentView(R.layout.dialog_list_selector);
        ButterKnife.bind(this);
        setGravity(Gravity.BOTTOM);
        cancel.setOnClickListener(v -> dismiss());
        adapter = new InnerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter.setOnItemClickListener((data, position) -> {
            if (itemClickListener != null) {
                itemClickListener.OnItemClick(data, position, ListDialog.this);
            }
        });
    }

    public ListDialog setShowCancel(boolean show) {
        if (show) {
            cancelGroup.setVisibility(View.VISIBLE);
        } else {
            cancelGroup.setVisibility(View.GONE);
        }
        return this;
    }

    public ListDialog setList(List<String> data) {
        adapter.setList(data);
        adapter.notifyDataSetChanged();
        return this;
    }

    public ListDialog setOnItemClickListener(DialogOnItemClickListener listener) {
        itemClickListener = listener;
        return this;
    }

    private static class InnerAdapter extends BaseRecyclerAdapter<String, BaseViewHolder<TextView>> {


        @NonNull
        @Override
        public BaseViewHolder<TextView> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BaseViewHolder<>(R.layout.item_list_selector_dialog, viewGroup);
        }

        @Override
        public void onBindViewHolder_(@NonNull BaseViewHolder<TextView> viewHolder, int position) {
            viewHolder.getItemView().setText(getItem(position));
        }
    }

    public interface DialogOnItemClickListener {
        void OnItemClick(String item, int position, ListDialog dialog);
    }
}
