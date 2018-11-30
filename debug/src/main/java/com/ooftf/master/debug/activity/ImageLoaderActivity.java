package com.ooftf.master.debug.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.debug.R;
import com.ooftf.master.debug.R2;
import com.ooftf.master.debug.aspectj.Log;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.engine.imageloader.ImageLoaderFactory;
import com.ooftf.service.engine.imageloader.ImageLoaderListener;
import com.ooftf.service.utils.JLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lihang9
 */
@Route(path = "/debug/activity/imageLoader")
public class ImageLoaderActivity extends BaseActivity {

    @BindView(R2.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);

    }

    @OnClick(R2.id.button)
    public void onViewClicked() {
        ImageLoaderFactory.INSTANCE.createInstance().display(this, "http://img03.tooopen.com/uploadfile/downs/images/20110714/sy_20110714135215645030.jpg", new ImageLoaderListener() {
            @Override
            public void onLoadComplete(Bitmap bitmap) {
                JLog.e(bitmap.isRecycled());
                JLog.e(bitmap);
                bitmap.recycle();
            }

            @Override
            public void onError() {

            }
        });
    }
}
