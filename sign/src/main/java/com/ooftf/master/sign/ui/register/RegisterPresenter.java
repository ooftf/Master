package com.ooftf.master.sign.ui.register;

import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.BasePresenter;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterContract.IView, RegisterContract.IModel> implements RegisterContract.IPresenter {
    @Inject
    public RegisterPresenter(RegisterContract.IModel module) {
        super(module);
    }

    @Override
    public void register() {
        getModule()
                .register(getView().getChannelId(), getView().getUsername(), getView().getPassword())
                .compose(RxLifecycleAndroid.bindView(getView().getRegisterLoadingButton()))
                .compose(new ButtonAction<>(getView().getRegisterLoadingButton(), "正在注册..."))
                .subscribe(new EmptyObserver<SignAssistBean>() {
                    @Override
                    public void onNext(SignAssistBean bean) {
                        if (bean.isResult()) {
                            getView().showSuccessDialog(getModule().getChannelName(getView().getChannelId()) + "注册成功");
                        } else {
                            getView().toast(bean.getMsg());
                        }
                    }
                });
    }
}
