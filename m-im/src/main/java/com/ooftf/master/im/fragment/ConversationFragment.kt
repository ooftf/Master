package com.ooftf.master.im.fragment

import android.graphics.Color
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.fragment.BaseLazyFragment
import com.ooftf.arch.frame.mvvm.fragment.BaseViewBindingFragment
import com.ooftf.master.im.R
import com.ooftf.master.im.databinding.ImFragmentConversationBinding
import com.ooftf.service.constant.RouterPath
import com.tencent.imsdk.TIMConversationType
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants

@Route(path = "/im/fragment/conversation")
class ConversationFragment : BaseViewBindingFragment<ImFragmentConversationBinding>() {
    override fun onLoad(rootView: View) {
        binding.conversationLayout.initDefault()
        binding.conversationLayout.conversationList.setOnItemClickListener { view: View?, i: Int, conversationInfo: ConversationInfo ->
            //此处为demo的实现逻辑，更根据会话类型跳转到相关界面，开发者可根据自己的应用场景灵活实现
            val chatInfo = ChatInfo()
            chatInfo.type = if (conversationInfo.isGroup) TIMConversationType.Group.value() else TIMConversationType.C2C.value()
            chatInfo.id = conversationInfo.id
            chatInfo.chatName = conversationInfo.title
            ARouter.getInstance().build(RouterPath.IM_ACTIVITY_PERSONAL_CHAT).withSerializable("chatInfo", chatInfo).navigation()
        }
        binding.conversationLayout.titleBar.findViewById<View>(R.id.page_title_layout).setBackgroundColor(Color.TRANSPARENT)
        binding.conversationLayout.titleBar.setOnRightClickListener { v: View? -> ARouter.getInstance().build(RouterPath.IM_ACTIVITY_ADD_CHAT).withBoolean(TUIKitConstants.GroupType.GROUP, false).navigation() }
        binding.conversationLayout.titleBar.setBackgroundColor(Color.WHITE)
    }

    override fun getToolbarId(): Int {
        return R.id.conversation_title
    }

    override fun isDarkFont(): Boolean {
        return true
    }

    override fun onDestroy() {
        ConversationManagerKit.getInstance().destroyConversation()
        super.onDestroy()
    }
}