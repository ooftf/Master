package com.ooftf.master.im.activity

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ashokvarma.bottomnavigation.BottomNavigationBar.SimpleOnTabSelectedListener
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ooftf.bottombar.java.FragmentCreator
import com.ooftf.bottombar.java.FragmentSwitchManager
import com.ooftf.master.im.R
import com.ooftf.master.im.other.ImManager
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.constant.RouterPath
import kotlinx.android.synthetic.main.activity_im_main.*
import java.util.*

/**
 * @author 99474
 */
@Route(path = RouterPath.IM_ACTIVITY_MAIN)
class ImMainActivity : BaseActivity() {
    companion object {
        const val TAG_CONVERSATION = "conversation"
        const val TAG_CONTACT = "contact"

        init {
            ImManager.init()
        }
    }

    var fsm: FragmentSwitchManager<String>? = null
    var kv: SparseArray<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_im_main)
        initFSM()
        initBottomBar()
    }

    private fun initFSM() {
        kv = SparseArray()
        val tags: MutableSet<String> = HashSet()
        kv!!.put(0, TAG_CONVERSATION)
        kv!!.put(1, TAG_CONTACT)
        for (i in 0 until kv!!.size()) {
            tags.add(kv!![i])
        }
        fsm = FragmentSwitchManager(supportFragmentManager, R.id.container, tags) { tag: String ->
            if (TAG_CONVERSATION == tag) {
                ARouter.getInstance().build("/im/fragment/conversation").navigation() as Fragment
            } else {
                ARouter.getInstance().build("/im/fragment/contact").navigation() as Fragment
            }
        }
    }

    private fun initBottomBar() {
        bottom_navigation_bar
                .addItem(BottomNavigationItem(R.drawable.ic_conversation, "消息"))
                .addItem(BottomNavigationItem(R.drawable.ic_contact, "通讯录"))
                .setTabSelectedListener(object : SimpleOnTabSelectedListener() {
                    override fun onTabSelected(position: Int) {
                        fsm!!.switchFragment(kv!![position])
                    }
                })
                .setFirstSelectedPosition(0)
                .initialise()
        fsm!!.switchFragment(kv!![0])
    }

    override fun isDarkFont(): Boolean {
        return true
    }

    override fun isImmersionEnable(): Boolean {
        return true
    }
}