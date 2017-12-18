package com.master.kit

import com.alibaba.fastjson.JSON
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import org.junit.Test

/**
 * Created by 99474 on 2017/12/7 0007.
 */
class JsonTest {

    @Test
    fun testGson() {
        var json = "{a:\"aaaaa\",b:\"bbbbbbbb\",c:\"ccccc\",d:\"dddddddddd\"}"
        var builder = GsonBuilder()
        builder.setLenient()
        builder.addDeserializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes): Boolean {
                println(f.name + "----" + f.declaredClass + "")
                return false
            }

        })
        /*  builder.registerTypeAdapterFactory(object :TypeAdapterFactory{
              override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T> {

                  return Gson().getAdapter(type)
              }

          })*/
        /*builder.registerTypeAdapter(String::class.java, JsonDeserializer<String> { json, typeOfT, context ->
            if(json.isJsonObject){
                null
            }else{
                json.asString
            }
        })*/
        var bean = builder.create().fromJson(json, Bean::class.java)
    }

    @Test
    fun testFastJson() {
        val json = "{a:\"aaaaa\",b:\"bbbbbbbb\",c:\"ccccc\",d:\"dddddddddd\"}"
        val s: Bean? = null
        try {
            var s = JSON.parseObject(json, Bean::class.java)
        } catch (excp: Exception) {

        }
        s?.let {
            println(JSON.toJSON(it))
        }
    }
}