package com.master.kit.fragment

import android.os.Bundle
import com.master.kit.activity.DrawableDebugActivity
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.DialogDemo
import com.master.kit.testcase.KeyBoardActivity
import com.master.kit.testcase.NewInstanceActivity
import com.master.kit.testcase.listview.ListViewActivity
import ooftf.com.widget.activity.PullToRefreshActivity
import com.master.kit.testcase.touchevent.TouchActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class DebugFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean(NewInstanceActivity::class.java))
        adapter.add(ScreenItemBean(DialogDemo::class.java))
        adapter.add(ScreenItemBean(KeyBoardActivity::class.java))
        adapter.add(ScreenItemBean(ListViewActivity::class.java))
        adapter.add(ScreenItemBean(TouchActivity::class.java))
        adapter.add(ScreenItemBean(DrawableDebugActivity::class.java))
        adapter.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): DebugFragment {
            val args = Bundle()
            val fragment = DebugFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
