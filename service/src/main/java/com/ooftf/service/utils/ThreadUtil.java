package com.ooftf.service.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * AndroidUtil 主要负责硬件层级
 * App主要负责软件层级
 */
public class ThreadUtil {
    private static ThreadPoolExecutor threadPool = createThreadPool();
    private static Handler mainHandler = new Handler(Looper.getMainLooper());

    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            mainHandler.post(runnable);
        }
    }

    public static void runOnIOThread(Runnable runnable) {
        if (isMainThread()) {
            threadPool.execute(runnable);
        } else {
            runnable.run();
        }
    }

    static ThreadPoolExecutor createThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            /**
             * 线程编号
             */
            int id = 0;

            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "ooftf" + id++);
            }
        });
    }
}
