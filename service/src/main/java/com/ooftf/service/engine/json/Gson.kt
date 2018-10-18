package com.ooftf.service.engine.json

import com.google.gson.Gson
import com.google.gson.JsonArray
import java.lang.reflect.Type

private class Gson : IJson {
    override fun <T> fromJsonToArray(jsonString: String, itemClazz: Class<T>): List<T> {
        val jsonArray = gson.fromJson(jsonString, JsonArray::class.java)
        val arrayList = ArrayList<T>()
        jsonArray.forEach {
            arrayList.add(gson.fromJson(it, itemClazz))
        }
        return arrayList
    }

    override fun <T> fromJson(jsonString: String, type: Type): T {
        return gson.fromJson(jsonString, type)
    }

    private var gson = Gson()

    override fun toJson(obj: Any): String {
        return gson.toJson(obj)
    }

    override fun <T> fromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }
}