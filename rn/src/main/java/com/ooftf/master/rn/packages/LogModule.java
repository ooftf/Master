package com.ooftf.master.rn.packages;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by 99474 on 2017/12/6 0006.
 */

public class LogModule extends ReactContextBaseJavaModule {
    public LogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "Log";
    }
    @ReactMethod
    public void e(String message) {
        Log.e("ract-native",message);
    }
}
