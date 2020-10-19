package com.ooftf.service.empty;

import com.ooftf.log.JLog;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public class EmptyObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        JLog.e("onError",e.toString());
    }

    @Override
    public void onComplete() {

    }
}
