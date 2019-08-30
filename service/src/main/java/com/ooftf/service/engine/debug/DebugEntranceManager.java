package com.ooftf.service.engine.debug;

import java.util.ArrayList;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/11 0011
 */
public class DebugEntranceManager {
    public static ArrayList<String> paths = new ArrayList<>();

    static void register(DebugEntranceProvider provider) {
        paths.addAll(provider.getPath());
    }

   /* public static ArrayList<BottomBarItemBean> getTabs() {
        Collections.sort(tabs, (o1, o2) -> o2.getPriority() - o1.getPriority());
        return tabs;
    }*/
}
