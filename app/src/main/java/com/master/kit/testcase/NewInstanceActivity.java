package com.master.kit.testcase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.master.kit.R;
import com.master.kit.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInstanceActivity extends BaseActivity {

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
