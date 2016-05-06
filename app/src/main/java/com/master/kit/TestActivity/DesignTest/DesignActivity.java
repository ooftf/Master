package com.master.kit.TestActivity.DesignTest;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.master.kit.R;

public class DesignActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
       // setSupportActionBar(toolbar);
       // toolbar.inflateMenu();
        toolbar.setTitle("我只是个熊啊");
       /* toolbar.setBackgroundColor(Color.parseColor("#00ff00"));
        toolbar.setDrawingCacheBackgroundColor(Color.parseColor("#00ff00"));*/

    }
}
