package com.master.kit.testcase.filedownload

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.master.kit.R
import kotlinx.android.synthetic.main.activity_download.*

class DownloadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        button.setOnClickListener {
            val intent = Intent(this, DownloadService::class.java)
            startService(intent)
        }
    }
}
