package com.ooftf.master.other.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.other.R;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TResult;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

@Route(path = "/other/takePhoto")
public class PhotoActivity extends TakePhotoActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        webView = findViewById(R.id.webview);
        webView.getSettings().setAllowFileAccess(true);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startPhotoPicker();
                startTakePhoto();
            }
        });
    }

    private void startTakePhoto() {
        getTakePhoto().onPickFromGallery();
    }

    private void startPhotoPicker() {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(PhotoActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }

    public void takeSuccess(TResult result){
        Log.e("ssss",result.getImage().getOriginalPath());
    }
    public void takeFail(TResult result,String msg){

    }
    public void takeCancel(){

    }
}
