package com.ooftf.service.other;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/3 0003
 */
public class LogAspectJX {
    @After("execution(* android.util.Log.printlns(..))")
    public void aspectImageLoader(JoinPoint joinPoint) throws Throwable {
        System.err.println("LogAspectJX aspect:::" + joinPoint.getSignature());
    }

    @Pointcut("execution(* android.app.Activity.onCreate(..))")
    public void onCreateCutPoint() {
        Log.e("LogAspectJX","onCreateCutPoint");
    }

    @Pointcut("execution(* android.app.Activity.onResume())")
    public void onResumeCutPoint() {
        Log.e("LogAspectJX","onResumeCutPoint");
    }

    @Pointcut("execution(* android.app.Activity.onPause(..))")
    public void onPauseCutPoint() {
        Log.e("LogAspectJX","onPauseCutPoint");
    }

    @Pointcut("execution(* android.app.Activity.onStart(..))")
    public void onStartCutPoint() {
        Log.e("LogAspectJX","onStartCutPoint");
    }

    @Pointcut("execution(* android.app.Activity.onStop(..))")
    public void onStopCutPoint() {
        Log.e("LogAspectJX","onStopCutPoint");
    }

    @Pointcut("execution(* android.app.Activity.onDestroy(..))")
    public void onDestroyCutPoint() {
        Log.e("LogAspectJX","onDestroyCutPoint");
    }
}
