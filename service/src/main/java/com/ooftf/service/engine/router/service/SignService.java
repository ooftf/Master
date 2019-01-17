package com.ooftf.service.engine.router.service;

import com.alibaba.android.arouter.facade.template.IProvider;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface SignService extends IProvider {
    Single<Boolean> register(String username, String password);

    Single<Boolean> signIn(String username, String password);

    boolean isSignIn();

    void signOut();

    /**
     * @param
     * @return
     */
    PublishSubject<String> subscribeSignIn();

    PublishSubject<String> subscribeSignOut();

    String getToken();

    String getUserId();
}
