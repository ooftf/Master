package com.ooftf.master.qrcode;

import android.app.Application;
import android.content.Context;

import com.ooftf.docking.api.IApplication;
import com.ooftf.log.JLog;
import com.ooftf.service.utils.TimeRuler;

/**
 * @author 99474
 */
public class QRCodeApp implements IApplication {
    private static Application application;
    @Override
    public void init(Application application) {
        QRCodeApp.application = application;
    }
    @Override
    public void onCreate() {
        TimeRuler.marker("MyApplication", "QRCodeApp start");

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void attachBaseContext(Context context) {

    }

    @Override
    public int getPriority() {
        return 0;
    }

    public static Application getApplication() {
        return application;
    }
}
