package com.ooftf.service.engine.json

import android.app.AlertDialog
import android.app.Dialog
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.lang.reflect.Type

class GsonImpl : IJsonParser {
    override fun <T> fromJsonToArray(jsonString: String, itemClazz: Class<T>): List<T> {
        val jsonArray = gson.fromJson(jsonString, JsonArray::class.java)
        var arrayList = ArrayList<T>()
        jsonArray.forEach {
            arrayList.add(gson.fromJson(it,itemClazz))
        }
        return arrayList
    }

    override fun <T> fromJson(jsonString: String, type: Type):T{
        return gson.fromJson(jsonString,type)
    }
    var gson: Gson
    constructor(){
        gson = Gson()
    }
    override fun toJson(obj:Any):String{
        return gson.toJson(obj)
    }
    override fun <T>fromJson(json:String,clazz: Class<T>):T{
       return gson.fromJson(json,clazz)
    }
}