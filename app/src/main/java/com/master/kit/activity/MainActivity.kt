package com.master.kit.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.master.kit.R
import com.master.kit.adapter.BottomBarAdapter
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.bottombar.java.FragmentSwitchManager
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterExtra
import com.ooftf.service.constant.RouterPath
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.MAIN_ACTIVITY_MAIN, extras = RouterExtra.Extras.NEED_SIGN)
class MainActivity : BaseActivity() {
    private lateinit var switchManager: FragmentSwitchManager<String>
    private lateinit var adapter: BottomBarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_main)
        title = PackerNg.getChannel(this)
        setupBottomBar()
    }

    private fun setupBottomBar() {

        adapter = BottomBarAdapter(this)
        adapter.add(BottomBarAdapter.BottomBarItemBean(TAB_WIDGET, R.drawable.ic_widget_selected_24dp, R.drawable.ic_widget_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarAdapter.BottomBarItemBean(TAB_SOURCE, R.drawable.ic_logic_selected_24dp, R.drawable.ic_logic_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarAdapter.BottomBarItemBean(TAB_APP, R.drawable.ic_app_selected_24dp, R.drawable.ic_app_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarAdapter.BottomBarItemBean(TAB_DEBUG, R.drawable.ic_debug_selected_24dp, R.drawable.ic_debug_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarAdapter.BottomBarItemBean(TAB_OTHER, R.drawable.ic_other_selected_24dp, R.drawable.ic_other_24dp, R.color.blue_light, R.color.black))
        switchManager = FragmentSwitchManager(
                supportFragmentManager,
                R.id.frame_fragment,
                adapter.data.map { it.text }.toSet())
        {
            when (it) {
                TAB_WIDGET -> ARouter.getInstance().build("/widget/fragment/widget").navigation() as Fragment
                TAB_SOURCE -> ARouter.getInstance().build("/source/fragment/source").navigation() as Fragment
                TAB_APP -> ARouter.getInstance().build("/applet/fragment/app").navigation() as Fragment
                TAB_DEBUG -> ARouter.getInstance().build("/debug/fragment/debug").navigation() as Fragment
                TAB_OTHER -> ARouter.getInstance().build("/other/fragment/other").navigation() as Fragment
                else -> ARouter.getInstance().build("/applet/fragment/app").navigation() as Fragment
            }

        }
        bottomBar.setOnItemSelectChangedListener { _, newIndex ->
            when (newIndex) {
                0 -> switchManager.switchFragment(TAB_WIDGET)
                1 -> switchManager.switchFragment(TAB_SOURCE)
                2 -> switchManager.switchFragment(TAB_APP)
                3 -> switchManager.switchFragment(TAB_DEBUG)
                4 -> switchManager.switchFragment(TAB_OTHER)
                else -> switchManager.switchFragment(TAB_APP)
            }
        }
        bottomBar.setAdapter(adapter)
        bottomBar.setSelectedIndex(0)
    }

    private var backPressedTime = 0L
    @DebugLog
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            android.os.Process.killProcess(android.os.Process.myPid())
        } else {
            backPressedTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }
    }

    val TAB_WIDGET = "widget"
    val TAB_SOURCE = "source"
    val TAB_APP = "app"
    val TAB_DEBUG = "debug"
    val TAB_OTHER = "other"
}
