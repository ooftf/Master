package com.ooftf.service.constant;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.utils.JLog;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class RouterExtra {
    static char Y = '1';
    static char N = '0';
    static int MAX_LENGTH = 32;
    static int INDEX_NEED_SIGN = 1;
    public interface Extras{
        /**
         * 登录开，其他关
         */
       int NEED_SIGN = 0b11000000000000000000000000000000;
    }



    public static boolean isNeedSign(int extra) {
        String extraBinaryString = Integer.toBinaryString(extra);
        JLog.e("extraBinaryString-bin",extraBinaryString);
        JLog.e("extraBinaryString-length",extraBinaryString.length());
        JLog.e("extraBinaryString-charAt",extraBinaryString.charAt(INDEX_NEED_SIGN));
        if (extraBinaryString.length() == MAX_LENGTH && extraBinaryString.charAt(INDEX_NEED_SIGN) == Y) {
            return true;
        }
        return false;
    }
}
