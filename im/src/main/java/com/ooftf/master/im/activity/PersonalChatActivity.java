package com.ooftf.master.im.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.tencent.qcloud.uikit.business.chat.c2c.view.C2CChatPanel;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RouterPath.IM_ACTIVITY_PERSONAL_CHAT)
public class PersonalChatActivity extends BaseActivity {
    @BindView(R2.id.chat_panel)
    C2CChatPanel chatPanel;
    @Autowired
    String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_chat);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        chatPanel.initDefault();
        chatPanel.setBaseChatId(chatId);
    }

    @Override
    public boolean isImmersionEnable() {
        return false;
    }
}