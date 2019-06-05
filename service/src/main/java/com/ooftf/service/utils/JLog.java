package com.ooftf.service.utils;

import android.util.Log;

import com.ooftf.service.engine.json.JsonFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @date 2018/9/20 0020
 * @desc
 **/
public class JLog {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String FRAME_LINE_HEADER = "║ ";
    public static final String FRAME_START = "╔══════════════════════════════════════════════════════════════════════════════════════════════════════";
    public static final String FRAME_END = "╚══════════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String BRACKET_START = "[";
    private static final String BRACKET_END = "]";
    private static final String FILLER = " ";

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
    public static final int MAX_LENGTH = 3000;
    public static final String TAG_EMPTY = "JLog-TAG-Empty";
    public static final String TAG_NULL = "JLog-TAG-Null";

    public static void v(Object info) {
        v(null, info);
    }


    public static void d(Object info) {
        d(null, info);
    }


    public static void i(Object info) {
        i(null, info);
    }


    public static void w(Object info) {
        w(null, info);
    }


    public static void e(Object info) {
        e(null, info);
    }

    public static void v(Object tag, Object info) {
        logObject(VERBOSE, tag, info);
    }

    public static void d(Object tag, Object info) {
        logObject(DEBUG, tag, info);
    }

    public static void i(Object tag, Object info) {
        logObject(INFO, tag, info);
    }

    public static void w(Object tag, Object info) {
        logObject(WARN, tag, info);
    }

    public static void e(Object tag, Object info) {
        logObject(ERROR, tag, info);
    }


    public static void vJson(Object tag, Object msg) {
        logJson(VERBOSE, tag, msg);
    }

    public static void dJson(Object tag, Object msg) {
        logJson(DEBUG, tag, msg);
    }

    public static void iJson(Object tag, Object msg) {
        logJson(INFO, tag, msg);
    }

    public static void wJson(Object tag, Object msg) {
        logJson(WARN, tag, msg);
    }

    public static void eJson(Object tag, Object msg) {
        logJson(ERROR, tag, msg);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void v(String tag, int i, Object... message) {
        bamboo(VERBOSE, tag, i, message);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void d(String tag, int i, Object... message) {
        bamboo(DEBUG, tag, i, message);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void i(String tag, int i, Object... message) {
        bamboo(INFO, tag, i, message);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void w(String tag, int i, Object... message) {
        bamboo(WARN, tag, i, message);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void e(String tag, int i, Object... message) {
        bamboo(ERROR, tag, i, message);
    }

    /**
     * 每个 message 固定长度，长度不够的用 {@link JLog#FILLER} 填补
     *
     * @param tag
     * @param i
     * @param message
     */
    public static void bamboo(int level, String tag, int i, Object... message) {
        StringBuffer result = new StringBuffer();
        for (Object s : message) {
            StringBuffer per = new StringBuffer();
            per.append(BRACKET_START);
            per.append(s);
            per.append(BRACKET_END);
            while (per.length() < i) {
                per.append(FILLER);
            }
            result.append(per);
        }
        logObject(level, tag, result.toString());
    }


    private static void logArray(int level, Object tag, String[] list) {
        logList(level, tag, Arrays.asList(list));
    }

    private static void logList(int level, Object tag, List<String> list) {
        logObject(level, tag, FRAME_START);
        for (String per : list) {
            logObject(level, tag, FRAME_LINE_HEADER + per);
        }
        logObject(level, tag, FRAME_END);
    }

    private static void logJson(int level, Object tag, Object msg) {
        String message;
        if (msg == null) {
            logObject(level, tag, msg);
            return;
        } else if (msg instanceof String) {
            message = (String) msg;
        } else {
            message = JsonFactory.getDefault().toJson(msg);
        }
        try {
            if (message.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(message);
                //最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = jsonObject.toString(4);
            } else if (message.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            }
        } catch (JSONException ignored) {
            ignored.printStackTrace();
        }
        logArray(level, tag, message.split(LINE_SEPARATOR));
    }

    /**
     * 最基本的打印方法，所有打印封装都应该基于这个方法
     *
     * @param level
     * @param tag
     * @param message
     */
    private static void logObject(int level, Object tag, Object message) {
        if (intercept(level)) {
            return;
        }
        String msgString = String.valueOf(message);
        if (msgString.length() > MAX_LENGTH) {
            logList(level, tag, StringUtil.split(msgString, MAX_LENGTH));
        } else {
            dispatch(level, parseTag(tag), msgString);
        }
    }

    private static PublishSubject<LogBean> logPublisher = PublishSubject.create();

    public static PublishSubject<LogBean> register() {
        return logPublisher;
    }

    /**
     * 这个方法应该只被 logObject 所调用
     *
     * @param level
     * @param tag
     * @param msg
     */
    private static void dispatch(int level, String tag, String msg) {
        logPublisher.onNext(new LogBean(level, tag, msg));
        switch (level) {
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            default:
                Log.d(tag, msg);

        }
    }

    private static boolean intercept(int level) {
        return false;
    }


    private static String parseTag(Object tag) {
        if (tag == null) {
            return TAG_NULL;
        } else if (tag instanceof String) {
            if (((String) tag).length() == 0) {
                return TAG_EMPTY;
            }
            return (String) tag;
        } else {
            return tag.getClass().getSimpleName();
        }
    }

    public static class LogBean {
        public LogBean(int level, String tag, String msg) {
            this.level = level;
            this.tag = tag;
            this.msg = msg;
        }

        public int level;
        public String tag;
        public String msg;
    }
}
