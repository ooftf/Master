package com.ooftf.master.other.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseListActivity
import com.ooftf.service.base.adapter.ActivityIntentListAdapter
import com.ooftf.service.bean.ActivityItemBean


@Route(path = "/other/pickPhotoList")
class PickPhotoListActivity : BaseListActivity() {
    override fun setListData(adapter: ActivityIntentListAdapter) {
        adapter.add(ActivityItemBean( "/other/takePhoto","TakePhoto库"))
        adapter.add(ActivityItemBean( "/other/matisseActivity","Matisse库"))

    }

}
