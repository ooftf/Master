package com.master.kit.testcase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.master.kit.R;
import tf.oof.com.service.base.BaseSlidingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInstanceActivity extends BaseSlidingActivity {

    @BindView(R.id.click)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_instance);
        ButterKnife.bind(this);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.class);
            }
        });
    }
}
