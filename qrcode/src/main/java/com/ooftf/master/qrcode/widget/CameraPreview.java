package com.ooftf.master.qrcode.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.ooftf.master.qrcode.engine.ICamera;
import com.ooftf.master.qrcode.engine.IPreviewCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import io.reactivex.Flowable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/11 0011
 */
public class CameraPreview extends FrameLayout implements ICamera {
    ICamera camera;

    public CameraPreview(@NonNull Context context) {

        super(context);
    }

    public CameraPreview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraPreview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraPreview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            camera = new CameraPreview21(getContext());
        } else {
            camera = new CameraPreviewLow(getContext());
        }*/
        camera = new CameraPreviewLow(getContext());
        addView(camera.getTargetView());
    }

    @Override
    public void startPreview() {
        camera.startPreview();
    }

    @Override
    public void stopPreview() {
        camera.stopPreview();
    }

    @Override
    public void setImageCallback(IPreviewCallback callback) {
        camera.setImageCallback(callback);
    }

    @Override
    public View getTargetView() {
        return camera.getTargetView();
    }
}
