package com.master.kit.testcase.retrofit;

import android.content.Context;

import com.dks.master.masterretrofit.BaseBean;
import com.dks.master.masterretrofit.Request;

import java.lang.ref.WeakReference;

/**
 * Created by master on 2017/3/3.
 */

public abstract class BaseHandlerRequest<E extends BaseBean> extends Request<E> {
    protected WeakReference<Context> contextWeakReference;
    public BaseHandlerRequest(Context context){
        super();
        this.contextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected void onResponseFailureSessionExpired(E e) {

    }

    @Override
    protected void onResponseFailureOtherSignIn(E e) {

    }

    Context getContext(){
        return contextWeakReference.get();
    }
}
