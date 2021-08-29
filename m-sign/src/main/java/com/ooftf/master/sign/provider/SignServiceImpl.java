package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.sign.bean.SignInBean;
import com.ooftf.master.session.m.sign.ISignService;
import com.ooftf.service.engine.typer.TyperFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.subjects.PublishSubject;

/**
 * 994749769   965661686
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Singleton
public class SignServiceImpl implements ISignService {
    @Inject
    public SignServiceImpl(){

    }
    private static final String KEY_ACCOUNT_INFO = "AccountInfo";


    public static PublishSubject<String> signInSubject = PublishSubject.create();
    public static PublishSubject<String> signOutSubject = PublishSubject.create();


    public void signIn(SignInBean.DataBean bean) {
        TyperFactory.getDefault().put(KEY_ACCOUNT_INFO, bean);
        signInSubject.onNext("");
    }

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
        TyperFactory.getDefault().remove(KEY_ACCOUNT_INFO);
        signInSubject.onNext("");
    }


    public SignInBean.DataBean getSignInfo() {
        return TyperFactory.getDefault().getObject(KEY_ACCOUNT_INFO, SignInBean.DataBean.class);
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
        if (getSignInfo() != null) {
            return getSignInfo().getToken();
        }

        return null;
    }

    @Override
    public String getUserId() {
        if (getSignInfo() != null) {
            return getSignInfo().getId();
        }

        return null;
    }

    @Override
    public String getUserName() {
        if (getSignInfo() != null) {
            return getSignInfo().getUsername();
        }
        return null;
    }
}
