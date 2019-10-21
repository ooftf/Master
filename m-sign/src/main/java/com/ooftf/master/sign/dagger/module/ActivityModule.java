package com.ooftf.master.sign.dagger.module;

import com.ooftf.master.sign.ui.register.RegisterContract;
import com.ooftf.master.sign.ui.register.RegisterModel;
import com.ooftf.master.sign.ui.register.RegisterPresenter;
import com.ooftf.master.sign.ui.sign.SignInContract;
import com.ooftf.master.sign.ui.sign.SignInModel;
import com.ooftf.master.sign.ui.sign.SignInPresenter;
import com.ooftf.service.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public SignInContract.IModel provideSignInModel(SignInModel model) {
        return model;
    }

    @Provides
    public SignInContract.IPresenter provideSignInPresenter(SignInPresenter presenter) {
        return presenter;
    }
    @Provides
    public RegisterContract.IModel provideRegisterModel(RegisterModel model) {
        return model;
    }

    @Provides
    public RegisterContract.IPresenter provideRegisterPresenter(RegisterPresenter presenter) {
        return presenter;
    }


}
