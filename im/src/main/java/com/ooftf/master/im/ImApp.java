package com.ooftf.master.im;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.docking.api.IApplication;
import com.ooftf.master.im.data.TencentImConts;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.router.service.IMultiSignService;
import com.ooftf.service.utils.JLog;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.uikit.BaseUIKitConfigs;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.common.IUIKitCallBack;
import com.tls.tls_sigature.tls_sigature;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/4/28 0028
 */
@com.ooftf.docking.annotation.Application
public class ImApp implements IApplication {


    @Override
    public void onCreate(Application application) {
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

    private void imSignIn(IMultiSignService service) {
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

    private void initTencentIm() {
        //初始化 SDK 基本配置
        TIMSdkConfig config = new TIMSdkConfig(TencentImConts.SDK_APP_ID)
                // 您可以从 IM 控制台获取 accountType 信息。目前仅支持独立模式，即36862
                .setAccoutType("36862")
                // 是否在控制台打印Log?
                .enableLogPrint(true)
                // Log输出级别（debug级别会很多）
                .setLogLevel(TIMLogLevel.DEBUG)
                .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
        // Log文件存放在哪里？
        //应替换成（BaseUIKitConfigs的配置请看后面章节）
        TUIKit.init(BaseApplication.instance, TencentImConts.SDK_APP_ID, BaseUIKitConfigs.getDefaultConfigs().setTIMSdkConfig(config));
    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void attachBaseContext(Context context) {

    }
}
