package com.ooftf.master.sign.dagger.component;

import com.ooftf.master.sign.ui.register.RegisterActivity;
import com.ooftf.master.sign.ui.sign.SignInActivity;
import com.ooftf.master.sign.dagger.module.ActivityModule;

import dagger.Component;

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(SignInActivity activity);
    void inject(RegisterActivity activity);
}
