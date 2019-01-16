package com.master.kit.testcase.design.flexible;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.master.kit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlexibleActivity extends AppCompatActivity {

    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout mainCollapsing;
    @BindView(R.id.CardView)
    CardView cardView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_appbar)
    AppBarLayout mainAppbar;


    boolean fabIsShow = true;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible);
        ButterKnife.bind(this);
        initToolbar();
        addFabAnimate();
    }

    private void addFabAnimate() {
        mainAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float percentage = -verticalOffset * 100f / appBarLayout.getTotalScrollRange();
                if (fabIsShow && percentage > 50) {
                    fabIsShow = false;
                    fab.animate().scaleX(0).scaleY(0).setDuration(200).start();
                }
                if (!fabIsShow && percentage < 50) {
                    fabIsShow = true;
                    fab.animate().scaleX(1).scaleY(1).setDuration(200).start();
                }
            }
        });
    }

    private void initToolbar() {
        //  toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        // toolbar.setTitle("臣服吧，你们这些小标题");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setNavigationContentDescription("badk");
    }
}
