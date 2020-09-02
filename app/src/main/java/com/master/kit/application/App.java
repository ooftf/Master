package com.master.kit.application;

import android.content.Context;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.log.Interceptor;
import com.ooftf.log.JLog;
import com.ooftf.log.JsonParser;
import com.ooftf.service.base.BaseApplication;
import com.wanjian.cockroach.Cockroach;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

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
        // initMatrix();
        //installCockroach();
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

    @Override
    protected void attachBaseContext(@Nullable Context base) {
        super.attachBaseContext(base);
    }
}
