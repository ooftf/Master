package com.ooftf.service.engine.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Type

private class Jackson : IJson {

    val mapper = ObjectMapper()

    init {
        // to allow (non-standard) unquoted field names in JSON:
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // to prevent exception when encountering unknown property:
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow use of apostrophes (single quotes), non standard
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    override fun toJson(obj: Any?): String? {
        if(obj == null){
            return null
        }
        return mapper.writeValueAsString(obj)
    }

    override fun <T> fromJson(json: String?, clazz: Class<T>): T? {
        if(json == null){
            return null
        }
        return mapper.readValue(json, clazz)
    }

    override fun <T> fromJson(jsonString: String?, type: Type): T? {
        if(jsonString == null){
            return null
        }
        return mapper.readValue(jsonString, mapper.typeFactory.constructType(type))
    }

    override fun <T> fromJsonToArray(jsonString: String?, itemClazz: Class<T>): List<T>? {
        if(jsonString == null){
            return null
        }
        return mapper.readValue(jsonString, mapper.typeFactory.constructArrayType(itemClazz))
    }

}