package tf.oof.com.service.utils;

import android.text.TextUtils;

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
        } else if(src.length() == 1) {
            return "0" + src;
        }else{
            return "00";
        }
    }
    public static String zeroPaddingToDouble(int src) {
        return zeroPaddingToDouble(String.valueOf(src));
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
