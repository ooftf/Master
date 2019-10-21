package com.ooftf.master.debug.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.R2;
import com.ooftf.progress.EnlargeHorizontalProgressDrawable;
import com.ooftf.progress.FlowHorizontalProgressDrawable;
import com.ooftf.progress.GradualHorizontalProgressDrawable;
import com.ooftf.progress.ShuntHorizontalProgressDrawable;
import com.ooftf.service.base.BaseBarrageActivity;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
@Route(path = "/debug/activity/ConstraintLayout")
public class ConstraintLayoutActivity extends BaseBarrageActivity {
    @BindView(R2.id.button0)
    Button button0;
    @BindView(R2.id.button1)
    Button button1;
    @BindView(R2.id.button2)
    Button button2;
    @BindView(R2.id.button3)
    Button button3;

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
        button0.post(() -> {
            FlowHorizontalProgressDrawable circularProgressDrawable = new FlowHorizontalProgressDrawable(this, button0);
            circularProgressDrawable.setIntrinsicWidth(button0.getMeasuredWidth());
            circularProgressDrawable.start();
            button0.setCompoundDrawablesWithIntrinsicBounds(null, null, null, circularProgressDrawable);
        });

        button1.post(() -> {
            EnlargeHorizontalProgressDrawable horizontalProgressDrawable = new EnlargeHorizontalProgressDrawable(this, button1);
            horizontalProgressDrawable.setIntrinsicWidth(button1.getMeasuredWidth());
            horizontalProgressDrawable.start();
            button1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable);
        });
        button2.post(() -> {
            GradualHorizontalProgressDrawable horizontalProgressDrawable = new GradualHorizontalProgressDrawable(this, button2);
            horizontalProgressDrawable.setIntrinsicWidth(button2.getMeasuredWidth());
            horizontalProgressDrawable.start();
            button2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, horizontalProgressDrawable);
        });
        button3.post(() -> {
            ShuntHorizontalProgressDrawable drawable = new ShuntHorizontalProgressDrawable(this, button3);
            drawable.setIntrinsicWidth(button3.getMeasuredWidth());
            drawable.start();
            drawable.setDuration(1000);
            button3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        });


    }
}
