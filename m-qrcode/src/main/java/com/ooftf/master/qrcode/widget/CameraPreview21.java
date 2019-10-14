package com.ooftf.master.qrcode.widget;

import android.Manifest;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

import com.ooftf.master.qrcode.engine.CompactCamera;
import com.ooftf.master.qrcode.engine.ICamera;
import com.ooftf.master.qrcode.engine.IPreviewCallback;
import com.ooftf.service.utils.JLog;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/8 0008
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class CameraPreview21 extends TextureView implements ICamera {
    CompactCamera camera;
    PublishSubject<CompactCamera> subject = PublishSubject.create();

    public CameraPreview21(Context context) {
        super(context);
    }

    public CameraPreview21(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        setKeepScreenOn(true);
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    JLog.e("onSurfaceTextureAvailable");
                    camera = new CompactCamera(CameraPreview21.this);
                    subject.onNext(camera);
                    subject.onComplete();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });

    }

    @Override
    protected void onDetachedFromWindow() {
        camera.destroy();
        super.onDetachedFromWindow();
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void startPreview() {
        JLog.e("startPreview///");
        getCamera().subscribe(
                CompactCamera::startPreview
                , Throwable::printStackTrace);

    }

    @Override
    public void stopPreview() {
        getCamera().subscribe(
                CompactCamera::stopPreview, Throwable::printStackTrace);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setImageCallback(IPreviewCallback callback) {
        getCamera().subscribe(compactCamera -> compactCamera.setImageCallback(callback), Throwable::printStackTrace);

    }

    @Override
    public View getTargetView() {
        return this;
    }

    @Override
    public int getPreviewWidth() {
        return camera.getPreviewWidth();
    }

    @Override
    public int getPreviewHeight() {
        return camera.getPreviewHeight();
    }

    public Observable<CompactCamera> getCamera() {
        JLog.e("getCamera///");
        return Observable
                .fromCallable(() -> {
                    JLog.e("getCamera");
                    return camera;
                })
                .retryWhen(throwableObservable -> {
                    JLog.e("throwableObservable");
                    return throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                        JLog.e("throwableObservable" + throwable.toString());
                        if (subject.hasComplete()) {
                            return Observable.error(throwable);
                        }
                        return subject;
                    });
                });
    }
}
