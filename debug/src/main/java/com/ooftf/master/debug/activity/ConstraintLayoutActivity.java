package com.ooftf.master.debug.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.service.base.BaseBarrageActivity;

import org.jetbrains.annotations.Nullable;

@Route(path = "/debug/ConstraintLayoutActivity")
public class ConstraintLayoutActivity extends BaseBarrageActivity{
    View button0;
    View button1;
    View button2;
    View button3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_constraint_practice);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("button0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("button1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("button2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBarrage("button3");
            }
        });
    }
}
