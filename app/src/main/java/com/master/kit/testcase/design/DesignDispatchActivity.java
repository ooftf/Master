package com.master.kit.testcase.design;

import android.os.Bundle;
import android.view.View;

import com.master.kit.R;
import com.master.kit.testcase.design.flexible.FlexibleActivity;

import tf.ooftf.com.service.base.BaseSlidingActivity;

public class DesignDispatchActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_dispatch);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.DesignActivity:
                startActivity(DesignActivity.class);
                break;
            case R.id.TabActivity:
                break;
            case R.id.FlexibleActivity:
                startActivity(FlexibleActivity.class);
                break;
        }
    }
}
