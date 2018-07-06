package com.ooftf.service.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AndroidUtil 主要负责硬件层级
 * App主要负责软件层级
 */
public class ThreadUtil {
    static ExecutorService threadPool = Executors.newCachedThreadPool();
    public static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
    public static void runOnUiThread(Runnable runnable){
        if (isMainThread()){
            runnable.run();
        }else{
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
        }
    }
    public static void runOnIOThread(Runnable runnable){
        if(isMainThread()){
            threadPool.execute(runnable);
        }else{
            runnable.run();
        }
    }
}
