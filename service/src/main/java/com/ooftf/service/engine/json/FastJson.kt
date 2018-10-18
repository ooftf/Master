package com.ooftf.service.engine.json

import com.alibaba.fastjson.JSON
import java.lang.reflect.Type

private class FastJson : IJson {
    override fun <T> fromJsonToArray(jsonString: String, itemClazz: Class<T>): List<T> {
        val jsonArray = JSON.parseArray(jsonString, itemClazz)
        return jsonArray
    }

    override fun <T> fromJson(jsonString: String, type: Type): T {
        return JSON.parseObject(jsonString, type)
    }

    override fun toJson(obj: Any): String {
        return JSON.toJSONString(obj)
    }

    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        return JSON.parseObject(json, clazz)
    }

}
