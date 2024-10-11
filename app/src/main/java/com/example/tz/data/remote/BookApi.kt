package com.example.tz.data.remote

import com.example.tz.data.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("books/")
     suspend fun getBooks(@Query("page") page: Int): Response<BookResponse>
}