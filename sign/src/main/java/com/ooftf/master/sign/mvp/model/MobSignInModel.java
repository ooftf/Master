package com.ooftf.master.sign.mvp.model;

import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.net.ServiceHolder;
import com.ooftf.service.net.mob.bean.SignInBean;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MobSignInModel implements SignInContract.IModel {
    @Inject
    public MobSignInModel(){

    }
    @Override
    public Observable<SignInBean> signIn(String username, String password) {
        return ServiceHolder
                .INSTANCE
                .getMobService()
                .signIn(username, password)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
