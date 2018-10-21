package com.ooftf.service.net.mob.action;

import com.ooftf.service.empty.EmptyObserver;
import com.ooftf.service.net.mob.bean.MobBaseBean;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class MobObserver<T extends MobBaseBean> extends EmptyObserver<T> {
    @Override
    public final void onNext(T t) {
        if (MobBaseBean.success.equals(t.getRetCode())) {
            onSuccess(t);
        } else {
            onFail(t);
        }
    }

    public void onSuccess(T bean) {

    }

    public void onFail(T bean) {

    }

}
