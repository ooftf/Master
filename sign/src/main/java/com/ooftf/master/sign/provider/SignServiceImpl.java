package com.ooftf.master.sign.provider;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.sign.bean.SignInBean;
import com.ooftf.master.sign.net.SignMobServiceHolder;
import com.ooftf.service.engine.router.ServiceMap;
import com.ooftf.service.engine.router.service.SignService;
import com.ooftf.service.engine.typer.TyperFactory;
import com.ooftf.service.net.mob.bean.MobBaseBean;

import io.reactivex.Single;
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

    public static PublishSubject<String> signInSubject = PublishSubject.create();
    public static PublishSubject<String> signOutSubject = PublishSubject.create();

    @Override
    public Single<Boolean> register(String username, String password) {
        return SignMobServiceHolder
                .signMobService
                .register(username, password)
                .singleOrError()
                .map(mobBaseBean -> MobBaseBean.success.equals(mobBaseBean.getRetCode()));
    }

    @Override
    public Single<Boolean> signIn(String username, String password) {
        return SignMobServiceHolder
                .signMobService
                .signIn(username, password)
                .singleOrError()
                .map(signInBean -> {
                    boolean success = MobBaseBean.success.equals(signInBean.getRetCode());
                    if (success) {
                        TyperFactory.getDefault().put(KEY_ACCOUNT_INFO, signInBean.getResult());
                    }
                    return success;
                });
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
    }


    public SignInBean.ResultBean getSignInfo() {
        return TyperFactory.getDefault().getObject(KEY_ACCOUNT_INFO, SignInBean.ResultBean.class);
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
            return getSignInfo().getUid();
        }

        return null;
    }
}
