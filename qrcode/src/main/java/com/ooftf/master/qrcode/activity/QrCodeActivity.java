package com.ooftf.master.qrcode.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.android.material.snackbar.Snackbar;
import com.ooftf.master.qrcode.R;
import com.ooftf.master.qrcode.R2;
import com.ooftf.master.qrcode.engine.GlideEngineV471;
import com.ooftf.master.qrcode.utils.QrDecoder;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.ProviderConstant;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.widget.dialog.OptDialog;
import com.ooftf.service.widget.toolbar.TailoredToolbar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 二维码扫描
 *
 * @author ooftf
 */
@Route(path = RouterPath.QRCODE_ACTIVITY_QRCODE)
public class QrCodeActivity extends BaseActivity {
    private static int REQUEST_CODE_CHOOSE = 257;
    @BindView(R2.id.cameraPreview)
    QRCodeReaderView cameraPreview;
    @BindView(R2.id.toolbar)
    TailoredToolbar toolbar;
    @BindView(R2.id.scan_line)
    View scanLine;
    TranslateAnimation translateAnimation;
    @BindView(R2.id.qrCode)
    View qrCode;
    @BindView(R2.id.album)
    View album;
    boolean isAllowPreview = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        setupCameraPreview();
        setupToolbar();
        setupAnimation();
        initViews();
    }

    private void initViews() {

        qrCode.setOnClickListener(v -> Snackbar.make(cameraPreview, "懒蛋", Snackbar.LENGTH_SHORT).show());
        album.setOnClickListener(v -> Matisse
                .from(QrCodeActivity.this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(1)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, ProviderConstant.FILE_PROVIDER))
                .spanCount(3)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngineV471())
                .forResult(REQUEST_CODE_CHOOSE));
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
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> cameraPreview.setOnQRCodeReadListener((text, points) -> {
                    emitter.onNext(text);
                }))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    stopPreview();
                    isAllowPreview = false;
                    showResult(s);
                });


        // Use this function to enable/disable decoding
        cameraPreview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        cameraPreview.setAutofocusInterval(2000L);
        // Use this function to set back camera preview
        cameraPreview.setBackCamera();
    }

    void startPreview() {
        if (!isAllowPreview) {
            return;
        }
        scanLine.setVisibility(View.VISIBLE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (REQUEST_CODE_CHOOSE == requestCode && resultCode == RESULT_OK) {
            List<String> uris = Matisse.obtainPathResult(data);
            if (uris != null && uris.size() > 0) {
                stopPreview();
                isAllowPreview = false;
                Observable
                        .fromCallable(() -> BitmapFactory.decodeFile(uris.get(0)))
                        .flatMap((Function<Bitmap, ObservableSource<String>>) bitmap -> new QrDecoder().getQRResult(bitmap, 0, 0))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            showResult(s);
                        }, throwable -> {
                            isAllowPreview = true;
                            startPreview();
                        });
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showResult(String result) {
        OptDialog optDialog = new OptDialog(this).setContentText(result);
        optDialog.setOnDismissListener(dialog -> {
            isAllowPreview = true;
            startPreview();
        });
        optDialog.show();
    }
}
