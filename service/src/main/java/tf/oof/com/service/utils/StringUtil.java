package tf.oof.com.service.utils;

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
