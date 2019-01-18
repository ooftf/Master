package com.ooftf.master.sign.mvp.model;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.ooftf.service.engine.router.service.IMultiSignService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignInModel implements SignInContract.IModel {
    @Autowired
    IMultiSignService multiAccountService;

    @Inject
    public SignInModel() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public Observable<SignAssistBean> signIn(String username, String password) {
        return multiAccountService.getCurrentService().signIn(username, password).toObservable();

    }
}
