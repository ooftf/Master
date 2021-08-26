package com.ooftf.master.sign.ui.sign

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.basic.AppHolder
import com.ooftf.basic.armor.InitLiveData
import com.ooftf.basic.utils.DensityUtil
import com.ooftf.basic.utils.toast
import com.ooftf.log.JLog
import com.ooftf.master.sign.net.SignMobService
import com.ooftf.master.sign.provider.SignServiceImpl
import com.ooftf.service.engine.router.assist.ISignService
import com.tencent.connect.UnionInfo
import com.tencent.connect.UserInfo
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import org.json.JSONObject

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/16
 */
class SignViewModel(application: Application) : BaseViewModel(application) {
    val username = InitLiveData("")
    val password = InitLiveData("")
    val keyBoardShow = InitLiveData(false)
    val marginTop = InitLiveData(DensityUtil.dip2px(SignInActivity.MAX_SIZE).toInt())
    fun signIn() {
        SignMobService()
                .signIn(username.value, password.value)
                .setLiveData(baseLiveData)
                .bindDialog()
                .doOnResponseSuccess { call, body ->
                    (ARouter.getInstance().navigation(ISignService::class.java) as SignServiceImpl)
                            .let {
                                it.signIn(body.data)
                            }
                    baseLiveData.post(body)
                }
    }

    val tencent by lazy {
        Tencent.createInstance("101516080", getApplication())
    }
    val listener = object : IUiListener {
        override fun onComplete(p0: Any?) {
            (p0 as? JSONObject)?.let {
                tencent.initSessionCache(p0)
                JLog.e("onComplete${p0::class.java}")
                JLog.e(p0)
            }
            //getUserInfo()
            //getUnionId()
        }

        override fun onError(p0: UiError?) {
        }

        override fun onCancel() {

        }

    }

    private fun getUserInfo() {
        val info = UserInfo(AppHolder.app, tencent.qqToken)
        info.getUserInfo(object : IUiListener {
            override fun onComplete(p0: Any?) {
                JLog.e("getUserInfo onComplete${p0!!::class.java}")
                JLog.e(p0)
            }

            override fun onError(p0: UiError?) {
                JLog.e("onError")
            }

            override fun onCancel() {
            }

        })
    }

    fun getUnionId() {
        UnionInfo(getApplication(), tencent.qqToken).getUnionId(object : IUiListener {
            override fun onComplete(p0: Any?) {
                JLog.e("getUnionId onComplete${p0!!::class.java}")
                JLog.e(p0)
                (p0 as? JSONObject)?.let {
                    tencent.startIMAio(getActivity(), "965661686", AppHolder.app.packageName).apply {
                        JLog.e("startIMAio::$this")
                    }
                }

            }

            override fun onError(p0: UiError?) {
            }

            override fun onCancel() {
            }

        })
    }

    fun qq() {
         if (!tencent.isSessionValid) {
             tencent.login(getActivity(), "all", listener)
         } else {
             toast("QQ已经处于登陆状态")
         }

       /* getApplication<Application>().startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=965661686")))*/
    }
}