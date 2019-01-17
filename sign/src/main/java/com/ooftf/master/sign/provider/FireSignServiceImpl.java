package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.ooftf.service.engine.router.service.SignService;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = ServiceMap.FIRE_SIGN, name = "测试服务")
public class FireSignServiceImpl implements SignService {
    FirebaseAuth mAuth;

    @Override
    public void init(Context context) {
        mAuth = FirebaseAuth.getInstance();
    }

    public static PublishSubject<String> signInSubject = PublishSubject.create();
    public static PublishSubject<String> signOutSubject = PublishSubject.create();

    @Override
    public Single<SignAssistBean> register(String username, String password) {
        return Single.create(emitter -> mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (emitter.isDisposed()) {
                return;
            }
            SignAssistBean result = new SignAssistBean();
            result.setResult(task.isSuccessful());
            if (task.isSuccessful()) {
                result.setMsg("ok");
            } else {
                result.setMsg(task.getException().getMessage());
            }
            emitter.onSuccess(result);
        }));
    }

    @Override
    public Single<SignAssistBean> signIn(String username, String password) {
        return Single.create(emitter -> FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    SignAssistBean result = new SignAssistBean();
                    result.setResult(task.isSuccessful());
                    if (task.isSuccessful()) {
                        result.setMsg("ok");
                    } else {
                        result.setMsg(task.getException().getMessage());
                    }
                    emitter.onSuccess(result);
                }));
    }

    @Override
    public boolean isSignIn() {

        if (mAuth.getCurrentUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void signOut() {
        mAuth.signOut();
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
        return null;
    }
}
