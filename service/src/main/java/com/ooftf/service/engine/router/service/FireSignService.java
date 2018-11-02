package com.ooftf.service.engine.router.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.google.firebase.auth.FirebaseUser;
import com.ooftf.service.bean.SignInfo;

import io.reactivex.Observer;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface FireSignService extends IProvider {


    boolean isSignIn();

    void signOut();

    void updateSignInfo(FirebaseUser info);

    FirebaseUser getSignInfo();

    void subscribeSignIn(Observer<SignInfo> observer);
    void subscribeSignOut(Observer<SignInfo> observer);
}
