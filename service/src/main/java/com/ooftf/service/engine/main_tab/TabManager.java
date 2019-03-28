package com.ooftf.service.engine.main_tab;

import java.util.ArrayList;
import java.util.Collections;

public class TabManager {
    static ArrayList<BottomBarItemBean> tabs = new ArrayList<>();

    static void register(MainTabProvider provider) {
        tabs.add(provider.getBottomBarItemBean());
    }

    public static ArrayList<BottomBarItemBean> getTabs() {
        Collections.sort(tabs, (o1, o2) -> o1.getPriority() - o2.getPriority());
        return tabs;
    }
}
