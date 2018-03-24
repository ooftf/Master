package com.master.kit.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.master.kit.R
import com.mcxiaoke.packer.helper.PackerNg
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.engine.FragmentSwitchManager
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/main/main")
class MainActivity : BaseActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var switchManager: FragmentSwitchManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = PackerNg.getChannel(this)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        switchManager = FragmentSwitchManager(
                supportFragmentManager,
                R.id.frame_fragment,
                R.id.tab_widget,
                R.id.tab_logic,
                R.id.tab_debug,
                R.id.tab_other,
                R.id.tab_app,
                onPreSwitch = null
        ) {
            when (it) {
                R.id.tab_widget -> return@FragmentSwitchManager ARouter.getInstance().build("/widget/widget").navigation() as Fragment
                R.id.tab_logic -> return@FragmentSwitchManager ARouter.getInstance().build("/main/logic").navigation() as Fragment
                R.id.tab_debug -> return@FragmentSwitchManager ARouter.getInstance().build("/debug/debug").navigation() as Fragment
                R.id.tab_other -> return@FragmentSwitchManager ARouter.getInstance().build("/main/other").navigation() as Fragment
                R.id.tab_app -> ARouter.getInstance().build("/applet/app").navigation() as Fragment
                else -> return@FragmentSwitchManager ARouter.getInstance().build("/widget/widget").navigation() as Fragment
            }

        }
        bottomBar.setOnTabSelectListener { tabId -> switchManager.switchFragment(tabId) }
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
    override fun onBackPressed() {
        if(System.currentTimeMillis()-backPressedTime<2000){
            android.os.Process.killProcess( android.os.Process.myPid())
        }else{
            backPressedTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }
    }
}
