package com.master.kit.testcase.pulltorefresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.master.kit.R;
import com.ooftf.pulltorefresh.widget.PullToLoadMoreFooter;
import com.ooftf.pulltorefresh.widget.PullToRefreshRoot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullToRefreshActivity extends AppCompatActivity {


    Handler handler;
    MyAdapter myAdapter;
    @BindView(R.id.main_list_view)
    ListView mainListView;
    @BindView(R.id.main_pull_to_refresh_root)
    PullToRefreshRoot mainPullToRefreshRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
        ButterKnife.bind(this);
        myAdapter = new MyAdapter(this);
        mainListView.setAdapter(myAdapter);
        fillData();
        handler = new Handler();
        final PullToLoadMoreFooter pullToLoadMoreFooter = new PullToLoadMoreFooter(this);
        pullToLoadMoreFooter.setListView(mainListView);
        pullToLoadMoreFooter.setOnLoadingMoreListener(new PullToLoadMoreFooter.OnLoadingMoreListener() {
            @Override
            public void OnLoadingMore() {
                simulateLoadingMore(pullToLoadMoreFooter);
            }
        });
        mainPullToRefreshRoot.setOnRefreshListener(new PullToRefreshRoot.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                simulatePullDownRefresh(pullToLoadMoreFooter);
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("OnItemClickListener", "position::" + position);
            }
        });

    }

    private void simulatePullDownRefresh(final PullToLoadMoreFooter pullToLoadMoreFooter) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainPullToRefreshRoot.onRefreshComplete();
                pullToLoadMoreFooter.hasMore();
            }
        }, 5000);
    }

    private void simulateLoadingMore(final PullToLoadMoreFooter pullToLoadMoreFooter) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fillData();
                pullToLoadMoreFooter.loadingComplete();
            }
        }, 5000);
    }

    private void fillData() {
        for (int i = 0; i < 100; i++) {
            myAdapter.getDatas().add(i + "");
        }
    }

    class MyAdapter extends BaseAdapter {
        List<String> datas;
        Context context;

        MyAdapter(Context context) {
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
            if (view == null) {
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
