package com.master.kit.testcase.design;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.kit.R;
import tf.oof.com.service.base.BaseSlidingActivity;

public class DesignActivity extends BaseSlidingActivity {
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton floatingActionButton;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        initViews();
        //appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
       // toolbar.inflateMenu();
        toolbar.setTitle("我只是个熊啊");
       // collapsingToolbarLayout.setTitle("我们都是熊啊");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout,"不要啊",Snackbar.LENGTH_LONG).show();
            }
        });
       // collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#ff0000"));
       /* toolbar.setBackgroundColor(Color.parseColor("#00ff00"));
        toolbar.setDrawingCacheBackgroundColor(Color.parseColor("#00ff00"));*/
    }

    private void initViews() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_CoordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.main_fab);
    }
}
