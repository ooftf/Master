package com.ooftf.widget.engine;

import com.ooftf.service.engine.main_tab.BottomBarItemBean;
import com.ooftf.service.engine.main_tab.MainTabProvider;
import com.ooftf.widget.R;

import androidx.annotation.Keep;

@Keep
public class WidgetTabProvider implements MainTabProvider {
    String TAB_WIDGET = "widget";

    @Override
    public BottomBarItemBean getBottomBarItemBean() {
        return new BottomBarItemBean(TAB_WIDGET, 100,"/widget/fragment/widget", R.drawable.ic_widget_selected_24dp, R.drawable.ic_widget_24dp, R.color.blue_light, R.color.black);
    }
}
