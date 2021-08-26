package com.ooftf.master.m.entrance.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.bottombar.java.FragmentSwitchManager
import com.ooftf.master.m.entrance.R
import com.ooftf.master.m.entrance.adapter.BottomBarAdapter
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.m.entrance.databinding.ActivityMainBinding
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.main_tab.TabManager
import dagger.hilt.android.AndroidEntryPoint

@Route(path = RouterPath.MAIN_ACTIVITY_MAIN)
@AndroidEntryPoint
class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {
    private lateinit var switchManager: FragmentSwitchManager<String>
    private lateinit var adapter: BottomBarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        title = PackerNg.getChannel(this)
        setupBottomBar()
    }

    @SuppressLint("CheckResult")
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
        binding.bottomBar.setOnItemSelectChangedListener { _, newIndex ->
            switchManager.switchFragment(adapter.getItem(newIndex).text)
        }
        binding.bottomBar.setAdapter(adapter)
        binding.bottomBar.setSelectedIndex(0)

    }

    private var backPressedTime = 0L
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            android.os.Process.killProcess(android.os.Process.myPid())
        } else {
            backPressedTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }
    }
}
