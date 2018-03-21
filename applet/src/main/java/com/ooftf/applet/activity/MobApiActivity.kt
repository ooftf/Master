package com.ooftf.applet.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.gson.Gson
import com.ooftf.applet.R
import com.ooftf.hi.controller.HiPresenterObserver
import com.ooftf.hi.view.HiResponseDialog
import com.ooftf.hi.view.HiResponseView
import com.ooftf.service.net.ServiceHolder
import com.ooftf.support.MaterialProgressDrawable
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_mob_api.*

@Route(path = "/applet/mobApi")
class MobApiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mob_api)
        bankCardQuery.setOnClickListener {
            val materialProgressDrawable = MaterialProgressDrawable(this, bankCardQuery)
            //materialProgressDrawable.setBackgroundColor(Color.parseColor("#00000000"))
            materialProgressDrawable.setColorSchemeColors(Color.parseColor("#00FF00"))
            bankCardQuery.setImageDrawable(materialProgressDrawable)
            //materialProgressDrawable.setStartEndTrim(0.2f, 0.8f);
            materialProgressDrawable.start()
            ServiceHolder
                    .mobService
                    .bankQuery(bankCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        phoneQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .phoneQuery(phone.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        idCardQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .idCardQuery(idCard.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        postcodeQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .postcodeQuery(postcode.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
        ipQuery.setOnClickListener {
            ServiceHolder
                    .mobService
                    .ipQuery(ip.text.toString())
                    .bindToLifecycle(window.decorView)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(BankCardObserve(HiResponseDialog(this)))
        }
    }

    inner class BankCardObserve<T>(view: HiResponseView<T>) : HiPresenterObserver<T>(view) {
        override fun onNext(value: T) {
            super.onNext(value)
            AlertDialog
                    .Builder(this@MobApiActivity)
                    .setMessage(Gson().toJson(value))
                    .show()
        }
    }
}
