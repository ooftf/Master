package com.ooftf.service.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AndroidUtil 主要负责硬件层级
 * App主要负责软件层级
 */
public class ThreadUtil {
    public static final String TAG = "ThreadUtil";
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int INIT_THREAD_COUNT = CPU_COUNT + 1;
    private static final int MAX_THREAD_COUNT = INIT_THREAD_COUNT;
    private static final long SURPLUS_THREAD_LIFE = 30L;
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

    public static void runOnNewThread(Runnable runnable) {
        if (isMainThread()) {
            threadPool.execute(runnable);
        } else {
            runnable.run();
        }
    }

    static ThreadPoolExecutor createThreadPool() {
        return new ThreadPoolExecutor(
                INIT_THREAD_COUNT,
                MAX_THREAD_COUNT,
                SURPLUS_THREAD_LIFE,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(64),
                new DefaultThreadFactory());
    }

    public static class DefaultThreadFactory implements ThreadFactory {
        private final AtomicInteger poolNumber = new AtomicInteger(1);

        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;

        public DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "ThreadPool No." + poolNumber.getAndIncrement() + ", thread No.";
        }

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            String threadName = namePrefix + threadNumber.getAndIncrement();
            Log.e(TAG, "Thread production, name is [" + threadName + "]");
            Thread thread = new Thread(group, runnable, threadName, 0);
            //设为非后台线程
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            //优先级为normal
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }

            // 捕获多线程处理中的异常
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    Log.e(TAG, "Running task appeared exception! Thread [" + thread.getName() + "], because [" + ex.getMessage() + "]");
                }
            });
            return thread;
        }
    }
}
