package com.master.kit.testcase.design.tab;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.kit.R;

/**
 * Created by master on 2016/6/3.
 */
public class TabFragment extends Fragment {


    private RecyclerView recyclerView;
    private String data;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        data = getActivity().getResources().getString(R.string.default_long);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) getView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerView.Adapter<Holder>() {
            LayoutInflater inflater;

            {
                inflater = LayoutInflater.from(getActivity());
            }

            @Override
            public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
                Holder holder = new Holder(inflater.inflate(R.layout.viewpager_card, parent, false));
                return holder;
            }

            @Override
            public void onBindViewHolder(Holder holder, int position) {
                holder.textView.setText(data);
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
