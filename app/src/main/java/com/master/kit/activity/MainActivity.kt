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
import com.ooftf.service.engine.FragmentSwitchManager
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/main/main")
class MainActivity : BaseActivity() {
    private lateinit var switchManager: FragmentSwitchManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        title = PackerNg.getChannel(this)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        switchManager = FragmentSwitchManager(
                supportFragmentManager,
                R.id.frame_fragment,
                "/widget/widget",
                "/source/source",
                "/debug/debug",
                "/other/other",
                "/applet/app",
                onPreSwitch = null
        ) {
            ARouter.getInstance().build(it).navigation() as Fragment
        }
        bottomBar.onItemSelectChangedListener = { oldIndex, newIndex->
            when(newIndex){
                0 ->  switchManager.switchFragment("/widget/widget")
                1 ->  switchManager.switchFragment("/source/source")
                2 ->  switchManager.switchFragment("/applet/app")
                3 ->  switchManager.switchFragment("/debug/debug")
                4 ->  switchManager.switchFragment("/other/other")
                else ->  switchManager.switchFragment("/applet/app")
            }
        }
        bottomBar.adapter = BottomBarAdapter(this)
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
    override fun onBackPressed() {
        if(System.currentTimeMillis()-backPressedTime<2000){
            android.os.Process.killProcess( android.os.Process.myPid())
        }else{
            backPressedTime = System.currentTimeMillis()
            toast("再按一次退出应用")
        }
    }
}
