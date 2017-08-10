package com.ooftf.kit.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by master on 2017/6/12 0012.
 */

public class StringUtil {
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isInteger(String str) {
        if (isEmpty(str)) return false;
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String str) {
        if (isEmpty(str)) return false;
        if(str.startsWith(".")||str.endsWith("."))return false;
        if(str.length()-str.replace(".","").length()>1)return false; //含有多个 . 符号
        Pattern pattern = Pattern.compile("^[0-9.]+$");///^$/;\d+\.\d+
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
}
