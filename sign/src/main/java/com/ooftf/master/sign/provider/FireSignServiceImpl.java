package com.ooftf.master.sign.provider;

import com.google.firebase.auth.FirebaseAuth;
import com.ooftf.service.engine.router.assist.ISignService;
import com.ooftf.service.engine.router.assist.SignAssistBean;

import hugo.weaving.DebugLog;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@DebugLog
public class FireSignServiceImpl implements ISignService {
    private final static FireSignServiceImpl INSTANCE = new FireSignServiceImpl();

    private FireSignServiceImpl() {

    }

    public static FireSignServiceImpl getInstance() {
        return INSTANCE;
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


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
        return Single.<SignAssistBean>create(emitter ->
                FirebaseAuth
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
                        }))
                .doOnSuccess(signAssistBean -> signInSubject.onNext(signAssistBean.getMsg()));
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
        return mAuth.getCurrentUser().getUid();
    }

    @Override
    public String getUserName() {
        return mAuth.getCurrentUser().getProviderId();
    }
}
