package com.ooftf.widget.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseSlidingActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.widget.R;

/**
 * @author ooftf
 */
@Route(path = RouterPath.WIDGET_PROGRESS_BAR)
public class ProgressBarActivity extends BaseSlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpb);
    }
}
