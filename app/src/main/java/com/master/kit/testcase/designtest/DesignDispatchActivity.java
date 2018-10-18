package com.master.kit.testcase.designtest;

import android.os.Bundle;
import android.view.View;

import com.master.kit.R;
import com.master.kit.testcase.design.DesignActivity;
import com.ooftf.service.base.BaseSlidingActivity;

public class DesignDispatchActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_dispatch);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.DesignActivity:
                startActivity(DesignActivity.class);
                break;
            case R.id.TabActivity:
                startActivity(TabActivity.class);
                break;
            default:
        }
    }
}
