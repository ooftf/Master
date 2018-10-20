package com.ooftf.widget.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.base.BaseBarrageActivity;
import com.ooftf.service.widget.toolbar.TailoredToolbar;
import com.ooftf.widget.R;

/**
 * @author lihang9
 * @date 2018-8-27 15:19:49
 */
@Route(path = "/widget/activity/toolbarDemo")
public class ToolbarDemoActivity extends BaseBarrageActivity {
    TailoredToolbar tailoredToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
        tailoredToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(tailoredToolbar);
        setTitle("标题");
        tailoredToolbar.addMenuItem(new TailoredToolbar.MenuItem(this).layoutRight().setImage(R.drawable.vector_refresh).setOnClickListenerChain(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("vector_refresh");
            }
        }));
        tailoredToolbar.addMenuItem(new TailoredToolbar.MenuItem(this).layoutRight().setText("刷新").setOnClickListenerChain(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("刷新");
            }
        }));
        tailoredToolbar.addMenuItem(new TailoredToolbar.MenuItem(this).layoutLeft().setImage(R.drawable.vector_icon_del));
        tailoredToolbar.addMenuItem(new TailoredToolbar.MenuItem(this).layoutLeft().setText("关闭"));
    }
}
