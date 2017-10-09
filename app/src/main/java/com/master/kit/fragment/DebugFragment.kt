package com.master.kit.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.master.kit.R
import com.master.kit.adapter.MainRecyclerAdapter
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.BaseFragment
import com.master.kit.testcase.DialogDemo
import com.master.kit.testcase.KeyBoardActivity
import com.master.kit.testcase.NewInstanceActivity
import com.master.kit.testcase.listview.ListViewActivity
import com.master.kit.testcase.net.NetActivity
import com.master.kit.testcase.pulltorefresh.PullToRefreshActivity
import com.master.kit.testcase.touchevent.TouchActivity

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.master.kit.bean.ScreenItemBean
import com.ooftf.applet.breakfast.BreakfastActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class DebugFragment : BaseHomeFragment() {

    override fun initData() {
        adapter.add(ScreenItemBean(NetActivity::class.java.simpleName,"",R.mipmap.ic_launcher, NetActivity::class.java,false))
        adapter.add(ScreenItemBean(NewInstanceActivity::class.java.simpleName,"",R.mipmap.ic_launcher,NewInstanceActivity::class.java,false))
        adapter.add(ScreenItemBean(DialogDemo::class.java.simpleName,"",R.mipmap.ic_launcher,DialogDemo::class.java,false))
        adapter.add(ScreenItemBean(PullToRefreshActivity::class.java.simpleName,"",R.mipmap.ic_launcher,PullToRefreshActivity::class.java,false))
        adapter.add(ScreenItemBean(KeyBoardActivity::class.java.simpleName,"",R.mipmap.ic_launcher,KeyBoardActivity::class.java,false))
        adapter.add(ScreenItemBean(ListViewActivity::class.java.simpleName,"",R.mipmap.ic_launcher,ListViewActivity::class.java,false))
        adapter.add(ScreenItemBean(TouchActivity::class.java.simpleName,"",R.mipmap.ic_launcher,TouchActivity::class.java,false))
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
