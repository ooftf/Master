package com.ooftf.master.other.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.UriUtils;
import com.blankj.utilcode.util.Utils;
import com.ooftf.master.other.R;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.uitl.TUriParse;

import java.io.File;
import java.util.ArrayList;

import cz.msebera.android.httpclient.client.utils.URIUtils;
import me.iwf.photopicker.PhotoPicker;

@Route(path = "/other/takePhoto")
public class PhotoActivity extends TakePhotoActivity {
    WebView webView;
    ValueCallback<Uri[]> mFilePathCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initWebView();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startPhotoPicker();
                startTakePhoto();
            }
        });
    }

    private void initWebView() {
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {//5.0+
                mFilePathCallback = filePathCallback;
               // startActivityForResult(fileChooserParams.createIntent(),125);
               startTakePhoto();
                return true;
            }
        });
        webView.loadUrl("file:///android_asset/takePhone.html");
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
        Log.e("getData",data.getData().toString());
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }

    public void takeSuccess(TResult result) {
        //Uri imageContentUri = TUriParse.convertFileUriToFileProviderUri(this, Uri.fromFile(new File(result.getImage().getOriginalPath())));
        Uri imageContentUri = Uri.fromFile(new File(result.getImage().getOriginalPath()));
        Log.e("takeSuccess",result.getImage().getOriginalPath());
        Log.e("takeSuccess",imageContentUri.toString());
        if (mFilePathCallback != null) {
            mFilePathCallback.onReceiveValue(new Uri[]{imageContentUri});
        }
    }

    public void takeFail(TResult result, String msg) {

    }

    public void takeCancel() {

    }

    public static Uri file2Uri(final File file) {
        if (file == null) return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = Utils.getApp().getPackageName() + ".provider";
            return FileProvider.getUriForFile(Utils.getApp(), authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }
}