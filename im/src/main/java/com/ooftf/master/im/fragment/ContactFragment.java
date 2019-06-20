package com.ooftf.master.im.fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.service.base.BaseLazyFragment;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/im/fragment/contact")
public class ContactFragment extends BaseLazyFragment {


    @BindView(R2.id.contact_layout)
    ContactLayout contactLayout;

    @Override
    public void onLoad() {
        ButterKnife.bind(this, getView());
        contactLayout.initDefault();
    }

    @Override
    public int getLayoutId() {
        return R.layout.im_fragment_contact;
    }

    @Override
    public int getToolbarId() {
        return R.id.contact_titlebar;
    }

    @Override
    public boolean isDarkFont() {
        return true;
    }
}
