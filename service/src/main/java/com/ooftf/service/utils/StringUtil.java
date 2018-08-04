package com.ooftf.service.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by master on 2017/6/12 0012.
 */

public class StringUtil {
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

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
     * 将数字补4成两位字符串
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
