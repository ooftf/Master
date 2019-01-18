package com.ooftf.service.engine.router.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.ooftf.service.engine.router.assist.ISignService;
import com.ooftf.service.engine.router.assist.SignChannelInfo;

import java.util.List;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/19 0019
 */
public interface IMultiSignService extends IProvider {
    String getCurrentAccountId();

    String getCurrentAccountName();

    ISignService getCurrentService();

    List<SignChannelInfo> getAllChannel();

    void switchToChannel(String id);
}
