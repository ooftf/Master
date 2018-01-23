package com.master.kit.activity.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.google.gson.Gson
import com.master.kit.R
import com.master.kit.net.ServiceHolder
import com.ooftf.hi.controller.HiPresenterObserver
import com.ooftf.hi.view.HiResponseDialog
import com.ooftf.hi.view.HiResponseView
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_mob_api.*

class MobApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mob_api)
        bankCardQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .bankQuery("3ab0f1586b2",bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        phoneQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .phoneQuery("3ab0f1586b2",phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        idCardQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .idCardQuery("3ab0f1586b2",idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        postcodeQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .postcodeQuery("3ab0f1586b2",postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        ipQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .ipQuery("3ab0f1586b2",ip.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
    }
    inner class BankCardObserve<T>(view : HiResponseView<T>): HiPresenterObserver<T>(view){
        override fun onNext(value: T) {
            super.onNext(value)
            AlertDialog
                    .Builder(this@MobApiActivity)
                    .setMessage(Gson().toJson(value))
                    .show()
        }
    }
}
