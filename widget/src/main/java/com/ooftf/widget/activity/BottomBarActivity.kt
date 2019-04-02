package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.engine.main_tab.TabManager
import com.ooftf.widget.R
import com.ooftf.widget.adapter.WidgetBottomBarAdapter
import kotlinx.android.synthetic.main.activity_bottom_bar.*

@Route(path = "/widget/activity/bottomBar")
class BottomBarActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_bar)
        val adapter = WidgetBottomBarAdapter(context = this)
        adapter.addAll(TabManager.getTabs())
        navigation.setAdapter(WidgetBottomBarAdapter(context = this))
    }
}
