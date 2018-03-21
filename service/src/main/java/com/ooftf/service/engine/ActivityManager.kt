package com.ooftf.service.engine

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process

/**
 * Created by master on 2016/3/3.
 */
object ActivityManager : ArrayList<Activity>() {
    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                remove(activity)
                if (size == 0) {
                    Process.killProcess(Process.myPid())
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                add(activity)
            }

        })
    }

    fun finishAll() {
        forEach { it.finish() }
    }

    fun finishActivities(cla: Class<*>) {
        filter { it.javaClass == cla }
                .forEach { it.finish() }
    }

    fun finishActivities(cla: Class<*>, resultCode: Int, intent: Intent) {
        filter { it.javaClass == cla }
                .forEach {
                    it.intent = intent
                    it.setResult(resultCode)
                    it.finish()
                }
    }

    fun finishOther(activity: Activity) {
        filter { it != activity }
                .forEach { it.finish() }
    }

    fun finishOther(vararg clas: Class<*>) {
        filter { !clas.contains(it.javaClass) }
                .forEach { it.finish() }
    }
}
