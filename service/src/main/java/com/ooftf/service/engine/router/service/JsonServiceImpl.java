package com.ooftf.service.engine.router.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.ooftf.service.engine.json.JsonFactory;
import com.ooftf.service.engine.router.ServiceMap;

import java.lang.reflect.Type;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/5/22 0022
 */
@Route(path = ServiceMap.JSON)
public class JsonServiceImpl implements SerializationService {
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return JsonFactory.getDefault().fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JsonFactory.getDefault().toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JsonFactory.getDefault().fromJson(input, clazz);
    }

    @Override
    public void init(Context context) {

    }
}
