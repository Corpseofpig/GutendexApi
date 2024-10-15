package com.example.tz.data.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.tz.data.model.Author
import com.example.tz.data.model.Formats
import com.example.tz.data.model.Translator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters() {
    val gson = Gson()

    @TypeConverter
    fun fromAuthorList(authors: List<Author>): String {
        return gson.toJson(authors)
    }

    @TypeConverter
    fun toAuthorList(string: String): List<Author> {
        val listType = object: TypeToken<List<Author>>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun fromTranslatorList(translator: List<Translator>): String {
        return gson.toJson(translator)
    }

    @TypeConverter
    fun toTranslatorList(string: String): List<Translator> {
        val listType = object : TypeToken<List<Translator>>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromFormats(formats: Formats): String {
        return gson.toJson(formats)
    }

    @TypeConverter
    fun toFormats(value: String): Formats {
        return gson.fromJson(value, Formats::class.java)
    }
}