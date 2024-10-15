package com.example.tz.data.model

import com.google.gson.annotations.SerializedName

data class Book(
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

data class Author(
    val name: String,
    val birth_year: Int,
    val death_year: Int
)

data class Translator(
    val name: String,
    val birth_year: Int,
    val death_year: Int
)

data class Formats(
    @SerializedName("text/html")
    val textHtml: String?,
    @SerializedName("application/epub+zip")
    val epubZip: String?,
    @SerializedName("application/x-mobipocket-ebook")
    val mobipocket: String?,
    @SerializedName("application/rdf+xml")
    val rdf: String?,
    @SerializedName("image/jpeg")
    val jpeg: String?,
    @SerializedName("application/octet-stream")
    val octet: String?,
)

