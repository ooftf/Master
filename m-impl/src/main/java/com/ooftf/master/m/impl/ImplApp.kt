package com.ooftf.master.m.impl

import android.app.Activity
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.docking.api.IApplication
import com.ooftf.mapping.lib.HttpUiMapping
import com.ooftf.mapping.lib.IResponse
import com.ooftf.master.m.impl.ui.LoadingDialog
import com.ooftf.master.unit.am.ActivityManager
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.router.assist.ISignService

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2020/10/27
 */
class ImplApp : IApplication {
    override fun init(application: Application) {

    }

    override fun onCreate(application: Application) {
        HttpUiMapping.init(object : HttpUiMapping.Provider {
            override fun createLoadingDialog(activity: Activity): HttpUiMapping.MyDialogInterface {
                return LoadingDialog(activity)
            }

            override fun onTokenInvalid(baseResponse: IResponse) {
                ARouter.getInstance().navigation(ISignService::class.java)?.run {
                    if (isSignIn) {
                        //clear()
                        ActivityManager.finishAll()
                        ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_SIGN_IN).navigation()
                    }
                }
            }

            override fun toast(string: String?) {
                string?.let {
                    com.ooftf.basic.utils.toast(it)
                }

            }

        }, BuildConfig.DEBUG)
    }

    override fun attachBaseContext(context: Context) {
    }

    override fun getPriority(): Int {
        return 10
    }
}