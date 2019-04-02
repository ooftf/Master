package com.ooftf.master.debug.engine;

import com.ooftf.master.debug.R;
import com.ooftf.service.engine.main_tab.BottomBarItemBean;
import com.ooftf.service.engine.main_tab.MainTabProvider;

import androidx.annotation.Keep;

@Keep
public class DebugTabProvider implements MainTabProvider {
    String TAB_DEBUG = "debug";

    @Override
    public BottomBarItemBean getBottomBarItemBean() {
        return new BottomBarItemBean(TAB_DEBUG, 70,"/debug/fragment/debug",R.drawable.ic_debug_selected_24dp, R.drawable.ic_debug_24dp, R.color.blue_light, R.color.black);
    }
}
