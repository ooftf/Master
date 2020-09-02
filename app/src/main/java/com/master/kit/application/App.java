package com.master.kit.application;

import android.content.Context;
import android.view.View;

import com.ooftf.service.base.BaseApplication;
import com.ooftf.log.JLog;
import com.wanjian.cockroach.Cockroach;

import org.jetbrains.annotations.Nullable;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/3 0003
 */
public class App extends BaseApplication {
    private static App instance;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // initMatrix();
        //installCockroach();
    }

   /* private void initMatrix() {
        // build matrix
        Matrix.Builder builder = new Matrix.Builder(this);
        // add general pluginListener
        builder.patchListener(new MasterPluginListener(this));
        // dynamic config
        IDynamicConfig dynamicConfig = new MasterDynamicConfig();

        // onCreate plugin
        IOCanaryPlugin ioCanaryPlugin = new IOCanaryPlugin(new IOConfig.Builder()
                .dynamicConfig(dynamicConfig)
                .build());
        //add to matrix
        builder.plugin(ioCanaryPlugin);

        //onCreate matrix
        Matrix.init(builder.build());

        // start plugin
        ioCanaryPlugin.start();
    }*/

    public static App getInstance() {
        return instance;
    }

    private void installCockroach() {
        // 可以达到产生崩溃自动finish activity的效果，但是再某些情况下会导致白屏切无法操作（比如react那个页面）
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(Thread thread, Throwable throwable) {
                JLog.e("Cockroach", throwable.toString());
            }
        });

    }

    @Override
    protected void attachBaseContext(@Nullable Context base) {
        super.attachBaseContext(base);
    }
}
