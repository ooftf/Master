package com.ooftf.service.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author ooftf
 * @date 2018/9/20 0020
 * @desc
 **/
public class JLog {
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    public static void v(Object info) {
        v(null, info);
    }

    public static void v(Object tag, Object info) {
        if (intercept(VERBOSE)) {
            return;
        }
        Log.v(parseTag(tag), String.valueOf(info));
    }

    public static void d(Object info) {
        d(null, info);
    }

    public static void d(Object tag, Object info) {
        if (intercept(DEBUG)) {
            return;
        }
        Log.d(parseTag(tag), String.valueOf(info));
    }

    public static void i(Object info) {
        i(null, info);
    }

    public static void i(Object tag, Object info) {
        if (intercept(INFO)) {
            return;
        }
        Log.i(parseTag(tag), String.valueOf(info));
    }

    public static void w(Object info) {
        w(null, info);
    }

    public static void w(Object tag, Object info) {
        if (intercept(WARN)) {
            return;
        }
        Log.w(parseTag(tag), String.valueOf(info));
    }

    public static void e(Object info) {
        e(null, info);
    }

    public static void e(Object tag, Object info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.e(parseTag(tag), String.valueOf(info));
    }

    private static boolean intercept(int level) {
        return false;
    }

    private static String parseTag(Object tag) {
        if (tag == null) {
            return "JLog";
        } else if (tag instanceof String) {
            if (((String) tag).length() == 0) {
                return "JLog";
            }
            return (String) tag;
        } else {
            return tag.getClass().getSimpleName();
        }
    }

}
