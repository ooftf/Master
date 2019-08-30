package com.ooftf.master.m.monitor;

import android.app.Application;
import android.content.Context;

import com.ooftf.docking.api.IApplication;
import com.ooftf.service.base.BaseApplication;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/30 0030
 */
public class MonitorApp implements IApplication {
    @Override
    public void init(Application application) {
//        /BaseApplication.instance
    }

    @Override
    public void onCreate() {

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

    @Override
    public int getPriority() {
        return 0;
    }
}
