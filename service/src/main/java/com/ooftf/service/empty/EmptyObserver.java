package com.ooftf.service.empty;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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

    }

    @Override
    public void onComplete() {

    }
}
