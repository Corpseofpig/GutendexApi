package com.example.tz.data.model

data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val translators: List<Translator>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyrighy: Boolean,
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
    val textHtml: String?,
    val epubZip: String?,
    val mobipocket: String?,
    val rdf: String?,
    val jpeg: String?,
    val octet: String?,
)

