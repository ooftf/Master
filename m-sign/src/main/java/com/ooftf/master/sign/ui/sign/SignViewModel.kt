package com.ooftf.master.sign.ui.sign

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.hihttp.action.ButtonAction
import com.ooftf.master.sign.provider.SignServiceImpl
import com.ooftf.service.empty.EmptyObserver
import com.ooftf.service.engine.router.assist.SignAssistBean
import com.trello.rxlifecycle3.android.RxLifecycleAndroid
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/16
 */
class SignViewModel(application: Application) : BaseViewModel(application) {
    val username = MutableLiveData("")
    val password = MutableLiveData("")
    fun signIn() {
        SignServiceImpl.getInstance().signIn()
        getModule()
                .signIn(getView().getChannelId(), getView().getUsername(), getView().getPassword())
                .observeOn(AndroidSchedulers.mainThread())
                .compose<Any>(ButtonAction<Any>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose<Any>(RxLifecycleAndroid.bindView<Any>(getView().getSinInLoadingButton()))
                .subscribe(object : EmptyObserver<SignAssistBean>() {
                    override fun onNext(bean: SignAssistBean) {
                        if (bean.isResult) {
                            getModule().switchToChannel(getView().getChannelId())
                            getView().nextActivity()
                        } else {
                            getView().showDialogMessage(bean.msg)
                        }
                    }
                })
    }
}