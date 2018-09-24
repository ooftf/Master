package com.ooftf.service.net.etd;

import com.ooftf.service.net.etd.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public abstract class SuccessResponse<T extends BaseBean> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (t.getSuccess()) {
            onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
}
