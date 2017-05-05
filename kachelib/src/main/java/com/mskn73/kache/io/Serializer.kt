package com.mskn73.kache.io

import com.google.gson.Gson

class Serializer internal constructor() {

    private val gson = Gson()

    fun serialize(`object`: Any, clazz: Class<*>): String {
        return gson.toJson(`object`, clazz)
    }

    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }
}
