package com.ooftf.service.engine.kv;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/19 0019
 */
public interface IKVManager extends IProvider {
    KV getDefaultKV();

    KV getKV(String name);
}
