package com.master.kit.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.master.kit.R
import com.master.kit.adapter.BottomBarAdapter
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.bottombar.java.FragmentSwitchManager
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterExtra
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.main_tab.BottomBarItemBean
import com.ooftf.service.engine.main_tab.TabManager
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
        adapter.addAll(TabManager.getTabs())
        switchManager = FragmentSwitchManager(
                supportFragmentManager,
                R.id.frame_fragment,
                adapter.data.map { it.text }.toSet())
        {
            var path = adapter.data.first { item ->
                item.text == it
            }.path
            ARouter.getInstance().build(path).navigation() as androidx.fragment.app.Fragment
        }
        bottomBar.setOnItemSelectChangedListener { _, newIndex ->
            switchManager.switchFragment(adapter.getItem(newIndex).text)
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
}
