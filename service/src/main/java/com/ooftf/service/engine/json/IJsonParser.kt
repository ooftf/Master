package com.ooftf.service.engine.json

import java.lang.reflect.Type

interface IJsonParser  {
    fun toJson(obj:Any): String
    fun <T>fromJson(json:String,clazz: Class<T>):T
    fun <T>fromJson(jsonString:String, type:Type):T
    fun <T>fromJsonToArray(jsonString: String,itemClazz:Class<T>):List<T>
    class Builder(){
        fun build():IJsonParser{
            return GsonImpl()
        }
    }
}