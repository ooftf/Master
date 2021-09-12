package com.ooftf.master.sign.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.arch.frame.mvvm.activity.BaseActivity;
import com.ooftf.basic.AppHolder;
import com.ooftf.master.session.di.SingletonEntryPoint;
import com.ooftf.service.constant.RouterExtra;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.engine.router.PostcardSerializable;
import com.ooftf.master.session.m.sign.ISignService;
import com.ooftf.log.JLog;

import dagger.hilt.android.EntryPointAccessors;

/**
 * 登录拦截器
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Interceptor(priority = 1)
public class SignInInterceptor implements IInterceptor {

    ISignService multiAccountService;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        int extra = postcard.getExtra();
        if (RouterExtra.isNeedSign(extra) && !multiAccountService.isSignIn()) {
            JLog.e("interceptor", postcard.getPath());
            ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_SIGN_IN).withBundle(BaseActivity.INTENT_DATA_SUCCESS_INTENT, PostcardSerializable.toBundle(postcard)).navigation();
            callback.onInterrupt(null);
        } else {
            JLog.e("onContinue", postcard.getPath());
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        multiAccountService = EntryPointAccessors.fromApplication(AppHolder.INSTANCE.getApp(), SingletonEntryPoint.class).getSignService();
    }
}
