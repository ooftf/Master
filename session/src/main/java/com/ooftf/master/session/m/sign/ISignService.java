package com.ooftf.master.session.m.sign;

import com.alibaba.android.arouter.facade.template.IProvider;

import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface ISignService {

    /**
     * 是否已经登陆
     *
     * @return
     */
    boolean isSignIn();

    /**
     * 登出
     */
    void signOut();

    /**
     * @param
     * @return
     */
    PublishSubject<String> subscribeSignIn();

    PublishSubject<String> subscribeSignOut();

    String getToken();

    String getUserId();

    String getUserName();
}
