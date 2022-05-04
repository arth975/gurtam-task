package com.gurtam.task.data.util

import com.google.gson.Gson
import java.io.InputStreamReader

object ResourceReader {
    fun <T> readJsonObject(path: String, clazz: Class<T>): T {
        val contentString = readJsonString(path)
        return Gson().fromJson(contentString, clazz)
    }

    fun readJsonString(path: String): String {
        val reader = InputStreamReader(javaClass.classLoader!!.getResourceAsStream(path))
        val contentString = reader.readText()
        reader.close()
        return contentString
    }
}