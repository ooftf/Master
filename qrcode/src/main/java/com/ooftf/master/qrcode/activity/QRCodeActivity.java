package com.ooftf.master.qrcode.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.qrcode.R;
import com.ooftf.master.qrcode.utils.QrDecoder;
import com.ooftf.master.qrcode.widget.CameraPreview;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.utils.JLog;

import org.jetbrains.annotations.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 二维码扫描
 *
 * @author 99474
 */
@Route(path = RouterPath.QRCODE_ACTIVITY_QRCODE)
public class QRCodeActivity extends BaseActivity {
    CameraPreview cameraPreview;
    Button stop;
    Button start;
    QrDecoder qrDecoder = new QrDecoder();

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
        cameraPreview.setImageCallback(info -> {
            JLog.e("length", info.getBytes().length);
            JLog.e("getImageFormat", info.getImageFormat());//35  17
            qrDecoder.decodePreviewByte(info.getBytes(), info.getWidth(), info.getHeight())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        cameraPreview.stopPreview();
                        JLog.e("onPreview", s);
                    });

        });
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
