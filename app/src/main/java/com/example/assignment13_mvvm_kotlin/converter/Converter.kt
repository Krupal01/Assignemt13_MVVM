package com.example.assignment13_mvvm_kotlin.converter

import androidx.room.TypeConverter
import com.example.assignment13_mvvm_kotlin.model.HighlightResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {

    @TypeConverter
    fun fromObject(obj: Any?): String {
        val gson = Gson()
        return gson.toJson(obj)
    }

    @TypeConverter
    fun toObject(obj: String?): Any? {
        return Gson().fromJson(obj, Any::class.java)
    }

    @TypeConverter
    fun fromHighlightResult(result: HighlightResult?): String? {
        return Gson().toJson(result)
    }

    @TypeConverter
    fun toHighlightResult(obj: String?): HighlightResult? {
        return Gson().fromJson(obj, HighlightResult::class.java)
    }

    @TypeConverter
    fun toList(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}