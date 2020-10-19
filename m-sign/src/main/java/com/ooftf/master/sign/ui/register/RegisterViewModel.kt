package com.ooftf.master.sign.ui.register

import android.app.Application
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.basic.armor.InitLiveData
import com.ooftf.master.sign.net.SignMobServiceHolder
import com.ooftf.service.utils.extend.toast

class RegisterViewModel(application: Application) : BaseViewModel(application) {
    val username = InitLiveData("")
    val password = InitLiveData("")
    fun register() {
        SignMobServiceHolder.signMobService.register(username.value, password.value)
                .setLiveData(baseLiveData)
                .bindDialog()
                .doOnResponseSuccess { call, body ->
                    toast("注册成功")
                }
    }
}