package com.master.kit.testcase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.master.kit.base.BaseActivity;
import com.master.kit.R;
import com.master.kit.utils.AlbumUtil;
import com.master.kit.utils.BitmapUtils;
import com.master.kit.utils.CameraUtil;
import com.master.kit.utils.LogUtil;

import java.io.File;

public class CameraActivity extends BaseActivity {

    private ImageView imageView;
    CameraUtil cameraUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraUtil = new CameraUtil(CameraActivity.this,100);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // CameraUtil.takePhoto(CameraActivity.this, Environment.getExternalStorageDirectory().getPath(),"aa",1000)
                cameraUtil.openCamera();
            }
        });
        imageView = (ImageView) findViewById(R.id.iv_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cameraUtil.handleActivityResult(requestCode, resultCode,new CameraUtil.Callbacks() {
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
