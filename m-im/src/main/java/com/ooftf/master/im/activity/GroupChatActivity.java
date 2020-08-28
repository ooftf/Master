package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import org.jetbrains.annotations.NotNull;
/**
 * @author 99474
 */
@Route(path = RouterPath.IM_ACTIVITY_GROUP_CHAT)
public class GroupChatActivity extends BaseActivity {

    @Autowired
    String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        ARouter.getInstance().inject(this);
    }

    @Override
    public boolean isDarkFont() {
        return true;
    }

    @Override
    public boolean isImmersionEnable() {
        return true;
    }
    @NotNull
    @Override
    public View[] getToolbar() {
        return new View[0];
    }
}
