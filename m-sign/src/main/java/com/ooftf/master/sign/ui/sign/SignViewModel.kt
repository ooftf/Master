package com.ooftf.master.sign.ui.sign

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.basic.armor.InitLiveData
import com.ooftf.master.sign.net.SignMobServiceHolder
import com.ooftf.master.sign.provider.SignServiceImpl
import com.ooftf.service.engine.router.assist.ISignService

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/16
 */
class SignViewModel(application: Application) : BaseViewModel(application) {
    val username = InitLiveData("")
    val password = InitLiveData("")
    fun signIn() {
        SignMobServiceHolder.signMobService
                .signIn(username.value, password.value)
                .setLiveData(baseLiveData)
                .bindDialog()
                .doOnResponseSuccess { call, body ->
                    (ARouter.getInstance().navigation(ISignService::class.java) as SignServiceImpl)
                            .let {
                                it.signIn(body.result)
                            }
                    baseLiveData.post(body)
                }
    }
}