package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.service.SignService;

import androidx.annotation.NonNull;
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
    public Single<Boolean> register(String username, String password) {
        return Single.create(emitter -> mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onSuccess(task.isSuccessful());
            }
        }));
    }

    @Override
    public Single<Boolean> signIn(String username, String password) {
        return Single.create(emitter -> FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    emitter.onSuccess(task.isSuccessful());
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
