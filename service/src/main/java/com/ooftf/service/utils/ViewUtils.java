package com.ooftf.service.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/15 0015
 */
public class ViewUtils {
    public static void removeSelf(View view) {
        if (view == null) {
            return;
        }
        if (view.getParent() == null) {
            return;
        }
        if (view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
