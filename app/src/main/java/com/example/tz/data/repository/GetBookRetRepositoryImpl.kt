package com.example.tz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tz.data.model.Book
import com.example.tz.data.pager.BookPagingSource
import com.example.tz.data.remote.BookApi
import com.example.tz.domain.repository.GetBooksRetRepository
import kotlinx.coroutines.flow.Flow

class GetBookRetRepositoryImpl(val api: BookApi ): GetBooksRetRepository {
    override fun getBooks(): Flow<PagingData<Book>> {
        return  Pager(
            config = PagingConfig(
                pageSize = 32,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {BookPagingSource(api) }
        ).flow
    }
}