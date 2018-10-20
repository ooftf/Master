package com.ooftf.master.debug.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.R2;
import com.ooftf.master.debug.engine.layoutmanager.CustomLayoutManager;
import com.ooftf.service.utils.JLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
@Route(path = "/debug/activity/LayoutManager")
public class LayoutManagerActivity extends Activity {
    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        ButterKnife.bind(this);
        JLog.e(this, getWindow().getDecorView().toString());
        recyclerView.setLayoutManager(new CustomLayoutManager());
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.item_text, parent, false)) {

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                textView.setText(String.valueOf(position));
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });
    }
}
