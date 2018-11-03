package com.ooftf.master.sign.mvp.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ooftf.hihttp.action.ButtonAction;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.engine.router.service.SignService;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

public class FireSignInPresenter extends BasePresenter<SignInContract.IView, SignInContract.IFireModel> implements SignInContract.IPresenter {
    @Autowired
    SignService signService;

    public FireSignInPresenter(SignInContract.IView attachView, SignInContract.IFireModel module) {
        super(attachView, module);
        ARouter.getInstance().inject(this);
    }


    @Override
    public void signIn() {
        getModule()
                .signIn(getView().getUsername(), getView().getPassword())
                .compose(new ButtonAction<>(getView().getSinInLoadingButton(), "正在登录..."))
                .compose(RxLifecycleAndroid.bindView(getView().getSinInLoadingButton()))
                .subscribe(new EmptyObserver<Task<AuthResult>>() {
                    @Override
                    public void onNext(Task<AuthResult> authResultTask) {
                        if (authResultTask.isSuccessful()){
                            getView().nextActivity();
                        }else{
                            getView().showDialogMessage("登录失败");
                        }
                    }
                });
    }
}
