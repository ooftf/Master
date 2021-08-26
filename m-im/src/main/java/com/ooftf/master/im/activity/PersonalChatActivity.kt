package com.ooftf.master.im.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.im.databinding.ActivityPersonalChatBinding
import com.ooftf.service.constant.RouterPath
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo

@Route(path = RouterPath.IM_ACTIVITY_PERSONAL_CHAT)
class PersonalChatActivity : BaseViewBindingActivity<ActivityPersonalChatBinding>() {

    @JvmField
    @Autowired
    var chatInfo: ChatInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        binding.chatPanel.initDefault()
        binding.chatPanel.setChatInfo(chatInfo)
    }

    override fun isDarkFont(): Boolean {
        return true
    }

    override fun isImmersionEnable(): Boolean {
        return true
    }

    override fun getToolbar(): Array<View> {
        return arrayOf(binding.chatPanel.titleBar)
    }

    public override fun onDestroy() {
        super.onDestroy()
        binding.chatPanel.exitChat()
    }
}