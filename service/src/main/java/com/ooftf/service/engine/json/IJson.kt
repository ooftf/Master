package com.ooftf.service.engine.json

import java.lang.reflect.Type

interface IJson {
    fun toJson(obj: Any): String
    fun <T> fromJson(json: String, clazz: Class<T>): T
    fun <T> fromJson(jsonString: String, type: Type): T
    fun <T> fromJsonToArray(jsonString: String, itemClazz: Class<T>): List<T>

}