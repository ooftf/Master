package com.ooftf.master.qrcode.widget;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;

import com.ooftf.master.qrcode.engine.CompactCamera;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/8 0008
 */
public class CameraPreview21 extends TextureView {
    public CameraPreview21(Context context) {
        super(context);
    }

    public CameraPreview21(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    {
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    CompactCamera camera = new CompactCamera(CameraPreview21.this);
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
}
