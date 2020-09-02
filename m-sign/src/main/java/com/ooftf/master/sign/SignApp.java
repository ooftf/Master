package com.ooftf.master.sign;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.ooftf.docking.api.IApplication;
import com.ooftf.log.JLog;
import com.ooftf.service.utils.TimeRuler;

public class SignApp implements IApplication {

    private static Application application;
    @Override
    public void init(Application application) {
        this.application = application;
    }

    @Override
    public void onCreate() {
        TimeRuler.marker("MyApplication", "SignApp start");
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
