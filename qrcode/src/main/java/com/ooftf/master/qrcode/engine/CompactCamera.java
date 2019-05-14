package com.ooftf.master.qrcode.engine;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.view.View;

import com.ooftf.master.qrcode.QRCodeApp;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.utils.JLog;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;


/**
 * @author 99474
 * <p>
 * https://blog.csdn.net/zhangbijun1230/article/details/80556903
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CompactCamera implements ICamera {
    private TextureView mTextureView;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSession;
    private PublishSubject<IPreviewCallback.ImageInfo> previewData = PublishSubject.create();
    private Surface surface;
    private ImageReader mImageReader;

    public CompactCamera(TextureView textureView) {
        this.mTextureView = textureView;
        surface = new Surface(mTextureView.getSurfaceTexture());
        //获取预览数据
        mImageReader = ImageReader.newInstance(mTextureView.getWidth(), mTextureView.getHeight(),
                ImageFormat.NV21, 1);
        //监听ImageReader的事件，当有图像流数据可用时会回调onImageAvailable方法，它的参数就是预览帧数据，可以对这帧数据进行处理
        mImageReader.setOnImageAvailableListener(reader -> {
            Image image = reader.acquireLatestImage();
            //我们可以将这帧数据转成字节数组，类似于Camera1的PreviewCallback回调的预览帧数据
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            JLog.e(image.getWidth() + ":" + image.getHeight());
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            previewData.onNext(new IPreviewCallback.ImageInfo(image.getFormat(), data, image.getWidth(), image.getHeight()));
            image.close();
        }, null);
        previewData
                .throttleFirst(100, TimeUnit.MILLISECONDS)
                .subscribe(data -> {
                    if (previewCallback != null) {
                        previewCallback.onPreview(data);
                    }
                });
    }

    @Override
    public void startPreview() {
        JLog.e("startPreview");
        getRequest().subscribe(captureRequest -> cameraCaptureSession.setRepeatingRequest(captureRequest, null, null));
    }

    @Override
    public void stopPreview() {
        if (cameraCaptureSession != null) {
            try {
                cameraCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    IPreviewCallback previewCallback;

    @Override
    public void setImageCallback(IPreviewCallback callback) {
        previewCallback = callback;
    }

    @Override
    public View getTargetView() {
        return mTextureView;
    }

    @Override
    public int getPreviewWidth() {
        return mImageReader.getWidth();
    }

    @Override
    public int getPreviewHeight() {
        return mImageReader.getHeight();
    }


    public void destroy() {
        if (cameraDevice != null) {
            cameraDevice.close();
        }
        previewData.onComplete();
    }


    @SuppressLint("MissingPermission")
    private Observable<CameraDevice> openCamera() {
        return Observable
                .fromCallable(() -> cameraDevice)
                .onErrorResumeNext(Observable.create(emitter -> {
                    CameraManager manager = (CameraManager) BaseApplication.instance.getSystemService(Context.CAMERA_SERVICE);
                    String[] cameraIdList = manager.getCameraIdList();
                    manager.openCamera(cameraIdList[0], new CameraDevice.StateCallback() {
                        @Override
                        public void onOpened(@NonNull CameraDevice camera) {
                            cameraDevice = camera;
                            emitter.onNext(camera);
                            emitter.onComplete();
                        }

                        @Override
                        public void onDisconnected(@NonNull CameraDevice camera) {
                            cameraDevice = null;
                        }


                        @Override
                        public void onClosed(@NonNull CameraDevice camera) {
                            cameraDevice = null;

                        }

                        @Override
                        public void onError(@NonNull CameraDevice camera, int error) {
                            cameraDevice = null;
                            emitter.onError(new Exception("onError::" + error));
                        }
                    }, null);
                }));
    }


    Observable<CameraCaptureSession> createSession() {
        return Observable
                .fromCallable(() -> cameraCaptureSession)
                .onErrorResumeNext(openCamera()
                        .flatMap((Function<CameraDevice, ObservableSource<CameraCaptureSession>>) camera -> Observable.create(emitter -> {
                            camera.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                                @Override
                                public void onConfigured(@NonNull CameraCaptureSession session) {
                                    cameraCaptureSession = session;
                                    emitter.onNext(session);
                                    emitter.onComplete();

                                }

                                @Override
                                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                                    emitter.onError(new Exception("onConfigureFailed"));
                                }
                            }, null);
                        })));


    }

    CaptureRequest mRequest;

    Observable<CaptureRequest> getRequest() {
        return Observable.fromCallable(() -> mRequest).onErrorResumeNext(throwable -> {
            return createSession().map(session -> {
                CaptureRequest.Builder captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                captureRequestBuilder.addTarget(surface);
                captureRequestBuilder.addTarget(mImageReader.getSurface());
                mRequest = captureRequestBuilder.build();
                return mRequest;
            });
        });

    }
}
