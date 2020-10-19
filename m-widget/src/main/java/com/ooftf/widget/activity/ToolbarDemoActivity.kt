package com.ooftf.widget.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.widget.toolbar.official.ToolbarPlus
import com.ooftf.service.base.BaseBarrageActivity
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_toolbar_demo.*

/**
 * @author lihang9
 * @date 2018-8-27 15:19:49
 */
@Route(path = "/widget/activity/toolbarDemo")
class ToolbarDemoActivity : BaseBarrageActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_demo)
        toolbar.title = "标题"
        toolbar.addMenuItem(ToolbarPlus.MenuItem(this).layoutRight().setImage(R.drawable.vector_refresh).setOnClickListenerChain { addBarrage("vector_refresh") })
        toolbar.addMenuItem(ToolbarPlus.MenuItem(this).layoutRight().setText("刷新").setOnClickListenerChain { addBarrage("刷新") })
        toolbar.addMenuItem(ToolbarPlus.MenuItem(this).layoutLeft().setImage(R.drawable.vector_icon_del))
        toolbar.addMenuItem(ToolbarPlus.MenuItem(this).layoutLeft().setText("关闭"))

        /*  toolbar.menu.add("外显").setIcon(R.drawable.vector_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)  //监听事件的返回值会影响界面点击效果，返回true没有点击效果，
        toolbar.menu.add("内藏").setIcon(R.drawable.vector_refresh).setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)*/
    }
}