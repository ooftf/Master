package com.ooftf.master.sign.dagger.component;

import com.ooftf.master.sign.activity.SignInActivity;
import com.ooftf.master.sign.dagger.module.SignInModule;

import dagger.Component;

@Component(modules = {SignInModule.class})
public interface SigInComponent {
    void inject(SignInActivity obj);
}
