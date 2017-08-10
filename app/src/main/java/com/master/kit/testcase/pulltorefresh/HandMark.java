package com.master.kit.testcase.pulltorefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.master.kit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandMark extends AppCompatActivity {

    @BindView(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @BindView(R.id.click)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handmark);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HandMarkFragment()).commit();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HandMarkFragment()).commit();
            }
        });
    }
}
