package com.example.tz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tz.data.model.Book
import com.example.tz.domain.usecase.GetBookRetUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class MainFragmentViewModel(val useCase: GetBookRetUseCase): ViewModel() {
    val books: Flow<PagingData<Book>> = useCase().cachedIn(viewModelScope)
}