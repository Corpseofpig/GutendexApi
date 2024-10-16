package com.example.tz.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tz.data.model.Book
import com.example.tz.domain.usecase.GetBookRetUseCase
import com.example.tz.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    val useCase: GetBookRetUseCase,
    val networkUtils: NetworkUtils
): ViewModel() {
    val books: Flow<PagingData<Book>> = useCase().cachedIn(viewModelScope)
    private val _books = MutableStateFlow<PagingData<Book>>(PagingData.empty())

   private val _networkState = MutableStateFlow(networkUtils.isInternetWorking())
    val networkState = _networkState.asStateFlow()

    val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book> = _selectedBook

    fun loadBooks() {
        viewModelScope.launch {
            useCase().cachedIn(viewModelScope).collect{
                _books.value = it
            }
        }
    }

    fun selectBook(book: Book) {
        _selectedBook.value = book
    }

    fun observeNetworkState() {
        viewModelScope.launch {
            networkUtils.observeNetworkState().collect{ isAvailable ->
                _networkState.value = isAvailable
                if (isAvailable) {
                    loadBooks()
                }
            }
        }
    }

    init {
        observeNetworkState()
        loadBooks()
    }

}