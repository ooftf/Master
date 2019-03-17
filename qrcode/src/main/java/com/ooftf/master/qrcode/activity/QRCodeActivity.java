package com.ooftf.master.qrcode.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.ooftf.master.qrcode.R;
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
    TailoredToolbar toolbar;
    View scanLine;
    TranslateAnimation translateAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        toolbar = findViewById(R.id.toolbar);
        cameraPreview = findViewById(R.id.cameraPreview);
        scanLine = findViewById(R.id.scan_line);
        setupCameraPreview();
        setupToolbar();
        setupAnimation();
    }

    private void setupAnimation() {
        translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, -0.98f, Animation.RELATIVE_TO_PARENT, 0f);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
    }

    private void setupToolbar() {
        MenuItem light = toolbar.getMenu().add("灯光");
        light.setIcon(R.drawable.ic_flash_off_black_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        light.setChecked(false);
        light.setOnMenuItemClickListener(item -> {
            item.setChecked(!item.isChecked());
            if (item.isChecked()) {
                item.setIcon(R.drawable.ic_flash_on_black_24dp);
            } else {
                item.setIcon(R.drawable.ic_flash_off_black_24dp);
            }
            cameraPreview.setTorchEnabled(item.isChecked());
            return true;
        });
    }

    private void setupCameraPreview() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        cameraPreview.setOnQRCodeReadListener((text, points) -> {
            JLog.e(text, points);
            stopPreview();
        });

        // Use this function to enable/disable decoding
        cameraPreview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        cameraPreview.setAutofocusInterval(2000L);

        // Use this function to set back camera preview
        cameraPreview.setBackCamera();
    }

    void startPreview() {
        scanLine.setVisibility(View.VISIBLE);
        //ValueAnimator valueAnimator = new ValueAnimator();
        //scanLine.animate().translationY(ScreenUtils.getScreenHeight()).setDuration(2000).setInterpolator().start();
        cameraPreview.startCamera();
        scanLine.setAnimation(translateAnimation);
        translateAnimation.start();

    }

    void stopPreview() {
        scanLine.setVisibility(View.GONE);
        translateAnimation.cancel();
        cameraPreview.stopCamera();
    }

    @Override
    protected void onResume() {
        startPreview();
        super.onResume();
    }

    @Override
    protected void onPause() {
        stopPreview();
        super.onPause();
    }

}
