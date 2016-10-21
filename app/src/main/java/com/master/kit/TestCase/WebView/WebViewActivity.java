package com.master.kit.testcase.webview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ooftf.pulltorefresh.widget.APullToRefreshHeader;
import com.ooftf.pulltorefresh.widget.PullToRefreshWebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PullToRefreshWebView webView = new PullToRefreshWebView(this);
        setContentView(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("www.baidu.com");
        webView.loadUrl("http://reg.163.com/resetpwd/index.do");
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.addJavascriptInterface(new JSHandler(), "Objectaaa");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains("/register/H5Register")){

                }else if(url.contains("/redPacket/index")){

                }else if(url.contains("/certification/realName")){

                }else if(url.contains("/invest/investlist")){
                    //投资列表

                }else{
//
                    webView.loadUrl(url);
                }
                return true;
            }
        });
        CookieManager cookieManager =  CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        webView.setOnRefreshListener(new APullToRefreshHeader.OnRefreshListener() {
            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.onRefreshComplete();
                    }
                },2000);
            }
        });
    }
    class JSHandler{
        @JavascriptInterface
        public void startInvestment(){

        }
        @JavascriptInterface
        public void inviteFriends(){
            //邀请好友

        }
    }
}
