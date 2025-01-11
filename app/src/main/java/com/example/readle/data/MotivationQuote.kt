package com.example.readle.data

import kotlinx.serialization.Serializable

//This data class defines a motivation quote.
//It includes an ID, the content of the motivation
//quote as well as information on the author.
@Serializable
data class MotivationQuote(
    val id: Int,
    val quote: String,
    val author: String
)