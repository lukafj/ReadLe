package com.example.readle.data

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val books: List<BookDesc>
)

@Serializable
data class BookDesc(
    val title: String?,
    val author: String?,
    val description: String?,
    val imageUrl: String?,
    val publisher: String?,
    val amazonLink: String?,
    val ISBN: String?,
    val rank: Int?
)
