package com.ooftf.master.sign.ui.register

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.basic.armor.InitLiveData
import com.ooftf.master.sign.net.SignMobService
import com.ooftf.service.utils.extend.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    val username = InitLiveData("")
    val password = InitLiveData("")
    @Inject
    lateinit var signMobApi:SignMobService
    fun register() {
        signMobApi.register(username.value, password.value)
                .setLiveData(baseLiveData)
                .bindDialog()
                .doOnResponseSuccess { call, body ->
                    toast("注册成功")
                }
    }
}