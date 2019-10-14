package com.ooftf.master.webview.engine;


import android.annotation.SuppressLint;


import com.ooftf.service.base.BaseApplication;
import com.tencent.smtt.sdk.WebView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JsInjector {
    String jsString = null;

    private Observable<String> getJsString() {
        return memory().switchIfEmpty(disk())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressLint("CheckResult")
    public void inject(WebView webView) {
        getJsString()
                .subscribe(js -> webView.loadUrl("javascript:" + js));
    }

    Observable<String> memory() {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            if (jsString == null) {
                emitter.onComplete();
            } else {
                emitter.onNext(jsString);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    Observable<String> disk() {
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            InputStreamReader inputReader = new InputStreamReader(BaseApplication.instance.getResources().getAssets().open("JDMWebViewJS.js"));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                sb.append(line);
            }
            emitter.onNext(sb.toString());
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
