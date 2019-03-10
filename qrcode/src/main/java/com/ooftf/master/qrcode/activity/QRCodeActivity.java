package com.ooftf.master.qrcode.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.qrcode.R;
import com.ooftf.master.qrcode.widget.CameraPreview21;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.utils.JLog;

import org.jetbrains.annotations.Nullable;

/**
 * 二维码扫描
 *
 * @author 99474
 */
@Route(path = RouterPath.QRCODE_ACTIVITY_QRCODE)
public class QRCodeActivity extends BaseActivity {
    CameraPreview21 cameraPreview;
    Button stop;
    Button start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        cameraPreview = findViewById(R.id.cameraPreview);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            start.setOnClickListener(view -> {
                cameraPreview.startPreview();
            });
            stop.setOnClickListener(view -> cameraPreview.stopPreview());
        }
        cameraPreview.setImageCallback(bytes -> JLog.e("onPreview", bytes.length));

    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraPreview.startPreview();
    }

    @Override
    protected void onStop() {
        cameraPreview.stopPreview();
        super.onStop();
    }
}
