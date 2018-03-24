package com.master.kit.activity.widget;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.master.kit.R;
import com.ooftf.service.base.BaseSlidingActivity;

@Route(path = "/main/progressBar")
public class ProgressBarActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpb);
    }
}