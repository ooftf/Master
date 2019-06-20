package com.ooftf.master.webview.utils;

import com.ooftf.service.utils.ViewUtils;
import com.tencent.smtt.sdk.WebView;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/20 0020
 */
public class WebViewUtil {
    public static void destory(WebView webView) {
        try {
            if (webView != null) {
                ViewUtils.removeSelf(webView);
                webView.stopLoading();
                // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                webView.getSettings().setJavaScriptEnabled(false);
                webView.clearHistory();
                webView.clearView();
                webView.removeAllViews();
                webView.destroy();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace(System.err);
        }
    }

    public static void destory(android.webkit.WebView webView) {
        try {
            if (webView != null) {
                ViewUtils.removeSelf(webView);
                webView.stopLoading();
                // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                webView.getSettings().setJavaScriptEnabled(false);
                webView.clearHistory();
                webView.clearView();
                webView.removeAllViews();
                webView.destroy();
            }
        } catch (Exception ignored) {
            ignored.printStackTrace(System.err);
        }
    }
}
