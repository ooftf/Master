package com.ooftf.master.m.entrance.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lzf.easyfloat.interfaces.OnPermissionResult
import com.lzf.easyfloat.permission.PermissionUtils
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.bottombar.java.FragmentSwitchManager
import com.ooftf.master.m.entrance.R
import com.ooftf.master.m.entrance.adapter.BottomBarAdapter
import com.ooftf.master.unit.am.ActivityManager
import com.ooftf.master.widget.eye.DevEye
import com.ooftf.master.widget.eye.DevEyeProvider
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.base.BaseApplication
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.main_tab.TabManager
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.MAIN_ACTIVITY_MAIN)
class MainActivity : BaseActivity() {
    private lateinit var switchManager: FragmentSwitchManager<String>
    private lateinit var adapter: BottomBarAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_main)
        title = PackerNg.getChannel(this)
        setupBottomBar()
        PermissionUtils.checkPermission(this)
        PermissionUtils.requestPermission(this, object : OnPermissionResult {
            override fun permissionResult(isOpen: Boolean) {
                //EasyFloat.with(this).setLayout(R.layout.float_test).show()
            }

        })
        DevEye.show(this)
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
        bottomBar.setOnItemSelectChangedListener { _, newIndex ->
            switchManager.switchFragment(adapter.getItem(newIndex).text)
        }
        bottomBar.setAdapter(adapter)
        bottomBar.setSelectedIndex(0)
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

    companion object {
        init {
            initGodEye()
        }

        private fun initGodEye() {
            DevEye.init(BaseApplication.instance, DevEyeProvider {
                ActivityManager.getTopActivity()!!
            })
        }
    }
}
