package com.ooftf.service.net.etd;

import com.ooftf.service.net.etd.bean.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public abstract class BaseResponse<T extends BaseBean> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (t.getSuccess()) {
            onSuccess(t);
        } else {
            onFail(t);
        }
    }

    /**
     * @param t
     */
    public void onFail(T t) {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    /**
     * @param t
     */
    public abstract void onSuccess(T t);
}
