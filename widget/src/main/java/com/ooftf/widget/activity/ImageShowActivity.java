package com.ooftf.widget.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.widget.R;

@Route(path = RouterPath.Widget.Activity.IMAGE_SHOW)
public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
    }
}
