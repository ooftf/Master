package com.ooftf.master.source.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.source.databinding.ActivityOkHttpBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

@Route(path = "/source/activity/okHttp")
class OkHttpActivity : BaseViewBindingActivity<ActivityOkHttpBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener {
            val client = OkHttpClient.Builder().build()
            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val requestBody = RequestBody.create(mediaType, "")
            val request = Request.Builder().url("").post(requestBody).build()
            client.newCall(request).execute().body?.string()
        }
    }
}
