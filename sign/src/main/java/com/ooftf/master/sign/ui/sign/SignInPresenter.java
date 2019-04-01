package com.ooftf.master.sign.ui.sign;

import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.BasePresenter;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SignInPresenter extends BasePresenter<SignInContract.IView, SignInContract.IModel> implements SignInContract.IPresenter {

    @Inject
    public SignInPresenter(SignInContract.IModel module) {
        super(module);
    }


    @Override
    public void signIn() {
        getModule()
                .signIn(getView().getChannelId(), getView().getUsername(), getView().getPassword())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new ButtonAction<>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(getView().getSinInLoadingButton()))
                .subscribe(new EmptyObserver<SignAssistBean>() {
                    @Override
                    public void onNext(SignAssistBean bean) {
                        if (bean.isResult()) {
                            getModule().switchToChannel(getView().getChannelId());
                            getView().nextActivity();
                        } else {
                            getView().showDialogMessage(bean.getMsg());
                        }
                    }
                });
    }

}
