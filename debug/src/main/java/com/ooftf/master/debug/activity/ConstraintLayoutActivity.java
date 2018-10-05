package com.ooftf.master.debug.activity;

import android.os.Bundle;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.R2;
import com.ooftf.master.debug.widget.HorizontalProgressDrawable;
import com.ooftf.master.debug.widget.HorizontalThreeProgressDrawable;
import com.ooftf.master.debug.widget.HorizontalTwoProgressDrawable;
import com.ooftf.service.base.BaseBarrageActivity;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
@Route(path = "/debug/ConstraintLayoutActivity")
public class ConstraintLayoutActivity extends BaseBarrageActivity {
    @BindView(R2.id.button0)
    Button button0;
    @BindView(R2.id.button1)
    Button button1;
    @BindView(R2.id.button2)
    Button button2;
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
        button0.post(() -> {

            HorizontalTwoProgressDrawable circularProgressDrawable = new HorizontalTwoProgressDrawable(this, button0);
            circularProgressDrawable.setIntrinsicWidth(button0.getMeasuredWidth());
            circularProgressDrawable.start();
            button0.setCompoundDrawablesWithIntrinsicBounds(null, circularProgressDrawable, null, null);
        });

        button1.post(() -> {
            HorizontalProgressDrawable horizontalProgressDrawable = new HorizontalProgressDrawable(this, button1);
            horizontalProgressDrawable.setIntrinsicWidth(button1.getMeasuredWidth());
            horizontalProgressDrawable.start();
            HorizontalThreeProgressDrawable drawable = new HorizontalThreeProgressDrawable(this, button1);
            drawable.setIntrinsicWidth(button1.getMeasuredWidth());
            drawable.start();
            button1.setCompoundDrawablesWithIntrinsicBounds(null, horizontalProgressDrawable, null, drawable);
        });
        button2.post(() -> {
            HorizontalProgressDrawable horizontalProgressDrawable = new HorizontalProgressDrawable(this, button2);
            horizontalProgressDrawable.setIntrinsicWidth(button2.getMeasuredWidth());
            horizontalProgressDrawable.start();
            button2.setCompoundDrawablesWithIntrinsicBounds(null, horizontalProgressDrawable, null, null);
        });


    }
}
