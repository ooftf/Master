package com.ooftf.applet.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.applet.databinding.ActivityMobApiBinding
import com.ooftf.applet.net.MobService
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.service.empty.EmptyObserver
import com.trello.rxlifecycle4.kotlin.bindToLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@Route(path = "/applet/activity/mobApi")
@AndroidEntryPoint
class MobApiActivity : BaseViewBindingActivity<ActivityMobApiBinding>() {
    @Inject
    lateinit var mobApi:MobService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bankCardQuery.setOnClickListener {
            mobApi
                    .bankQuery(binding.bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.phoneQuery.setOnClickListener {
            mobApi
                    .phoneQuery(binding.phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.idCardQuery.setOnClickListener {
            mobApi
                    .idCardQuery(binding.idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.postcodeQuery.setOnClickListener {
            mobApi
                    .postcodeQuery(binding.postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.ipQuery.setOnClickListener {
            mobApi
                    .ipQuery(binding.ip.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
    }

    inner class BankCardObserve<T> : EmptyObserver<T>() {
        override fun onNext(value: T) {
            super.onNext(value)
            AlertDialog
                    .Builder(this@MobApiActivity)
                    .setMessage(Gson().toJson(value))
                    .show()
        }
    }
}
