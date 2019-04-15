package com.ooftf.service.engine;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.ooftf.service.utils.JLog;

public class ActivityLifecycleLog {
    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                log(activity, savedInstanceState);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                log(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                log(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                log(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                log(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                log(activity, outState);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                log(activity);
            }
        });

    }

    static void log(Activity activity) {
        log(activity, null);
    }

    static void log(Activity activity, Bundle message) {
        if (message == null) {
            JLog.e("Activity-lifecycle", 36, activity.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[3].getMethodName());
        } else {
            JLog.e("Activity-lifecycle", 36, activity.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[3].getMethodName(), message);
        }

    }
}
