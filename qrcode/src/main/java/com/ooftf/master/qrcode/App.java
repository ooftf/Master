package com.ooftf.master.qrcode;

import android.app.Application;
import android.content.Context;

import com.ooftf.docking.api.IApplication;

public class App implements IApplication {
    private static Application INSTANCE;
    @Override
    public void onCreate(Application application) {
        INSTANCE = application;
    }
    public static Application getInstance(){
        return INSTANCE;
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
}
