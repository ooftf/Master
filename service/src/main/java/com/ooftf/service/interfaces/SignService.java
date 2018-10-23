package com.ooftf.service.interfaces;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.ooftf.service.bean.SignInfo;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface SignService extends IProvider {


    boolean isSignIn();

    void signOut();

    void updateSignInfo(SignInfo info);

    SignInfo getSignInfo();

    void subscribeSignIn(Observer<SignInfo> observer);
    void subscribeSignOut(Observer<SignInfo> observer);
}
