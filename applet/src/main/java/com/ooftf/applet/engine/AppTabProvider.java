package com.ooftf.applet.engine;

import com.ooftf.applet.R;
import com.ooftf.service.engine.main_tab.BottomBarItemBean;
import com.ooftf.service.engine.main_tab.MainTabProvider;

import androidx.annotation.Keep;

@Keep
public class AppTabProvider implements MainTabProvider {
    String TAB_APP = "app";

    @Override
    public BottomBarItemBean getBottomBarItemBean() {
        return new BottomBarItemBean(TAB_APP, 80,"/applet/fragment/app", R.drawable.ic_app_selected_24dp, R.drawable.ic_app_24dp, R.color.blue_light, R.color.black);
    }
}
