package com.ooftf.widget.modules.design;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.widget.R;
import com.ooftf.service.base.BaseSlidingActivity;

/**
 * @author 99474
 */
@Route(path = "/main/design")
public class DesignDispatchActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_dispatch);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.DesignActivity) {
            startActivity(DesignActivity.class);
        } else if (id == R.id.TabActivity) {
        } else if (id == R.id.FlexibleActivity) {
            startActivity(FlexibleActivity.class);
        }
    }
}
