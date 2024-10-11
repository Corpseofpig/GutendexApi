package com.example.tz.domain.repository

import kotlinx.coroutines.flow.Flow

interface GetBooksRetRepository {
    fun getBooks(page: Int): Flow
}


