package tf.oof.com.service.utils;

import android.graphics.Point;

/**
 * Created by 99474 on 2017/12/14 0014.
 */

public class Math {
    //求多次幂
    public static double power(double base, int power) {
        double result = 1;
        for (int i = 0; i < power; i++) {
            result = result*base;
        }
        return result;
    }
    //求两点之间的距离
    public static double distance(double x1,double y1,double x2,double y2){
        return java.lang.Math.sqrt(power(x2-x1,2)+power(y2-y1,2));
    }
    //求两点之间的距离
    public static double distance(Point p1,Point p2){
        return java.lang.Math.sqrt(power(p2.x-p1.x,2)+power(p2.y-p1.y,2));
    }

}
