package com.ooftf.master.qrcode.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.ooftf.master.qrcode.R;
import com.ooftf.master.qrcode.utils.QrDecoder;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.utils.JLog;
import com.ooftf.service.widget.toolbar.TailoredToolbar;

import org.jetbrains.annotations.Nullable;

/**
 * 二维码扫描
 *
 * @author 99474
 */
@Route(path = RouterPath.QRCODE_ACTIVITY_QRCODE)
public class QRCodeActivity extends BaseActivity {
    QRCodeReaderView cameraPreview;
    Button stop;
    Button start;
    QrDecoder qrDecoder = new QrDecoder();
    TailoredToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        toolbar = findViewById(R.id.toolbar);
        cameraPreview = findViewById(R.id.cameraPreview);
        setupCameraPreview();
        setupToolbar();
    }

    private void setupToolbar() {
        MenuItem light = toolbar.getMenu().add("");
        light.setIcon(R.drawable.light_close).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        light.setChecked(false);
        light.setOnMenuItemClickListener(item -> {
            item.setChecked(!item.isChecked());
            if (item.isChecked()) {
                item.setIcon(R.drawable.light_open);
            } else {
                item.setIcon(R.drawable.light_close);
            }
            cameraPreview.setTorchEnabled(item.isChecked());
            return true;
        });
    }

    private void setupCameraPreview() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        cameraPreview.setOnQRCodeReadListener((text, points) -> {
            JLog.e(text, points);
            cameraPreview.stopCamera();
        });

        // Use this function to enable/disable decoding
        cameraPreview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        cameraPreview.setAutofocusInterval(2000L);

        // Use this function to set back camera preview
        cameraPreview.setBackCamera();
    }


    @Override
    protected void onResume() {
        cameraPreview.startCamera();
        super.onResume();
    }

    @Override
    protected void onPause() {
        cameraPreview.stopCamera();
        super.onPause();
    }

}
