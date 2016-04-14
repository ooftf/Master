package com.master.kit.TestActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.master.kit.R;
import com.master.kit.widget.VerticalPageLayout.VerticalPageLayout;

public class PageLayoutActivity extends AppCompatActivity {
    VerticalPageLayout mPageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_layout);
        mPageLayout = (VerticalPageLayout) findViewById(R.id.pl_main);
        mPageLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
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
                if(convertView == null){
                    convertView = new TextView(PageLayoutActivity.this);
                }
                ((TextView)convertView).setText("第"+position+"个视图:+positionposition"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position+"aaaaa"+position);
                return convertView;
            }
        });
    }
}
