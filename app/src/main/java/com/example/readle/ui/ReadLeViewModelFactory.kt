package com.example.readle.ui

import ReadLeRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


//Provides Factory to create instance of ViewModel for the entire app
class ReadLeViewModelFactory(private val repository: ReadLeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadLeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReadLeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}