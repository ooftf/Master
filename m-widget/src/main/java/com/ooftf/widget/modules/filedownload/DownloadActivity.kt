package com.ooftf.widget.modules.filedownload

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.R
import com.ooftf.widget.databinding.ActivityDownloadBinding

@Route(path = "/main/download")
class DownloadActivity : BaseViewBindingActivity<ActivityDownloadBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        binding.button.setOnClickListener {
            val intent = Intent(this, DownloadService::class.java)
            startService(intent)
        }
    }
}
