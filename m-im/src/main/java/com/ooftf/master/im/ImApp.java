package com.ooftf.master.im;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.docking.api.IApplication;
import com.ooftf.docking.api.MainProcess;
import com.ooftf.master.im.data.TencentImConts;
import com.ooftf.service.base.BaseApplication;
import com.ooftf.service.engine.router.service.IMultiSignService;
import com.ooftf.log.JLog;
import com.ooftf.service.utils.ThreadUtil;
import com.ooftf.service.utils.TimeRuler;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.session.SessionWrapper;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tls.tls_sigature.tls_sigature;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/4/28 0028
 */
public class ImApp implements IApplication {
    private static Application application;

    @Override
    public void init(Application application) {
        ImApp.application = application;
    }
    @MainProcess
    @Override
    public void onCreate(Application application) {


    }

    @Override
    public void attachBaseContext(Context context) {

    }

    @Override
    public int getPriority() {
        return 0;
    }

    public static Application getApplication() {
        return application;
    }
}
