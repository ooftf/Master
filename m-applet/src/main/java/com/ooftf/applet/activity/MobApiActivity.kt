package com.ooftf.applet.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.applet.net.MobService
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.empty.EmptyObserver
import com.trello.rxlifecycle4.kotlin.bindToLifecycle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_mob_api.*

@Route(path = "/applet/activity/mobApi")
class MobApiActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mob_api)
        bankCardQuery.setOnClickListener {
            MobService()
                    .bankQuery(bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        phoneQuery.setOnClickListener {
            MobService()
                    .phoneQuery(phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        idCardQuery.setOnClickListener {
            MobService()
                    .idCardQuery(idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        postcodeQuery.setOnClickListener {
            MobService()
                    .postcodeQuery(postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve())
        }
        ipQuery.setOnClickListener {
            MobService()
                    .ipQuery(ip.text.toString())
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
