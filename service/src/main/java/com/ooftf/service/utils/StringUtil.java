package com.ooftf.service.utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author master
 * @date 2017/6/12 0012
 */

public class StringUtil {

    public static List<String> split(String src, int perLength) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= src.length(); ) {
            int end = Math.min(src.length(), i + perLength);
            list.add(src.substring(i, end));
            i = end;
        }
        return list;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    /**
     * 判断是否是json格式
     *
     * @param string
     * @return
     */
    public static boolean isJson(String string) {
        try {
            new JSONObject(string);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * 将数字 填充为两位字符串
     *
     * @param src
     * @return
     */
    public static String zeroPaddingToDouble(String src) {

        if (src.length() > 1) {
            return src;
        } else if (src.length() == 1) {
            return "0" + src;
        } else {
            return "00";
        }
    }

    /**
     * 将数字补成两位字符串
     *
     * @param src
     * @return
     */
    public static String zeroPaddingToDouble(int src) {
        return zeroPaddingToDouble(String.valueOf(src));
    }

    public static String listToString(List list, String splitCharacter) {
        if (list == null) {
            return null;
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                result += list.get(i);
            } else {
                result += list.get(i) + splitCharacter;
            }
        }
        return result;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String str) {
        try {
            Long.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String str) {
        try {
            Double.valueOf(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
