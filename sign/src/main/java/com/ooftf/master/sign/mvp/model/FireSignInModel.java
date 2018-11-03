package com.ooftf.master.sign.mvp.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ooftf.master.sign.mvp.contract.SignInContract;
import com.ooftf.service.net.ServiceHolder;
import com.ooftf.service.net.mob.bean.SignInBean;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FireSignInModel implements SignInContract.IFireModel {
    @Inject
    public FireSignInModel() {

    }

    @Override
    public Observable<Task<AuthResult>> signIn(String username, String password) {
        return Observable.create(new ObservableOnSubscribe<Task<AuthResult>>() {
            @Override
            public void subscribe(ObservableEmitter<Task<AuthResult>> emitter) throws Exception {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (emitter.isDisposed()) {
                                    return;
                                }
                                emitter.onNext(task);
                            }
                        });
            }
        });
       /* return ServiceHolder
                .INSTANCE
                .getMobService()
                .signIn(username, password)
                .observeOn(AndroidSchedulers.mainThread());*/
    }
}
