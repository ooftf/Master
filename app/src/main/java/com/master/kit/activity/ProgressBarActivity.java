package com.master.kit.activity;

import android.os.Bundle;

import com.master.kit.R;

import butterknife.ButterKnife;
import tf.oof.com.service.base.BaseSlidingActivity;

public class ProgressBarActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpb);
    }
}
