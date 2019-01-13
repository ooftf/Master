package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ooftf.master.sign.dagger.component.DaggerFireSignServiceComponent;
import com.ooftf.service.bean.SignInfo;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.service.FireSignService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = ServiceMap.FIRE_SIGN, name = "测试服务")
public class FireSignServiceImpl implements FireSignService {
    @Inject
    FirebaseAuth mAuth;

    @Override
    public void init(Context context) {
        DaggerFireSignServiceComponent.create().inject(this);
    }

    public static PublishSubject<SignInfo> signInSubject = PublishSubject.create();
    public static PublishSubject<SignInfo> signOutSubject = PublishSubject.create();

    @Override
    public boolean isSignIn() {
        if (getSignInfo() == null) {
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
    public void updateSignInfo(FirebaseUser info) {
        mAuth.updateCurrentUser(info);
    }

    @Override
    public FirebaseUser getSignInfo() {
        return mAuth.getCurrentUser();
    }

    @Override
    public void subscribeSignIn(Observer<SignInfo> observer) {
        signInSubject.subscribe(observer);
    }

    @Override
    public void subscribeSignOut(Observer<SignInfo> observer) {
        signOutSubject.subscribe(observer);
    }
}
