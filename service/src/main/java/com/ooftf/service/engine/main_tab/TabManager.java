package com.ooftf.service.engine.main_tab;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.Keep;

/**
 * AutoRegister 中 codeInsertToClassName组件
 *
 * @author 99474
 */
@Keep
public class TabManager {
    static ArrayList<BottomBarItemBean> tabs = new ArrayList<>();

    static void register(MainTabProvider provider) {
        tabs.add(provider.getBottomBarItemBean());
    }

    public static ArrayList<BottomBarItemBean> getTabs() {
        Collections.sort(tabs, (o1, o2) -> o2.getPriority() - o1.getPriority());
        return tabs;
    }
}
