package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterPath.IM_ACTIVITY_PERSONAL_CHAT)
public class PersonalChatActivity extends BaseActivity {
    @BindView(R2.id.chat_panel)
    ChatLayout chatPanel;
    @Autowired
    ChatInfo chatInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        chatPanel.initDefault();
        chatPanel.setChatInfo(chatInfo);

    }

    @Override
    public boolean isDarkFont() {
        return true;
    }

    @Override
    public boolean isImmersionEnable() {
        return true;
    }

    @Nullable
    @Override
    public View getToolbar() {
        return chatPanel.getTitleBar();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chatPanel.exitChat();
    }
}
