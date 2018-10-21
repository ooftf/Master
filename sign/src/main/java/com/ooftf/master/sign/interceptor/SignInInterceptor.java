package com.ooftf.master.sign.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.ooftf.service.constant.BusAction;
import com.ooftf.service.constant.RouterExtra;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.interfaces.SignService;
import com.ooftf.service.utils.JLog;

/**
 * 登录拦截器
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Interceptor(priority = 8)
public class SignInInterceptor implements IInterceptor {
    Context mContext;
    @Autowired
    SignService signService;
    Postcard mPostcard;
    InterceptorCallback mCallback;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        int extra = postcard.getExtra();
        if (RouterExtra.isNeedSign(extra) && !signService.isSignIn()) {
            ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_SIGN_IN).navigation();
            mPostcard = postcard;
            mCallback = callback;
        } else {
            callback.onContinue(postcard);
        }
    }

    @Subscribe(
            tags = {
                    @Tag(BusAction.SIGN_IN_SUCCESS)
            }
    )
    public void onSignSucess(String string) {
        JLog.e("onSignSucess");
        if (mCallback != null && mPostcard != null) {
            mCallback.onContinue(mPostcard);
            mCallback = null;
            mPostcard = null;
        }

    }

    @Subscribe(
            tags = {
                    @Tag(BusAction.SIGN_IN_FAIL)
            }
    )

    public void onSignFail(String string) {
        JLog.e("onSignFail");
        if (mCallback != null && mPostcard != null) {
            mCallback.onInterrupt(new Exception("登录失败"));
            mCallback = null;
            mPostcard = null;
        }

    }

    @Override
    public void init(Context context) {
        mContext = context;
        ARouter.getInstance().inject(this);
        RxBus.get().register(this);
    }
}
