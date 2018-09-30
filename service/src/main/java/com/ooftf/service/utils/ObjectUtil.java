package com.ooftf.service.utils;

/**
 * 操作Object的工具类
 */
public class ObjectUtil {
    public static boolean equals(Object theOne, Object theOther) {
        if (theOne == null && theOther == null) {
            return true;
        }
        if (theOne != null) {
            return theOne.equals(theOther);
        }
        return false;
    }
}
