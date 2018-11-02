package com.ooftf.master.sign.dagger.module;

import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/3 0003
 */
@Module
public class FirebaseAuthModule {
    @Provides
    public FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

}
