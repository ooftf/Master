package com.master.kit.testcase.listview;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.master.kit.R;
import tf.oof.com.service.base.BaseSlidingActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseSlidingActivity {
    BaseAdapter adapter;
    private ListView listView;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.lv_main);
        list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("第"+i+"个");
        }
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) convertView;
                if(textView == null){
                    textView = new TextView(ListViewActivity.this);
                    ListView.LayoutParams params = new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);

                    textView.setLayoutParams(params);
                    textView.setGravity(Gravity.CENTER);
                }
                textView.setText(list.get(position));
                return textView;
            }
        };
        listView.setAdapter(adapter);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int i = listView.getFirstVisiblePosition();
              /*  list.add("5");
                list.remove(1);*/
                list = new ArrayList<>();
                for(int i=0;i<90;i++){
                    list.add("第"+(i+1)+"个");
                }
                adapter.notifyDataSetChanged();
                //adapter.notifyDataSetInvalidated();
              //  listView.setVerticalScrollbarPosition(i);
            }
        });
    }
}
