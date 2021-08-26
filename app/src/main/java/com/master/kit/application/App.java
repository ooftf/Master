package com.master.kit.application;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.master.kit.BuildConfig;
import com.master.kit.matrix.MyDynamicConfig;
import com.master.kit.matrix.MyPluginListener;
import com.ooftf.director.app.Director;
import com.ooftf.director.app.PanelDialog;
import com.ooftf.director.app.ShowEntranceSwitch;
import com.ooftf.log.JLog;
import com.ooftf.log.JsonParser;
import com.ooftf.service.base.BaseApplication;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.iocanary.IOCanaryPlugin;
import com.tencent.matrix.iocanary.config.IOConfig;
import com.wanjian.cockroach.Cockroach;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

import dagger.hilt.android.HiltAndroidApp;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/3 0003
 */
@HiltAndroidApp
public class App extends BaseApplication {
    private static App instance;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JLog.INSTANCE.setInterceptor(i -> true);
        JLog.INSTANCE.setJsonParser(new JsonParser() {
            @Nullable
            @Override
            public String object2Json(@Nullable Object o) {
                return ARouter.getInstance().navigation(SerializationService.class).object2Json(o);
            }

            @Override
            public <T> T parseObject(@Nullable String s, @Nullable Type type) {
                return ARouter.getInstance().navigation(SerializationService.class).parseObject(s, type);
            }
        });
        initMatrix();
        //installCockroach();
    }



    private void initMatrix() {

        Matrix.Builder builder = new Matrix.Builder(this); // build matrix
        builder.patchListener(new MyPluginListener(this)); // add general pluginListener
        MyDynamicConfig dynamicConfig = new MyDynamicConfig(); // dynamic config

        // init plugin
        IOCanaryPlugin ioCanaryPlugin = new IOCanaryPlugin(new IOConfig.Builder()
                .dynamicConfig(dynamicConfig)
                .build());
        //add to matrix
        builder.plugin(ioCanaryPlugin);

        //init matrix
        Matrix.init(builder.build());

        // start plugin
        ioCanaryPlugin.start();
    }

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

}
