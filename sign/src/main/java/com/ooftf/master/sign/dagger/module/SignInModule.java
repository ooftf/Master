package com.ooftf.master.sign.dagger.module;

import com.ooftf.master.sign.activity.SignInActivity;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.master.sign.mvp.model.FireSignInModel;
import com.ooftf.master.sign.mvp.model.MobSignInModel;
import com.ooftf.master.sign.mvp.presenter.FireSignInPresenter;
import com.ooftf.master.sign.mvp.presenter.SignInPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author lihang9
 */
@Module
public class SignInModule {
    SignInActivity mActivity;

    public SignInModule(SignInActivity activity) {
        mActivity = activity;
    }

    @Provides
    public SignInActivity provideSignInActivity() {
        return mActivity;
    }

    @Provides
    public SignInContract.IView provideSignInView() {
        return mActivity;
    }

    @Provides
    public SignInContract.IPresenter provideSignInPresenter(SignInContract.IView view, SignInContract.IFireModel model) {
        return new FireSignInPresenter(view, model);
    }

    @Provides
    public SignInContract.IModel provideSignInModel() {
        return new MobSignInModel();
    }

    @Provides
    public SignInContract.IFireModel provideFireSignInModel() {
        return new FireSignInModel();
    }
}
