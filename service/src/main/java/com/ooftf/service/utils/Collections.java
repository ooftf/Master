package com.ooftf.service.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/19 0019
 */
public class Collections {
    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
        for (T t : src) {
            dest.add(t);
        }
    }
}
