package com.ooftf.service.engine.json

class JsonBuilder {
    fun build(): IJsonParser {
        return ImplGson()
    }

    companion object {
        val default by lazy {
            JsonBuilder().build()
        }
    }
}