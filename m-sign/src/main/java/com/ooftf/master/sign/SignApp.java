package com.ooftf.master.sign;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.ooftf.docking.api.IApplication;
import com.ooftf.docking.api.MainProcess;
import com.ooftf.log.JLog;
import com.ooftf.service.utils.TimeRuler;

public class SignApp implements IApplication {

    private static Application application;
    @Override
    public void init(Application application) {
        SignApp.application = application;
    }
    @MainProcess
    @Override
    public void onCreate(Application application) {
        TimeRuler.marker("MyApplication", "SignApp start");
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
