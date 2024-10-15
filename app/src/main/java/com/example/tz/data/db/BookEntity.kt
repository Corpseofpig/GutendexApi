package com.example.tz.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.tz.data.model.Author
import com.example.tz.data.model.Formats
import com.example.tz.data.model.Translator

@Entity(tableName = "books")
@TypeConverters(Converters::class)

 data class BookEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val translators: List<Translator>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    val media_type: String,
    val formats: Formats,
    val download_count: Int
)
