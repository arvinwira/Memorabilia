package com.example.memorabilia.data

import androidx.lifecycle.*
import com.example.memorabilia.database.Book
import kotlinx.coroutines.launch

class BookViewModel(private val repository: Repository,    private val userPreference: UserPreference
) : ViewModel() {

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> get() = _book


}
