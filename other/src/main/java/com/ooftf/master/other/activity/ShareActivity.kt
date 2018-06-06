package com.ooftf.master.other.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.hishare.HiShare
import com.ooftf.hishare.sina.WbPlatform
import com.ooftf.master.other.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.utils.ShareUtil
import kotlinx.android.synthetic.main.activity_share.*
@Route(path = "/other/share")
class ShareActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        wbShare.setOnClickListener {
            ShareUtil.share(this,"http://www.baidu.com","标题","这是个内容",BitmapFactory.decodeResource(resources,R.drawable.logo_legacy))
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        HiShare.onNewIntent(intent)
    }
}
