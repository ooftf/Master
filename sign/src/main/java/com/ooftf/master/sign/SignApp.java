package com.ooftf.master.sign;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.ooftf.docking.annotation.AppShip;
import com.ooftf.docking.api.IApplication;

@AppShip
public class SignApp implements IApplication {
    @Override
    public void onCreate(Application application) {
        Log.e("SignApp","onCreate");
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
