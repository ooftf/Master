package com.master.kit.TestActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.master.kit.Base.BaseActivity;
import com.master.kit.R;
import com.master.kit.utils.AlbumUtil;
import com.master.kit.utils.LogUtil;

public class CameraActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // CameraUtil.takePhoto(CameraActivity.this, Environment.getExternalStorageDirectory().getPath(),"aa",1000);
                AlbumUtil.selectPhoto(CameraActivity.this,100);
            }
        });
        imageView = (ImageView) findViewById(R.id.iv_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e("Intent",data+"");
        if(resultCode == 0) return;
        imageView.setImageBitmap(AlbumUtil.getBitmapFromIntent(this,data));
    }

}
