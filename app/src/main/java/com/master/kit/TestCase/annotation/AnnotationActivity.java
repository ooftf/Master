package com.master.kit.testcase.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.master.kit.R;

import org.w3c.dom.Text;

public class AnnotationActivity extends AppCompatActivity {
    @Myannotation(sssssss = R.id.text)
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        AnnotationInject.bindView(this);
        textView.setText("啊哈，注解成功");
    }
}
