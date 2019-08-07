package com.ooftf.widget.activity;

import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.widget.toolbar.custom.MasterToolbar;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.widget.R;
import com.ooftf.widget.R2;
import com.ooftf.widget.self.StateLayoutSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 状态切换布局的Demo页面
 *
 * @author 99474
 */
@Route(path = RouterPath.Widget.Activity.STATE_LAYOUT_SAMPLE)
public class StateLayoutSampleActivity extends BaseActivity {

    @BindView(R2.id.toolbar)
    MasterToolbar toolbar;
    @BindView(R2.id.switcherLayout)
    StateLayoutSwitcher switcherLayout;
    @BindView(R2.id.buttonNormal)
    Button buttonNormal;
    @BindView(R2.id.buttonError)
    Button buttonError;
    @BindView(R2.id.buttonEmpty)
    Button buttonEmpty;
    @BindView(R2.id.buttonLoading)
    Button buttonLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_layout_sample);
        ButterKnife.bind(this);
        buttonNormal.setOnClickListener(v -> switcherLayout.switchToSuccess());
        buttonError.setOnClickListener(v -> switcherLayout.switchToError());
        buttonEmpty.setOnClickListener(v -> switcherLayout.switchToEmpty());
        buttonLoading.setOnClickListener(v -> switcherLayout.switchToLoading());
    }
}
