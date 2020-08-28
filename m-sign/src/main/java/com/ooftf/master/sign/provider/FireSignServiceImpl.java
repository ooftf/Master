package com.ooftf.master.sign.provider;

import com.ooftf.service.engine.router.assist.ISignService;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */

public class FireSignServiceImpl implements ISignService {
    private final static FireSignServiceImpl INSTANCE = new FireSignServiceImpl();

    private FireSignServiceImpl() {

    }

    public static FireSignServiceImpl getInstance() {
        return INSTANCE;
    }

    public static PublishSubject<String> signInSubject = PublishSubject.create();
    public static PublishSubject<String> signOutSubject = PublishSubject.create();


    @Override
    public Single<SignAssistBean> register(String username, String password) {
        return null;
    }

    @Override
    public Single<SignAssistBean> signIn(String username, String password) {
        return null;
    }

    @Override
    public boolean isSignIn() {
        return false;
    }

    @Override
    public void signOut() {
        //mAuth.signOut();
        signInSubject.onNext("");
    }


    @Override
    public PublishSubject<String> subscribeSignIn() {
        return signInSubject;
    }

    @Override
    public PublishSubject<String> subscribeSignOut() {
        return signOutSubject;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getUserId() {
        return  "";
    }

    @Override
    public String getUserName() {
        return  "";
    }
}
