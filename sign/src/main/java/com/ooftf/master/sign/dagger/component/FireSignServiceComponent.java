package com.ooftf.master.sign.dagger.component;

import com.ooftf.master.sign.activity.RegisterActivity;
import com.ooftf.master.sign.dagger.module.FirebaseAuthModule;
import com.ooftf.master.sign.provider.FireSignServiceImpl;
import com.ooftf.master.sign.provider.SignServiceImpl;

import dagger.Component;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/3 0003
 */
@Component(modules = {FirebaseAuthModule.class})
public interface FireSignServiceComponent {
    void inject(FireSignServiceImpl obj);
}
