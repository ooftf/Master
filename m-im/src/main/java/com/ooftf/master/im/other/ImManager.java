package com.ooftf.master.im.other;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.ImApp;
import com.ooftf.master.im.data.TencentImConts;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.router.service.IMultiSignService;
import com.ooftf.log.JLog;
import com.ooftf.service.utils.TimeRuler;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tls.tls_sigature.tls_sigature;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/4 0004
 */
public class ImManager {
    public static void init() {

        initTencentIm();
        IMultiSignService service = ARouter.getInstance().navigation(IMultiSignService.class);
        if (service.getCurrentService().isSignIn()) {
            imSignIn(service);
        }
        service.getCurrentService().subscribeSignIn().subscribe(s -> {
            // identifier为用户名，userSig 为用户登录凭证
            imSignIn(service);
        });
    }

    private static void imSignIn(IMultiSignService service) {
        JLog.e("ImAppOnCreate");
        Log.e("ImApp,getUserId", service.getCurrentService().getUserId());
        String userId = service.getCurrentService().getUserName();
        tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(TencentImConts.SDK_APP_ID, userId, TencentImConts.PRI_KEY, 24 * 3600 * 180);

        JLog.e("ImAppOnCreate userId", userId);
        JLog.e("ImAppOnCreate urlSig", result.urlSig);
        JLog.e("ImAppOnCreate urlSig", result.errMessage);
        TUIKit.login(userId, result.urlSig, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                /**
                 * IM 登录成功后的回调操作，一般为跳转到应用的主页（这里的主页内容为下面章节的会话列表）
                 */
                Log.e("TUIKit", "imlogin succ");
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Log.e("imlogin fail", errMsg);
            }
        });
    }

    private static void initTencentIm() {
        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        TUIKit.init(ImApp.getApplication(), TencentImConts.SDK_APP_ID, configs);
    }
}
