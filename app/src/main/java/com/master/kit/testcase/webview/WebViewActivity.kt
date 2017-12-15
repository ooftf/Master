package com.master.kit.testcase.webview

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.webkit.CookieManager
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_web_view.*
import ooftf.com.widget.self.pulltorefresh.PullToRefreshRoot

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView.settings.javaScriptEnabled = true
        //webView.loadUrl("www.baidu.com");
        webView.loadUrl("http://reg.163.com/resetpwd/index.do")
        webView.settings.defaultTextEncodingName = "utf-8"
        webView.addJavascriptInterface(JSHandler(), "Objectaaa")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                if (url.contains("/register/H5Register")) {

                } else if (url.contains("/redPacket/index")) {

                } else if (url.contains("/certification/realName")) {

                } else if (url.contains("/invest/investlist")) {
                    //投资列表

                } else {
                    //
                    webView!!.loadUrl(url)
                }
                return true
            }
        }
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        pullToRefreshRoot.setOnRefreshListener { Handler().postDelayed({ pullToRefreshRoot.onRefreshComplete() }, 2000) }
    }

    internal inner class JSHandler {
        @JavascriptInterface
        fun startInvestment() {

        }

        @JavascriptInterface
        fun inviteFriends() {
            //邀请好友

        }
    }
}
