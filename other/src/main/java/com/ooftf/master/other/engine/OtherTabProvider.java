package com.ooftf.master.other.engine;

import com.ooftf.master.other.R;
import com.ooftf.service.engine.main_tab.BottomBarItemBean;
import com.ooftf.service.engine.main_tab.MainTabProvider;

import androidx.annotation.Keep;

@Keep
public class OtherTabProvider implements MainTabProvider {
    String TAB_OTHER = "other";
    @Override
    public BottomBarItemBean getBottomBarItemBean() {
        return new BottomBarItemBean(TAB_OTHER, 60, "/other/fragment/other",R.drawable.ic_other_selected_24dp, R.drawable.ic_other_24dp, R.color.blue_light, R.color.black);
    }
}
