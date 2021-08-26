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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@Route(path = "/applet/activity/mobApi")
class MobApiActivity : BaseViewBindingActivity<ActivityMobApiBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bankCardQuery.setOnClickListener {
            MobService()
                    .bankQuery(binding.bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.phoneQuery.setOnClickListener {
            MobService()
                    .phoneQuery(binding.phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.idCardQuery.setOnClickListener {
            MobService()
                    .idCardQuery(binding.idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.postcodeQuery.setOnClickListener {
            MobService()
                    .postcodeQuery(binding.postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        binding.ipQuery.setOnClickListener {
            MobService()
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
