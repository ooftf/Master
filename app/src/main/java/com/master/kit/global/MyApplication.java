package com.master.kit.global;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by master on 2016/12/26.
 */

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.init(getApplicationContext());
    }
}
