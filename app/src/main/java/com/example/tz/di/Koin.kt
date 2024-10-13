package com.example.tz.di

import com.example.tz.data.remote.BookApi
import com.example.tz.data.repository.GetBookRetRepositoryImpl
import com.example.tz.domain.repository.GetBooksRetRepository
import com.example.tz.domain.usecase.GetBookRetUseCase
import com.example.tz.presentation.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.factory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single <BookApi> {
    Retrofit.Builder()
        .baseUrl("https://gutendex.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BookApi::class.java)
    }
    single<GetBooksRetRepository> {GetBookRetRepositoryImpl(get())}

    factory {GetBookRetUseCase(get())}

    viewModel {MainFragmentViewModel(get())}

}