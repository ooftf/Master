package com.ooftf.service.engine;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author ooftf
 * @date 2018/8/23 0023
 * @desc
 **/
public class RxScrollerPlus extends Scroller {
    public RxScrollerPlus(Context context) {
        super(context);
    }

    public RxScrollerPlus(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public RxScrollerPlus(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    private int periodMilliSeconds = 10;


    public Observable<Point> startScrollRx(final int startX, final int startY, final int dx, final int dy, final int duration) {
        return Observable.intervalRange(1, duration / periodMilliSeconds + 1, 0, periodMilliSeconds, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Point>() {
                    @Override
                    public Point apply(Long aLong) throws Exception {
                        computeScrollOffset();
                        return new Point(getCurrX(), getCurrY());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        RxScrollerPlus.super.startScroll(startX, startY, dx, dy, duration);
                    }
                });
    }


}
