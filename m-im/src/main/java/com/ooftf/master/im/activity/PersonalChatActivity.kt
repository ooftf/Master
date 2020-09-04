package com.ooftf.master.im.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.master.im.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import kotlinx.android.synthetic.main.activity_personal_chat.*

@Route(path = RouterPath.IM_ACTIVITY_PERSONAL_CHAT)
class PersonalChatActivity : BaseActivity() {

    @JvmField
    @Autowired
    var chatInfo: ChatInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_chat)
        ARouter.getInstance().inject(this)
        chat_panel.initDefault()
        chat_panel.setChatInfo(chatInfo)
    }

    override fun isDarkFont(): Boolean {
        return true
    }

    override fun isImmersionEnable(): Boolean {
        return true
    }

    override fun getToolbar(): Array<View> {
        return arrayOf(chat_panel.titleBar)
    }

    public override fun onDestroy() {
        super.onDestroy()
        chat_panel.exitChat()
    }
}