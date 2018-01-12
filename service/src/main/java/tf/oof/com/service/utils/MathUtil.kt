package tf.oof.com.service.utils

import android.graphics.Point

/**
 * Created by 99474 on 2017/12/14 0014.
 */

object MathUtil {
    //求多次幂
    fun power(base: Double, power: Int): Double {
        var result = 1.0
        for (i in 0 until power) {
            result = result * base
        }
        return result
    }

    //求两点之间的距离
    fun distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return java.lang.Math.sqrt(power(x2 - x1, 2) + power(y2 - y1, 2))
    }

    //求两点之间的距离
    fun distance(p1: Point, p2: Point): Double {
        return java.lang.Math.sqrt(power((p2.x - p1.x).toDouble(), 2) + power((p2.y - p1.y).toDouble(), 2))
    }

}
