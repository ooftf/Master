package com.ooftf.master.debug.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.aspectj.Log;

@Route(path = "/debug/annotation")
@Log
public class AnnotationActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        /*AnnotationInject.bindView(this);
        textView.setText("啊哈，注解成功");*/
    }
}
