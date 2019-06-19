package com.ooftf.master.im.fragment;


import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseFragment;
import com.ooftf.service.base.BaseLazyFragment;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationFragment extends BaseLazyFragment {


    @BindView(R2.id.session_panel)
    ConversationLayout sessionPanel;

    @Override
    public void onLoad() {
        ButterKnife.bind(getView());
        sessionPanel.initDefault();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_im_main;
    }
}
