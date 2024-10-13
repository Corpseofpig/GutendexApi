package com.example.tz.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tz.data.model.Book
import com.example.tz.data.model.BookResponse
import com.example.tz.data.remote.BookApi

class BookPagingSource(
    private val bookApi: BookApi
): PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        val anchorPosition = state.anchorPosition?: return null
        val page = state.closestPageToPosition(anchorPosition)
        return page?.prevKey?.plus(1)?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val page = params.key?: 1
            val response = bookApi.getBooks(page)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (page == 1 ) null else page -1,
                nextKey = if (response.body()!!.results.isEmpty()) null else page + 1
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

}