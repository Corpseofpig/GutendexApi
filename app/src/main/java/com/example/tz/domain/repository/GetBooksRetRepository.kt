package com.example.tz.domain.repository

import androidx.paging.PagingData
import com.example.tz.data.model.Book
import kotlinx.coroutines.flow.Flow

interface GetBooksRetRepository {
    fun getBooks(): Flow<PagingData<Book>>
}


