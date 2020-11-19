package com.ooftf.service.utils;

import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.ooftf.basic.utils.ThreadUtil;

import io.reactivex.functions.Function;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/27 0027
 */
public class LifeFunction<T, R> implements Function<T, R> {
    private Function<T, R> reference;

    public LifeFunction(Function<T,R> real, LifecycleOwner owner) {
        reference = real;
        ThreadUtil.runOnUiThread(() -> owner.getLifecycle().addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            void onDestroy() {
                reference = null;
                owner.getLifecycle().removeObserver(this);
            }
        }));

    }

    public LifeFunction(Function<T,R> real, View owner) {
        reference = real;
        owner.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                reference = null;
                owner.removeOnAttachStateChangeListener(this);
            }
        });
    }

    @Override
    public R apply(T t) throws Exception {
        if (reference == null) {
            return null;
        }
        return reference.apply(t);
    }
}
