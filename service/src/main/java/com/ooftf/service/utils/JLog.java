package com.ooftf.service.utils;

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

    public static void v(String info) {
        v(null, info);
    }

    public static void v(Object tag, String info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.v(parseTag(tag), info);
    }

    public static void d(String info) {
        d(null, info);
    }

    public static void d(Object tag, String info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.d(parseTag(tag), info);
    }

    public static void i(String info) {
        i(null, info);
    }

    public static void i(Object tag, String info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.i(parseTag(tag), info);
    }

    public static void w(String info) {
        w(null, info);
    }

    public static void w(Object tag, String info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.w(parseTag(tag), info);
    }

    public static void e(String info) {
        e(null, info);
    }

    public static void e(Object tag, String info) {
        if (intercept(ERROR)) {
            return;
        }
        Log.e(parseTag(tag), info);
    }

    private static boolean intercept(int level) {
        return false;
    }

    private static String parseTag(Object tag) {
        if (tag == null) {
            return "JLog";
        } else if (tag instanceof String) {
            return (String) tag;
        } else {
            return tag.getClass().getSimpleName();
        }
    }

}
