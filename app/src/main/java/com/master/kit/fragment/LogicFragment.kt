package com.master.kit.fragment

import android.os.Bundle
import com.master.kit.R
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.design.DesignDispatchActivity
import com.master.kit.testcase.filedownload.DownloadActivity
import com.master.kit.testcase.retrofit.SignInActivity
import com.master.kit.testcase.viewpager.ViewPagerActivity
import com.master.kit.testcase.webview.WebViewActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class LogicFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean(DesignDispatchActivity::class.java.simpleName,"",R.mipmap.ic_launcher,DesignDispatchActivity::class.java,false))
        adapter.add(ScreenItemBean(ViewPagerActivity::class.java.simpleName,"",R.mipmap.ic_launcher,ViewPagerActivity::class.java,false))
        adapter.add(ScreenItemBean(SignInActivity::class.java.simpleName,"",R.mipmap.ic_launcher,SignInActivity::class.java,false))
        adapter.add(ScreenItemBean(WebViewActivity::class.java.simpleName,"",R.mipmap.ic_launcher,WebViewActivity::class.java,false))
        adapter.add(ScreenItemBean(DownloadActivity::class.java.simpleName,"",R.mipmap.ic_launcher,DownloadActivity::class.java,false))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): LogicFragment {

            val args = Bundle()

            val fragment = LogicFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
