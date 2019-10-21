package com.ooftf.master.qrcode.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.ooftf.master.qrcode.engine.ICamera;
import com.ooftf.master.qrcode.engine.IPreviewCallback;
import com.ooftf.master.qrcode.utils.CameraConfigurationUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 */
class CameraPreviewLow extends SurfaceView implements SurfaceHolder.Callback, ICamera {
    private static final String TAG = "CameraPreview";

    PublishSubject<IPreviewCallback.ImageInfo> preCallback = PublishSubject.create();
    Camera.PreviewCallback callback = (data, camera1) -> {
        preCallback.onNext(new IPreviewCallback.ImageInfo(camera1.getParameters().getPreviewFormat(), data, camera1.getParameters().getPreviewSize().width, camera1.getParameters().getPreviewSize().height));
    };
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreviewLow(Context context) {
        super(context);
        setKeepScreenOn(true);
        mCamera = Camera.open();
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            mCamera.setDisplayOrientation(90);
        } else {
            mCamera.setDisplayOrientation(0);
        }
        Camera.Parameters parameters = mCamera.getParameters();
        CameraConfigurationUtils.setBestPreviewFPS(parameters);
        CameraConfigurationUtils.setFocusArea(parameters);
        mCamera.setParameters(parameters);
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        preCallback
                .throttleFirst(100, TimeUnit.MILLISECONDS)
                .subscribe(bytes -> {
                    if (previewCallback != null) {
                        previewCallback.onPreview(bytes);
                    }
                });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            startPreview();

        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.

    }

    @Override
    protected void onDetachedFromWindow() {
        mCamera.release();
        preCallback.onComplete();
        super.onDetachedFromWindow();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            startPreview();
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void startPreview() {
        try {
            // 必须设置在startPreview旁边，stopPreview会清除这个回调
            mCamera.setPreviewCallback(callback);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void stopPreview() {

        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }

    IPreviewCallback previewCallback;

    @Override
    public void setImageCallback(IPreviewCallback callback) {
        previewCallback = callback;
    }

    @Override
    public View getTargetView() {
        return this;
    }

    @Override
    public int getPreviewWidth() {
        return mCamera.getParameters().getPreviewSize().width;
    }

    @Override
    public int getPreviewHeight() {
        return mCamera.getParameters().getPreviewSize().height;
    }
}
