package com.ooftf.master.sign.mvp.presenter;

import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class SignInPresenter extends BasePresenter<SignInContract.IView, SignInContract.IModel> implements SignInContract.IPresenter {

    public SignInPresenter(SignInContract.IView attachView, SignInContract.IModel module) {
        super(attachView, module);
    }


    @Override
    public void signIn() {
        getModule()
                .signIn(getView().getUsername(), getView().getPassword())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new ButtonAction<>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(getView().getSinInLoadingButton()))
                .subscribe(new EmptyObserver<SignAssistBean>() {
                    @Override
                    public void onNext(SignAssistBean bean) {
                        if (bean.isResult()) {
                            getView().nextActivity();
                        } else {
                            getView().showDialogMessage(bean.getMsg());
                        }
                    }
                });
    }
}
