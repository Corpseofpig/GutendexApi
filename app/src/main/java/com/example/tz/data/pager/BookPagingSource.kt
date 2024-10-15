package com.example.tz.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tz.data.db.BookDao
import com.example.tz.data.db.BookEntity
import com.example.tz.data.model.Book
import com.example.tz.data.model.BookResponse
import com.example.tz.data.remote.BookApi
import com.example.tz.utils.NetworkUtils

class BookPagingSource(
    private val bookApi: BookApi,
    private val bookDao: BookDao,
    private val networkUtils: NetworkUtils
): PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        val anchorPosition = state.anchorPosition?: return null
        val page = state.closestPageToPosition(anchorPosition)
        return page?.prevKey?.plus(1)?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val page = params.key?: 1
            if (networkUtils.isInternetWorking()) {
                val response = bookApi.getBooks(page)
                val books = response.body()?.results?: emptyList()
                bookDao.insertAll(books.map {it.toBookEntity()})
                LoadResult.Page(
                    data = books,
                    prevKey = if (page == 1 ) null else page -1,
                    nextKey = if (books.isEmpty()) null else page + 1
                )
            } else{
                val allLocalBooks = bookDao.getBooks()
                val bookList = allLocalBooks.map {it.toBook()}
                if (bookList.isEmpty() && networkUtils.isInternetWorking()) {
                  return load(params)
                }
                LoadResult.Page(
                    data =bookList,
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun Book.toBookEntity(): BookEntity{
     return BookEntity(
      id = id,
        title = title,
        authors = authors,
        translators = translators,
        subjects = subjects,
        bookshelves = bookshelves,
        languages = languages,
        copyright = copyright,
        media_type = media_type,
        formats = formats,
        download_count = download_count
    )
}

fun BookEntity.toBook(): Book{
    return Book(
        id = id,
        title = title,
        authors = authors,
        translators = translators,
        subjects = subjects,
        bookshelves = bookshelves,
        languages = languages,
        copyright = copyright,
        media_type = media_type,
        formats = formats,
        download_count = download_count
    )
}