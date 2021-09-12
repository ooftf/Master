package com.ooftf.master.im;

import android.app.Application;
import android.content.Context;
import com.ooftf.docking.api.IApplication;
import com.ooftf.docking.api.MainProcess;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/4/28 0028
 */
public class ImApp implements IApplication {
    private static Application application;

    @MainProcess
    @Override
    public void onCreate(Application application) {
        ImApp.application = application;

    }


    @Override
    public int getPriority() {
        return 0;
    }

    public static Application getApplication() {
        return application;
    }
}
