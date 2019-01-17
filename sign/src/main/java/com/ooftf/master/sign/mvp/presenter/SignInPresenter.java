package com.ooftf.master.sign.mvp.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.service.SignService;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;

import io.reactivex.android.schedulers.AndroidSchedulers;

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
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new ButtonAction<>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(getView().getSinInLoadingButton()))
                .subscribe(new EmptyObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            getView().nextActivity();
                        } else {
                            getView().showDialogMessage("登录失败");
                        }
                    }
                });
    }
}
