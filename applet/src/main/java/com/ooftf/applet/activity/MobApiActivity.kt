package com.ooftf.applet.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.hihttp.action.DialogAction
import com.ooftf.hihttp.action.ImageViewAction
import com.ooftf.service.empty.EmptyObserver
import com.ooftf.service.net.ServiceHolder
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_mob_api.*

@Route(path = "/applet/activity/mobApi")
class MobApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mob_api)
        bankCardQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .bankQuery(bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(ImageViewAction(bankCardQuery))
                    .subscribe(BankCardObserve())
        }
        phoneQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .phoneQuery(phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(DialogAction(this))
                    .subscribe(BankCardObserve())
        }
        idCardQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .idCardQuery(idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(DialogAction(this))
                    .subscribe(BankCardObserve())
        }
        postcodeQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .postcodeQuery(postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(DialogAction(this))
                    .subscribe(BankCardObserve())
        }
        ipQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .ipQuery(ip.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(DialogAction(this))
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
