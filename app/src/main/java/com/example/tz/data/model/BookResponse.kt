package com.example.tz.data.model

data class BookResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Book>
)

