package com.ooftf.master.sign.provider;

import com.ooftf.master.sign.bean.SignInBean;
import com.ooftf.master.sign.net.SignMobServiceHolder;
import com.ooftf.service.engine.router.assist.ISignService;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.ooftf.service.engine.typer.TyperFactory;
import com.ooftf.service.net.mob.bean.MobBaseBean;

import hugo.weaving.DebugLog;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * 994749769   965661686
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@DebugLog
public class SignServiceImpl implements ISignService {
    private static final String KEY_ACCOUNT_INFO = "AccountInfo";
    private final static SignServiceImpl INSTANCE = new SignServiceImpl();

    private SignServiceImpl() {

    }

    public static SignServiceImpl getInstance() {
        return INSTANCE;
    }

    public static PublishSubject<String> signInSubject = PublishSubject.create();
    public static PublishSubject<String> signOutSubject = PublishSubject.create();


    @Override
    public Single<SignAssistBean> register(String username, String password) {
        return SignMobServiceHolder
                .signMobService
                .register(username, password)
                .singleOrError()
                .map(mobBaseBean -> {
                    SignAssistBean bean = new SignAssistBean();
                    boolean success = MobBaseBean.success.equals(mobBaseBean.getRetCode());
                    bean.setResult(success);
                    if (success) {
                        bean.setMsg("ok");
                    } else {
                        bean.setMsg(mobBaseBean.getMsg());
                    }
                    return bean;
                });
    }

    @Override
    public Single<SignAssistBean> signIn(String username, String password) {
        return SignMobServiceHolder
                .signMobService
                .signIn(username, password)
                .singleOrError()
                .map(mobBaseBean -> {
                    SignAssistBean bean = new SignAssistBean();
                    boolean success = MobBaseBean.success.equals(mobBaseBean.getRetCode());
                    bean.setResult(success);
                    if (success) {
                        TyperFactory.getDefault().put(KEY_ACCOUNT_INFO, mobBaseBean.getResult());
                        bean.setMsg("ok");
                    } else {
                        bean.setMsg(mobBaseBean.getMsg());
                    }
                    return bean;
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
