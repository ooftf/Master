package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

@Route(path = "/source/activity/okHttp")
class OkHttpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)
        button.setOnClickListener {
            var client = OkHttpClient.Builder().build()
            var requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")
            var request = Request.Builder().url("").post(requestBody).build()
            client.newCall(request).execute().body()?.string()
        }
    }
}
