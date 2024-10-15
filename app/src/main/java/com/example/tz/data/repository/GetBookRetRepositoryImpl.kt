package com.example.tz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tz.data.db.BookDao
import com.example.tz.data.model.Book
import com.example.tz.data.pager.BookPagingSource
import com.example.tz.data.remote.BookApi
import com.example.tz.domain.repository.GetBooksRetRepository
import com.example.tz.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow

class GetBookRetRepositoryImpl( private val api: BookApi,
                               private val bookDao: BookDao,
                               private val networkUtils: NetworkUtils
): GetBooksRetRepository {
    override fun getBooks(): Flow<PagingData<Book>> {
        return  Pager(
            config = PagingConfig(
                pageSize = 32,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {BookPagingSource(api, bookDao, networkUtils) }
        ).flow
    }
}