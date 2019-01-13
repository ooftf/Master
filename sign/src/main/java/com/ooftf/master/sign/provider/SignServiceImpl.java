package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.bean.SignInfo;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.service.SignService;
import com.ooftf.service.engine.typer.TyperFactory;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = ServiceMap.SIGN, name = "测试服务")
public class SignServiceImpl implements SignService {
    private static final String KEY_ACCOUNT_INFO = "AccountInfo";

    @Override
    public void init(Context context) {

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
        TyperFactory.getDefault().remove(KEY_ACCOUNT_INFO);
    }

    @Override
    public void updateSignInfo(SignInfo info) {
        TyperFactory.getDefault().put(KEY_ACCOUNT_INFO, info);
    }

    @Override
    public SignInfo getSignInfo() {
        return TyperFactory.getDefault().getObject(KEY_ACCOUNT_INFO, SignInfo.class);
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
