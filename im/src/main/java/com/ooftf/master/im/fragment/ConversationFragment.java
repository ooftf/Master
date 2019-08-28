package com.ooftf.master.im.fragment;


import android.graphics.Color;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseLazyFragment;
import com.ooftf.service.constant.RouterPath;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/im/fragment/conversation")
public class ConversationFragment extends BaseLazyFragment {


    @BindView(R2.id.conversation_layout)
    ConversationLayout conversationLayout;

    @Override
    public void onLoad() {
        ButterKnife.bind(this, getView());
        conversationLayout.initDefault();
        conversationLayout.getConversationList().setOnItemClickListener((view, i, conversationInfo) -> {
            //此处为demo的实现逻辑，更根据会话类型跳转到相关界面，开发者可根据自己的应用场景灵活实现
            ChatInfo chatInfo = new ChatInfo();
            chatInfo.setType(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
            chatInfo.setId(conversationInfo.getId());
            chatInfo.setChatName(conversationInfo.getTitle());
            ARouter.getInstance().build(RouterPath.IM_ACTIVITY_PERSONAL_CHAT).withSerializable("chatInfo", chatInfo).navigation();
        });
        conversationLayout.getTitleBar().findViewById(R.id.page_title_layout).setBackgroundColor(Color.TRANSPARENT);
        conversationLayout.getTitleBar().setOnRightClickListener(v -> ARouter.getInstance().build(RouterPath.IM_ACTIVITY_ADD_CHAT).withBoolean(TUIKitConstants.GroupType.GROUP, false).navigation());
        conversationLayout.getTitleBar().setBackgroundColor(Color.WHITE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.im_fragment_conversation;
    }

    @Override
    public int getToolbarId() {
        return R.id.conversation_title;
    }

    @Override
    public boolean isDarkFont() {
        return true;
    }

    @Override
    public void onDestroy() {
        ConversationManagerKit.getInstance().destroyConversation();
        super.onDestroy();

    }
}
