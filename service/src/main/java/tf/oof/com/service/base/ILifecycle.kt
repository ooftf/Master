package tf.oof.com.service.base

/**
 * Created by master on 2017/10/11 0011.
 */
interface ILifecycle {
    fun isAlive():Boolean
    fun isShowing():Boolean
    fun isTouchable():Boolean
}