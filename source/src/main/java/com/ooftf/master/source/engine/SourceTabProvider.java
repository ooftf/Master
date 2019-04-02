package com.ooftf.master.source.engine;

import com.ooftf.master.source.R;
import com.ooftf.service.engine.main_tab.BottomBarItemBean;
import com.ooftf.service.engine.main_tab.MainTabProvider;

import androidx.annotation.Keep;

@Keep
public class SourceTabProvider implements MainTabProvider {
    String TAB_SOURCE = "source";

    @Override
    public BottomBarItemBean getBottomBarItemBean() {
        return new BottomBarItemBean(TAB_SOURCE, 90, "/source/fragment/source", R.drawable.ic_logic_selected_24dp, R.drawable.ic_logic_24dp, R.color.blue_light, R.color.black);
    }
}
