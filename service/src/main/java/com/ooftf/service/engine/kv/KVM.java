package com.ooftf.service.engine.kv;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.mmkv.MMKV;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/19 0019
 */
@Route(path = "/service/KVManager", name = "序列化")
public class KVM implements IKVManager {
    KV defaultKV;
    Context context;

    @Override
    public void init(Context context) {
        MMKV.initialize(context);
        defaultKV = new MMKVKV(context);
        this.context = context;
    }

    @Override
    public KV getDefaultKV() {
        return defaultKV;
    }

    @Override
    public KV getKV(String name) {
        return new MMKVKV(context, name);
    }
}
