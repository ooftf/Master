package tf.oof.com.service.engine

import android.app.Activity
import android.content.Intent
import java.util.*

/**
 * Created by master on 2016/3/3.
 */
class ActivityManager private constructor() : HashSet<Activity>() {

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
        filter { it!=activity }
                .forEach { it.finish() }
    }

    fun finishOther(vararg clas: Class<*>) {
        filter { !clas.contains(it.javaClass) }
                .forEach { it.finish() }
    }
    companion object {
        val instance: ActivityManager by lazy {
            ActivityManager()
        }
    }
}
