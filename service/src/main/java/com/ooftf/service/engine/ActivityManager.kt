package com.ooftf.service.engine

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process
import java.lang.ref.WeakReference

/**
 * Created by master on 2016/3/3.
 *
 * 只适用于单进程Activity
 */
object ActivityManager {
    private val activities = ArrayList<Activity>()
    private var touchCounter = 0
    private var showCounter = 0
    private var top: WeakReference<Activity>? = null

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {
                if (top?.get() == activity) {
                    top = null
                }
                touchCounter--

            }

            override fun onActivityResumed(activity: Activity) {
                top = WeakReference(activity)
                touchCounter++

            }

            override fun onActivityStarted(activity: Activity?) {
                if (showCounter == 0) {
                    foregroundObservers.forEach { it.invoke() }
                }
                showCounter++

            }

            override fun onActivityDestroyed(activity: Activity) {
                activities.remove(activity)
                if (activities.size == 0) {
                    Process.killProcess(Process.myPid())
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {
                showCounter--
                if (showCounter == 0) {
                    backgroundObservers.forEach { it.invoke() }
                }
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activities.add(activity)
            }

        })
    }

    fun getTopActivity(): Activity? = top?.get()

    private var foregroundObservers = LinkedHashSet<() -> Unit>()

    private var backgroundObservers = LinkedHashSet<() -> Unit>()


    fun registerForegroundObserver(observer: () -> Unit) {
        foregroundObservers.add(observer)
    }

    fun clearForegroundObserver() {
        foregroundObservers.clear()
    }

    fun unRegisterForegroundObserver(observer: () -> Unit) {
        foregroundObservers.remove(observer)
    }

    fun registerBackgroundObserver(observer: () -> Unit) {
        backgroundObservers.add(observer)
    }

    fun clearBackgroundObserver() {
        backgroundObservers.clear()
    }

    fun unRegisterBackgroundObserver(observer: () -> Unit) {
        backgroundObservers.remove(observer)
    }

    fun isAppForeground() = touchCounter > 0
    fun finishAll() {
        activities.forEach { it.finish() }
    }

    fun finishActivities(cla: Class<*>) {
        activities.filter { it.javaClass == cla }
                .forEach { it.finish() }
    }

    fun finishActivities(cla: Class<*>, resultCode: Int, intent: Intent) {
        activities.filter { it.javaClass == cla }
                .forEach {
                    it.intent = intent
                    it.setResult(resultCode)
                    it.finish()
                }
    }

    fun finishOther(activity: Activity) {
        activities.filter { it != activity }
                .forEach { it.finish() }
    }

    fun finishOther(vararg cls: Class<*>) {
        activities.filter { !cls.contains(it.javaClass) }
                .forEach { it.finish() }
    }
}
