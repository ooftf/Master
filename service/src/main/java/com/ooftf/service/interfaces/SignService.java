package com.ooftf.service.interfaces;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.ooftf.service.bean.SignInfo;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public interface SignService extends IProvider {
    String EVENT_LOGIN_SUCCESS = "event_login_success";
    String EVENT_LOGIN_FAIL = "event_login_fail";

    boolean isSignIn();

    void signOut();

    void updateSignInfo(SignInfo info);

    SignInfo getSignInfo();

   /* void publish(@SignEvent String event);

    Observable<String> getSignObserver();*/
}
