package com.ooftf.master.im.fragment

import android.content.Intent
import android.graphics.Color
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.master.im.ImApp
import com.ooftf.master.im.R
import com.ooftf.master.im.databinding.ImFragmentContactBinding
import com.ooftf.master.im.modules.friendprofile.FriendProfileActivity
import com.ooftf.master.im.modules.newfriend.NewFriendActivity
import com.ooftf.service.constant.RouterPath
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants


@Route(path = "/im/fragment/contact")
class ContactFragment : BaseViewBindingFragment<ImFragmentContactBinding>() {
    override fun onLoad(rootView: View) {
        super.onLoad(rootView)
        binding.contactLayout.initDefault()
        itemClick()
        initToolbar()
    }

    private fun initToolbar() {
        binding.contactLayout.titleBar.findViewById<View>(R.id.page_title_layout).setBackgroundColor(Color.TRANSPARENT)
        binding.contactLayout.titleBar.setOnRightClickListener { v: View -> ARouter.getInstance().build(RouterPath.IM_ACTIVITY_ADD_CHAT).withBoolean(TUIKitConstants.GroupType.GROUP, false).navigation() }
        binding.contactLayout.titleBar.setBackgroundColor(Color.WHITE)
    }


    fun itemClick(){
        binding.contactLayout.contactListView.setOnItemClickListener { position, contact ->
            if (position == 0) {
                val intent = Intent(ImApp.getApplication(), NewFriendActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                ImApp.getApplication().startActivity(intent)
            } else if (position == 1) {
                /* val intent = Intent(ImApp.getApplication(), GroupListActivity::class.java)
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                 ImApp.getApplication().startActivity(intent)*/
            } else if (position == 2) {
                /* val intent = Intent(ImApp.getApplication(), BlackListActivity::class.java)
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                 ImApp.getApplication().startActivity(intent)*/
            } else {
                val intent = Intent(ImApp.getApplication(), FriendProfileActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra(TUIKitConstants.ProfileType.CONTENT, contact)
                ImApp.getApplication().startActivity(intent)
            }
        }
    }

    override fun getToolbarId(): Int {
        return R.id.contact_titlebar
    }

    override fun isDarkFont(): Boolean {
        return true
    }
}