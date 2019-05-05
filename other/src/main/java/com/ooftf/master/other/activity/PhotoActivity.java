package com.ooftf.master.other.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;

import androidx.annotation.Keep;
import androidx.core.content.FileProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.Utils;
import com.ooftf.master.other.R;
import com.ooftf.service.constant.ProviderConstant;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.uitl.TUriParse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

@Route(path = "/other/activity/takePhoto")
public class PhotoActivity extends TakePhotoActivity {
    WebView webView;
    ValueCallback<Uri[]> mFilePathCallback;
    int flag = 0;

    public static Uri file2Uri(final File file) {
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = ProviderConstant.FILE_PROVIDER;
            return FileProvider.getUriForFile(Utils.getApp(), authority, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        initWebView();
        findViewById(R.id.button).setOnClickListener(v -> {
            //startPhotoPicker();
            startTakePhoto();
        });
    }

    private void initWebView() {
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.addJavascriptInterface(new JsAndroid(), "AndroidJs");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture) {//>= 4.1

            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {//5.0+
                mFilePathCallback = filePathCallback;
                //startActivityForResult(fileChooserParams.createIntent(),125);
                flag = 2;
                startTakePhotoMultiple();
                return true;
            }
        });
        //webView.loadUrl("file:///android_asset/takePhone.html");
        webView.loadUrl("http://10.0.23.53:8080/takePhone.html");
        //webView.loadUrl("http://htmlpreview.github.io/?https://raw.githubusercontent.com/ooftf/Material/master/takePhone.html");
    }

    private void startTakePhoto() {
        getTakePhoto().onPickFromGallery();
    }

    private void startTakePhotoMultiple() {
        getTakePhoto().onPickMultiple(2);
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
        //Log.e("getData", data.getData().toString());
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        for (TImage e : result.getImages()) {
            Log.e("takeSuccess", e.getOriginalPath());
        }

        if (flag == 1) {
            webView.evaluateJavascript("javascript:setImage(\"" + result.getImage().getOriginalPath() + "\")", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {

                }
            });
        } else if (flag == 2) {
            List<Uri> uris = new ArrayList<>();
            for (TImage e : result.getImages()) {
                uris.add(TUriParse.convertFileUriToFileProviderUri(this, Uri.fromFile(new File(e.getOriginalPath()))));
            }

            Uri imageContentUri = TUriParse.convertFileUriToFileProviderUri(this, Uri.fromFile(new File(result.getImage().getOriginalPath())));
            //Uri imageContentUri = Uri.fromFile(new File(result.getImage().getOriginalPath()));
            Log.e("takeSuccess", imageContentUri.toString());
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(uris.toArray(new Uri[0]));
                mFilePathCallback = null;
            }
        }
        flag = 0;
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Keep
    public class JsAndroid extends Object {
        @JavascriptInterface
        public void pickImage() {
            flag = 1;
            startTakePhoto();
        }
    }
}