package com.master.kit.testcase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.master.kit.R;

import com.master.kit.utils.GalleryHelper;
import com.ooftf.kit.utils.BitmapUtils;


import java.io.IOException;

public class AlbumActivity extends AppCompatActivity {

    private ImageView imageView;
    GalleryHelper galleryHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        galleryHelper = new GalleryHelper(AlbumActivity.this,100);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryHelper.openGallery();
            }
        });
        imageView = (ImageView) findViewById(R.id.iv_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        galleryHelper.handleActivityResult(requestCode, resultCode, data, new GalleryHelper.Callbacks() {
            @Override
            public void onImagePicked(Uri uri) throws IOException {
                imageView.setImageBitmap(BitmapUtils.readBitmapFromUri(uri,800*400,AlbumActivity.this));
            }

            @Override
            public void onImagePickerError(Exception e) {

            }

            @Override
            public void onCanceled() {

            }
        });

    }
}
