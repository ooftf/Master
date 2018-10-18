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
    //static ExecutorService threadPool = Executors.newCachedThreadPool();
    static ThreadPoolExecutor threadPool = createThreadPool();

    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
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
