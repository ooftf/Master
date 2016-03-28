package com.master.kit.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by master on 2016/3/3.
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;

    public static void v(String message) {
        if (LEVEL <= VERBOSE) {
            String tag = getDefaultTag();
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (LEVEL <= VERBOSE) {
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag();
            }
            Log.v(tag, message);
        }
    }

    public static void d(String message) {
        if (LEVEL <= DEBUG) {
            String tag = getDefaultTag();
            Log.d(tag,message);
        }
    }

    public static void d(String tag, String message) {
        if (LEVEL <= DEBUG) {
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag();
            }
            Log.d(tag,message);
        }
    }

    public static void i(String message) {
        if (LEVEL <= INFO) {
            String tag = getDefaultTag();
            Log.i(tag,message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag();
            }
            Log.i(tag,message);
        }
    }

    public static void w(String message) {
        if (LEVEL <= WARN) {

            String tag = getDefaultTag();
            Log.w(tag,message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {

            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag();
            }
            Log.w(tag,  message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {

            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag();
            }
            Log.e(tag,message);
        }
    }
    public static String getDefaultTag() {
        return "Default";
    }

}
