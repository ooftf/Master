package com.ooftf.master.sign.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
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

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        int extra = postcard.getExtra();
        if (RouterExtra.isNeedSign(extra) && !signService.isSignIn()) {
            Object navigation = ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_SIGN_IN).navigation();
            JLog.e("navigation" + navigation);
            callback.onInterrupt(new Exception("未登录"));
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        ARouter.getInstance().inject(this);
    }
}
