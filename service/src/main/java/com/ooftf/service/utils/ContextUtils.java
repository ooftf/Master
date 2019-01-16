package com.ooftf.service.utils;

import android.app.Activity;
import android.content.Context;
import android.view.ContextThemeWrapper;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/27 0027
 */
public class ContextUtils {
    /**
     * 如果你确定 context是activity 可以使用此方法
     *
     * @param context
     * @return
     */
    public static Activity toActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextThemeWrapper) {
            return toActivity(((ContextThemeWrapper) context).getBaseContext());
        } else if (context instanceof androidx.appcompat.view.ContextThemeWrapper) {
            return toActivity(((androidx.appcompat.view.ContextThemeWrapper) context).getBaseContext());
        }
        return null;
    }
}
