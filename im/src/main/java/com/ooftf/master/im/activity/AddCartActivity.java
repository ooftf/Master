package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/21 0021
 */
@Route(path = RouterPath.IM_ACTIVITY_ADD_CHAT)
public class AddCartActivity extends BaseActivity {

    @BindView(R2.id.start_c2c_chat_title)
    TitleBarLayout startC2cChatTitle;
    @BindView(R2.id.contact_list_view)
    ContactListView contactListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_add_cart);
        ButterKnife.bind(this);
        contactListView.setSingleSelectMode(true);
        contactListView.loadDataSource(ContactListView.DataSource.FRIEND_LIST);
    }

    @Nullable
    @Override
    public View getToolbar() {
        return startC2cChatTitle;
    }
}
