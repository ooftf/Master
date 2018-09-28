package com.ooftf.master.debug.activity;

import android.os.Bundle;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.R2;
import com.ooftf.service.base.BaseBarrageActivity;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
@Route(path = "/debug/ConstraintLayoutActivity")
public class ConstraintLayoutActivity extends BaseBarrageActivity{
    @BindView(R2.id.button0)
    View button0;
    @BindView(R2.id.button1)
    View button1;
    @BindView(R2.id.button2)
    View button2;
    @BindView(R2.id.button3)
    View button3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_constraint_practice);
        ButterKnife.bind(this);
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
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setCenterRadius(40);
        circularProgressDrawable.setProgressRotation(60);
        circularProgressDrawable.setStrokeWidth(5);
        circularProgressDrawable.start();
        button0.setBackgroundDrawable(circularProgressDrawable);

    }
}
