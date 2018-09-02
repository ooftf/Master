package com.ooftf.master.debug.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;

/**
 * @author 99474
 */
@Route(path = "/debug/AcrossRecyclerActivity")
public class AcrossRecyclerActivity extends AppCompatActivity {
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_across_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 1) {
                    RecyclerView recyclerView = new RecyclerView(AcrossRecyclerActivity.this);
                    recyclerView.setAdapter(new RecyclerView.Adapter() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            TextView textView = new TextView(AcrossRecyclerActivity.this);
                            textView.setPadding(10, 10, 10, 10);
                            return new RecyclerView.ViewHolder(textView) {
                            };
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                            Log.e("onBindViewHolder", "ChildeenRecyclerView"+position);
                            ((TextView) holder.itemView).setText(position + "");
                        }

                        @Override
                        public int getItemCount() {
                            return 100;
                        }
                    });
                    recyclerView.setLayoutManager(new LinearLayoutManager(AcrossRecyclerActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    return new RecyclerView.ViewHolder(recyclerView) {

                    };
                } else {
                    TextView textView = new TextView(AcrossRecyclerActivity.this);
                    textView.setPadding(10, 10, 10, 10);
                    return new RecyclerView.ViewHolder(textView) {

                    };

                }

            }

            @Override
            public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
                int itemViewType = getItemViewType(position);
                if (itemViewType == 1) {
                    Log.e("onBindViewHolder", "RecyclerView");
                    holder.itemView.post(new Runnable() {
                        @Override
                        public void run() {
                            ((RecyclerView)holder.itemView).getAdapter().notifyDataSetChanged();
                        }
                    });


                } else {
                    ((TextView) holder.itemView).setText(position + "");
                }
            }

            @Override
            public int getItemCount() {
                return 1000;
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 20) {
                    return 1;
                }
                return 0;
            }
        });
    }
}
