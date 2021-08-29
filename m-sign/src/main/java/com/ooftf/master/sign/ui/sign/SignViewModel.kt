package com.ooftf.master.sign.ui.sign

import android.app.Application
import com.ooftf.arch.frame.mvvm.vm.BaseViewModel
import com.ooftf.basic.AppHolder
import com.ooftf.basic.armor.InitLiveData
import com.ooftf.basic.utils.DensityUtil
import com.ooftf.basic.utils.toast
import com.ooftf.log.JLog
import com.ooftf.master.sign.net.SignMobService
import com.ooftf.master.sign.provider.SignServiceImpl
import com.tencent.connect.UnionInfo
import com.tencent.connect.UserInfo
import com.tencent.tauth.IUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/16
 */
@HiltViewModel
class SignViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    val username = InitLiveData("")
    val password = InitLiveData("")
    val keyBoardShow = InitLiveData(false)
    val marginTop = InitLiveData(DensityUtil.dip2px(SignInActivity.MAX_SIZE).toInt())
    @Inject
    lateinit var signMobApi:SignMobService
    @Inject
    lateinit var tencent:Tencent
    @Inject
    lateinit var signService:SignServiceImpl

    fun signIn() {
        signMobApi
                .signIn(username.value, password.value)
                .setLiveData(baseLiveData)
                .bindDialog()
                .doOnResponseSuccess { call, body ->
                    signService
                            .let {
                                it.signIn(body.data)
                            }
                    baseLiveData.post(body)
                }
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