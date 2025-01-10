package com.example.readle.data

data class BookResponse (
    val books: List<Book>
)

data class Book(
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String
)