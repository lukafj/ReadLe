package com.example.readle.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readle.data.Book
import com.example.readle.fetchBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReadLeViewModel: ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val result = fetchBooks(query)
                _books.value = result
            } catch (e: Exception) {
                // Handle error (e.g., show an error message)
                _books.value = emptyList()
            }
        }
    }
}