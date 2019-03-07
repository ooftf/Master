package com.ooftf.master.qrcode.engine;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.view.Surface;

import com.ooftf.master.qrcode.App;
import com.ooftf.master.qrcode.widget.CameraPreview;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CompactCamera {
    SurfaceTexture cameraPreview = new SurfaceTexture("");
    {
        CameraManager manager = (CameraManager) App.getInstance().getSystemService(Context.CAMERA_SERVICE);
        String[] cameraIdList = manager.getCameraIdList();
        manager.openCamera(cameraIdList[0], new CameraDevice.StateCallback() {
            @Override
            public void onOpened(@NonNull CameraDevice camera) {
                camera.createCaptureSession(new ArrayList<Surface>() {
                    {
                        add(new Surface(cameraPreview));
                    }
                }, new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(@NonNull CameraCaptureSession session) {

                    }

                    @Override
                    public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                    }
                },null);
            }

            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {

            }

            @Override
            public void onError(@NonNull CameraDevice camera, int error) {

            }
        });
    }
}
