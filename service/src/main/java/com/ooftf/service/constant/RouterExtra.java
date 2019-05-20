package com.ooftf.service.constant;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.utils.JLog;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class RouterExtra {

    public static boolean isNeedSign(int extra) {
        return (extra & 1) == 0;
    }
}
