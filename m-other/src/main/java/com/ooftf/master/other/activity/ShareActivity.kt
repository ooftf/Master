package com.ooftf.master.other.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.hishare.HiShare
import com.ooftf.master.other.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.widget.dialog.ShareDialog
import kotlinx.android.synthetic.main.activity_share.*


@Route(path = "/other/activity/share")
class ShareActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        wbShare.setOnClickListener {
            share()
        }
    }

    private fun share() {
        var dialog = ShareDialog(this);
        var shareData = ShareDialog.ShareData()
        shareData.params = HiShare.ShareParams("http://www.baidu.com", "标题", "这是个内容", null, BitmapFactory.decodeResource(resources, com.ooftf.master.other.R.drawable.logo_legacy))
        shareData.channelShare.add(ShareDialog.ShareItem("QQ好友", R.mipmap.ic_qq, HiShare.ShareType.QQ_FRIEND))
        shareData.channelShare.add(ShareDialog.ShareItem("微信好友", R.mipmap.ic_wx_freind, HiShare.ShareType.WX_FRIEND))
        shareData.channelShare.add(ShareDialog.ShareItem("微信朋友圈", R.mipmap.ic_wx_moment, HiShare.ShareType.WX_MOMENT))
        dialog.setData(shareData);
        dialog.show()
    }
}
