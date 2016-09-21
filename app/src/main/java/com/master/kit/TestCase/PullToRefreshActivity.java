package com.master.kit.testcase;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.master.kit.R;
import com.ooftf.pulltorefresh.widget.PullToRefreshHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.content;
import static android.R.id.list;

public class PullToRefreshActivity extends AppCompatActivity {

    @BindView(R.id.main_listview)
    com.ooftf.pulltorefresh.widget.PullToRefreshListView mainListview;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        MyAdapter myAdapter = new MyAdapter(this);
        mainListview.setAdapter(myAdapter);
        for(int i =0;i<100;i++){
            myAdapter.getDatas().add(i+"");
        }
        handler = new Handler();
        mainListview.setOnRefreshListener(new PullToRefreshHeader.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainListview.onRefreshComplete();
                    }
                },5000);
            }
        });
        mainListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("OnItemClickListener","position::"+position);
            }
        });
    }
    class MyAdapter extends BaseAdapter{
        List<String> datas;
        Context context;
        MyAdapter(Context context){
            this.context = context;
            datas = new ArrayList<>();
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) convertView;
            if(view == null){
                view = new TextView(context);
            }
            view.setText(datas.get(position));
            return view;
        }

        public List<String> getDatas() {
            return datas;
        }
    }

}
