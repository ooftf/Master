package com.master.kit.testcase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.master.kit.R
import com.master.kit.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.BaseSlidingActivity
import tf.oof.com.service.engine.FragmentSwitchManager

class MainActivity : BaseActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context,MainActivity::class.java)
        }
    }
    private lateinit var switchManager: FragmentSwitchManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                onDoSwitch = null
        ) {
            when (it) {
                R.id.tab_widget -> return@FragmentSwitchManager WidgetFragment.newInstance()
                R.id.tab_logic -> return@FragmentSwitchManager LogicFragment.newInstance()
                R.id.tab_debug -> return@FragmentSwitchManager DebugFragment.newInstance()
                R.id.tab_other -> return@FragmentSwitchManager OtherFragment.newInstance()
                R.id.tab_app -> return@FragmentSwitchManager AppFragment.newInstance()
                else -> return@FragmentSwitchManager WidgetFragment.newInstance()
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
    var backPressedTime = 0L
    override fun onBackPressed() {
        if(System.currentTimeMillis()-backPressedTime<2000){
            android.os.Process.killProcess( android.os.Process.myPid())
        }else{
            backPressedTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }
    }
}
