package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.tencent.qcloud.uikit.business.chat.group.view.GroupChatPanel;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @author 99474
 */
@Route(path = RouterPath.IM_ACTIVITY_GROUP_CHAT)
public class GroupChatActivity extends BaseActivity {

    @BindView(R2.id.chat_panel)
    GroupChatPanel chatPanel;
    @Autowired
    String chatId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
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
