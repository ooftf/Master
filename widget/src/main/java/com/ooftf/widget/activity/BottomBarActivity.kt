package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.R
import com.ooftf.widget.adapter.WidgetBottomBarAdapter
import kotlinx.android.synthetic.main.activity_bottom_bar.*

@Route(path = "/widget/activity/bottomBar")
class BottomBarActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_bar)
        val adapter = WidgetBottomBarAdapter(context = this)
        adapter.add(WidgetBottomBarAdapter.BottomBarItemBean("widget", R.drawable.ic_widget_selected_24dp, R.drawable.ic_widget_24dp, R.color.blue_light, R.color.black))
        adapter.add(WidgetBottomBarAdapter.BottomBarItemBean("source", R.drawable.ic_logic_selected_24dp, R.drawable.ic_logic_24dp, R.color.blue_light, R.color.black))
        adapter.add(WidgetBottomBarAdapter.BottomBarItemBean("app", R.drawable.ic_app_selected_24dp, R.drawable.ic_app_24dp, R.color.blue_light, R.color.black))
        adapter.add(WidgetBottomBarAdapter.BottomBarItemBean("debug", R.drawable.ic_debug_selected_24dp, R.drawable.ic_debug_24dp, R.color.blue_light, R.color.black))
        adapter.add(WidgetBottomBarAdapter.BottomBarItemBean("other", R.drawable.ic_other_selected_24dp, R.drawable.ic_other_24dp, R.color.blue_light, R.color.black))
        navigation.setAdapter(WidgetBottomBarAdapter(context = this))
    }
}
