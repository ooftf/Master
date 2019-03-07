package com.ooftf.master.qrcode.engine;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;

import com.ooftf.master.qrcode.App;

import java.nio.ByteBuffer;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * @author 99474
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CompactCamera {
    TextureView mTextureView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CompactCamera(TextureView textureView) {
        this.mTextureView = textureView;
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    void init() {
        CameraManager manager = (CameraManager) App.getInstance().getSystemService(Context.CAMERA_SERVICE);
        String[] cameraIdList = new String[0];
        try {
            cameraIdList = manager.getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        if (mTextureView.getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        try {
            manager.openCamera(cameraIdList[0], new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    Surface surface = new Surface(mTextureView.getSurfaceTexture());

                    try {
                        CaptureRequest.Builder captureRequestBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                        captureRequestBuilder.addTarget(surface);
                        camera.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                            @Override
                            public void onConfigured(@NonNull CameraCaptureSession session) {
                                CaptureRequest request = captureRequestBuilder.build();
                                try {
                                    session.setRepeatingRequest(request, null, null);
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                            }
                        }, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {

                }

                @Override
                public void onError(@NonNull CameraDevice camera, int error) {

                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void ss() {
        //前三个参数分别是需要的尺寸和格式，最后一个参数代表每次最多获取几帧数据，本例的2代表ImageReader中最多可以获取两帧图像流
        ImageReader mImageReader = ImageReader.newInstance(mTextureView.getWidth(), mTextureView.getHeight(),
                ImageFormat.JPEG, 2);
        //监听ImageReader的事件，当有图像流数据可用时会回调onImageAvailable方法，它的参数就是预览帧数据，可以对这帧数据进行处理
        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            @Override
            public void onImageAvailable(ImageReader reader) {
                Image image = reader.acquireLatestImage();
                //我们可以将这帧数据转成字节数组，类似于Camera1的PreviewCallback回调的预览帧数据
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                image.close();
            }
        }, null);
    }
}
