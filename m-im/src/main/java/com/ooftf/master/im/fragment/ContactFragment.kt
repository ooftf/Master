package com.ooftf.master.im.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.im.R
import com.ooftf.service.base.BaseLazyFragment
import kotlinx.android.synthetic.main.im_fragment_contact.*

@Route(path = "/im/fragment/contact")
class ContactFragment : BaseLazyFragment() {
    override fun onLoad(rootView: View) {
        contact_layout.initDefault()
    }

    override fun getLayoutId(): Int {
        return R.layout.im_fragment_contact
    }

    override fun getToolbarId(): Int {
        return R.id.contact_titlebar
    }

    override fun isDarkFont(): Boolean {
        return true
    }
}