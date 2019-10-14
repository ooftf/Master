package com.ooftf.widget.modules.design;

import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.widget.Toolbar;
import com.ooftf.service.base.BaseSlidingActivity;
import com.ooftf.widget.R;

/**
 * @author 99474
 */
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
        toolbar.setTitle("我只是个熊啊");
        //collapsingToolbarLayout.setTitle("我们都是熊啊");
        floatingActionButton.setOnClickListener(v -> Snackbar.make(coordinatorLayout, "不要啊", Snackbar.LENGTH_LONG).show());
        // collapsingToolbarLayout.setContentScrimColor(Color.parseColor("#ff0000"));
       /* toolbar.setBackgroundColor(Color.parseColor("#00ff00"));
        toolbar.setDrawingCacheBackgroundColor(Color.parseColor("#00ff00"));*/
    }

    private void initViews() {
        appBarLayout = findViewById(R.id.main_appbar);
        coordinatorLayout = findViewById(R.id.main_CoordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        floatingActionButton = findViewById(R.id.main_fab);
    }
}
