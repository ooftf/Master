package com.ooftf.service.engine;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ooftf.service.utils.JLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * 打印 activity和fragment的生命周期方法
 */
public class LifecycleLog {
    private static FragmentLifecycleCallbacks fragmentLifecycleCallbacks = new FragmentLifecycleCallbacks();

    public static void init(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                log(activity, savedInstanceState);
                if(activity instanceof FragmentActivity){
                    FragmentActivity fragmentActivity = (FragmentActivity) activity;
                    fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true);
                }
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

    static class FragmentLifecycleCallbacks extends FragmentManager.FragmentLifecycleCallbacks {

        public FragmentLifecycleCallbacks() {
            super();
        }

        @Override
        public void onFragmentPreAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
            log(f, context);
        }

        @Override
        public void onFragmentAttached(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Context context) {
            log(f, context);
        }

        @Override
        public void onFragmentPreCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            log(f, savedInstanceState);
        }

        @Override
        public void onFragmentCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            log(f, savedInstanceState);
        }

        @Override
        public void onFragmentActivityCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @Nullable Bundle savedInstanceState) {
            log(f, savedInstanceState);
        }

        @Override
        public void onFragmentViewCreated(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
            log(f, v, savedInstanceState);
        }

        @Override
        public void onFragmentStarted(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentPaused(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentStopped(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentSaveInstanceState(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle outState) {
            super.onFragmentSaveInstanceState(fm, f, outState);
        }

        @Override
        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }

        @Override
        public void onFragmentDetached(@NonNull FragmentManager fm, @NonNull Fragment f) {
            log(f);
        }
    }

    static void log(Object activityOrFragment, Object... message) {
        List<Object> list = new ArrayList<>();
        list.add(activityOrFragment.getClass().getSimpleName());
        list.add(Thread.currentThread().getStackTrace()[3].getMethodName());
        List<Object> list1 = Arrays.asList(message);
        list.addAll(list1);
        String tag = null;
        if (activityOrFragment instanceof Activity) {
            tag = "Activity-lifecycle";
        } else if (activityOrFragment instanceof Fragment) {
            tag = "Fragment-lifecycle";
        }
        JLog.i(tag, 32, list.toArray());
    }
}
