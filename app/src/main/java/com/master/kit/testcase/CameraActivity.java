package com.master.kit.testcase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import tf.oof.com.service.base.BaseActivity;
import com.master.kit.R;

import com.master.kit.utils.CameraHelper;
import tf.oof.com.service.utils.BitmapUtils;
import tf.oof.com.service.utils.LogUtil;


import java.io.File;

public class CameraActivity extends BaseActivity {

    private ImageView imageView;
    CameraHelper cameraHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraHelper = new CameraHelper(CameraActivity.this,100);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraHelper.openCamera();
            }
        });
        imageView = (ImageView) findViewById(R.id.iv_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cameraHelper.handleActivityResult(requestCode, resultCode,new CameraHelper.Callbacks() {
            @Override
            public void onImagePicked(File imageFile) {
                imageView.setImageBitmap(BitmapUtils.readBitmapFromPath(imageFile.getPath(),800*800));
            }

            @Override
            public void onImagePickerError(Exception e) {
                LogUtil.e("我错了");
            }

            @Override
            public void onCanceled() {
                LogUtil.e("你居然干了这种事情");
            }
        });
    }

}
