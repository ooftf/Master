package com.ooftf.master.webview.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.webview.R;
import com.ooftf.master.webview.engine.JsInjector;
import com.ooftf.master.webview.widget.ImgSrcHandlerDialog;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import org.jetbrains.annotations.Nullable;

/**
 * @author ooftf
 */
@Route(path = RouterPath.WEBVIEW_ACTIVITY_WEBVIEW)
public class MasterWebViewActivity extends BaseActivity {
    WebView webView;
    JsInjector jsInjector = new JsInjector();
    @Autowired
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_webview);
        ARouter.getInstance().inject(this);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("jmimage:")) {
                    new ImgSrcHandlerDialog(MasterWebViewActivity.this, url.replace("jmimage:", "")).show();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @SuppressLint("CheckResult")
            @Override
            public void onPageFinished(WebView view, String url) {
                jsInjector.inject(view);
            }
        });
        if(url!=null){
            webView.loadUrl(url);
        }else{
            webView.loadUrl("https://mtt.m.jd.com/article/articleView/0b800dcf-1d28-4be8-86d3-3f70535318cb.action");
        }

    }
}
