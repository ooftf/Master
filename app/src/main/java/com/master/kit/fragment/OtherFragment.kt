package com.master.kit.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.master.kit.R
import com.master.kit.adapter.MainRecyclerAdapter
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.BaseFragment
import com.master.kit.testcase.AlbumActivity
import com.master.kit.testcase.CameraActivity
import com.master.kit.testcase.annotation.AnnotationActivity
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.master.kit.bean.ScreenItemBean
import com.master.kit.testcase.filedownload.DownloadActivity

/**
 * Created by master on 2017/9/26 0026.
 */

class OtherFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean(AlbumActivity::class.java.simpleName,"",R.mipmap.ic_launcher, AlbumActivity::class.java,false))
        adapter.add(ScreenItemBean(AnnotationActivity::class.java.simpleName,"",R.mipmap.ic_launcher,AnnotationActivity::class.java,false))
        adapter.add(ScreenItemBean(CameraActivity::class.java.simpleName,"",R.mipmap.ic_launcher,CameraActivity::class.java,false))
        adapter.add(ScreenItemBean(SoftKeyboardActivity::class.java.simpleName,"",R.mipmap.ic_launcher,SoftKeyboardActivity::class.java,false))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): OtherFragment {

            val args = Bundle()

            val fragment = OtherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
