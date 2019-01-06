package com.master.kit.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.master.kit.R
import com.master.kit.adapter.BottomBarAdapter
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterExtra
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.FragmentSwitchManager
import hugo.weaving.DebugLog
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.MAIN_ACTIVITY_MAIN, extras = RouterExtra.Extras.NEED_SIGN)
class MainActivity : BaseActivity() {
    private lateinit var switchManager: FragmentSwitchManager
    private lateinit var adapter: BottomBarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = PackerNg.getChannel(this)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        switchManager = FragmentSwitchManager(
                supportFragmentManager,
                R.id.frame_fragment,
                "/widget/fragment/widget",
                "/source/fragment/source",
                "/debug/fragment/debug",
                "/other/fragment/other",
                "/applet/fragment/app",
                onPreSwitch = null
        ) {
            ARouter.getInstance().build(it).navigation() as Fragment
        }
        bottomBar.setOnItemSelectChangedListener { oldIndex, newIndex ->
            when (newIndex) {
                0 -> switchManager.switchFragment("/widget/fragment/widget")
                1 -> switchManager.switchFragment("/source/fragment/source")
                2 -> switchManager.switchFragment("/applet/fragment/app")
                3 -> switchManager.switchFragment("/debug/fragment/debug")
                4 -> switchManager.switchFragment("/other/fragment/other")
                else -> switchManager.switchFragment("/applet/fragment/app")
            }
        }
        adapter = BottomBarAdapter(this)
        adapter.add(BottomBarItemBean("widget", R.drawable.ic_widget_selected_24dp, R.drawable.ic_widget_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarItemBean("source", R.drawable.ic_logic_selected_24dp, R.drawable.ic_logic_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarItemBean("app", R.drawable.ic_app_selected_24dp, R.drawable.ic_app_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarItemBean("debug", R.drawable.ic_debug_selected_24dp, R.drawable.ic_debug_24dp, R.color.blue_light, R.color.black))
        adapter.add(BottomBarItemBean("other", R.drawable.ic_other_selected_24dp, R.drawable.ic_other_24dp, R.color.blue_light, R.color.black))
        bottomBar.setAdapter(adapter)
        bottomBar.setSelectedIndex(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.action_settings -> toast(System.currentTimeMillis().toString() + "")
        }
        return true
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

    class BottomBarItemBean(var text: String, var selectedImageId: Int, var unSelectedImageId: Int, var selectedColorId: Int, var unSelectedColorId: Int)
}
