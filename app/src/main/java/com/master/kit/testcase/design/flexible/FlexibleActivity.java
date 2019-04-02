package com.master.kit.testcase.design.flexible;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.master.kit.R;
import com.ooftf.service.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
public class FlexibleActivity extends BaseActivity {

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
        mainAppbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percentage = -verticalOffset * 100f / appBarLayout.getTotalScrollRange();
            if (fabIsShow && percentage > 50) {
                fabIsShow = false;
                fab.animate().scaleX(0).scaleY(0).setDuration(200).start();
            }
            if (!fabIsShow && percentage < 50) {
                fabIsShow = true;
                fab.animate().scaleX(1).scaleY(1).setDuration(200).start();
            }
        });
    }

    private void initToolbar() {
        //  toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        // toolbar.setTitle("臣服吧，你们这些小标题");
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        toolbar.setNavigationContentDescription("badk");
    }
}
