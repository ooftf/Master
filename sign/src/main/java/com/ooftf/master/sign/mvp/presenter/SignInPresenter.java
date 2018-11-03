package com.ooftf.master.sign.mvp.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.bean.SignInfo;
import com.ooftf.service.engine.router.service.SignService;
import com.ooftf.service.net.mob.action.MobObserver;
import com.ooftf.service.net.mob.bean.SignInBean;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

public class SignInPresenter extends BasePresenter<SignInContract.IView, SignInContract.IModel> implements SignInContract.IPresenter {
    @Autowired
    SignService signService;

    public SignInPresenter(SignInContract.IView attachView, SignInContract.IModel module) {
        super(attachView, module);
        ARouter.getInstance().inject(this);
    }


    @Override
    public void signIn() {
        getModule()
                .signIn(getView().getUsername(), getView().getPassword())
                .compose(new ButtonAction<>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(getView().getSinInLoadingButton()))
                .subscribe(new MobObserver<SignInBean>() {
                    @Override
                    public void onSuccess(SignInBean bean) {
                        SignInfo info = new SignInfo();
                        info.setUid(bean.getResult().getUid());
                        info.setToken(bean.getResult().getToken());
                        signService.updateSignInfo(info);
                        getView().nextActivity();
                    }
                });
    }
}
