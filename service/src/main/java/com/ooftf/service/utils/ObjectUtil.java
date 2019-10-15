package com.ooftf.service.utils;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

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

    public static <T> T copy(T o) {
        if (o == null) {
            return null;
        }
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        String json = serializationService.object2Json(o);
        return serializationService.parseObject(json, o.getClass());
    }

    public static Object copy(Object o, Type type) {
        if (o == null) {
            return null;
        }
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        String json = serializationService.object2Json(o);
        return serializationService.parseObject(json, type);
    }

    public static <T> T copy(Object o, Class<T> type) {
        if (o == null) {
            return null;
        }
        SerializationService serializationService = ARouter.getInstance().navigation(SerializationService.class);
        String json = serializationService.object2Json(o);
        return serializationService.parseObject(json, type);
    }
}
