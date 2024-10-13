package com.example.tz.domain.usecase

import androidx.paging.PagingData
import com.example.tz.data.model.Book
import com.example.tz.domain.repository.GetBooksRetRepository
import kotlinx.coroutines.flow.Flow

class GetBookRetUseCase(val repository: GetBooksRetRepository) {
    operator fun invoke(): Flow<PagingData<Book>>{
        return repository.getBooks()
    }
}