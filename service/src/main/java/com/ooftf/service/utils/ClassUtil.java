package com.ooftf.service.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/13 0013
 */
public class ClassUtil {
    public static Class getParameterizedClass(Class cls, int index) {
        Type type = cls.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length > index) {
                return (Class) actualTypeArguments[index];
            }
        }
        return null;
    }
    public static <T> T newInstance(Class<T> t) {
        try {
            Constructor<T> constructor = t.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
