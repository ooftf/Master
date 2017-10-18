package tf.oof.com.service.engine

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * Created by master on 2017/10/18 0018.
 */
class ScrollerPlus: Scroller {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, interpolator: Interpolator?) : super(context, interpolator)
    constructor(context: Context?, interpolator: Interpolator?, flywheel: Boolean) : super(context, interpolator, flywheel)
    private val loopTimer :LoopTimer by lazy {
        LoopTimer(period = 1000 / 100.toLong())
    }

    /**
     * computeScrollOffset 每次只能调用一次，如果调用两次会出现，两次结果不一致的情况，因为第一次运算，有可能得出结果已经结束但这次放回结果却是true，但是下一次调用返回结果确实false
     *
     * 可以理解为，computeScrollOffset 返回值，代表 两次computeScrollOffset时得出的结果是否有差值
     */
    fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int, moving:(currX:Int,currY:Int)->Unit, onFinish:()->Unit) {
        super.startScroll(startX, startY, dx, dy, duration)
        loopTimer.start{
            if(computeScrollOffset()){
                moving(currX,currY)
            }else{
                loopTimer.cancel()
                onFinish()
            }
        }
    }
    fun cancel(){
        loopTimer.cancel()
    }
}