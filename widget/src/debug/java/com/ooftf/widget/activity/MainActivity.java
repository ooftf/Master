package com.ooftf.widget.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.widget.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, (Fragment) ARouter.getInstance().build("/widget/widget").navigation())
                .commitAllowingStateLoss();
    }
}
