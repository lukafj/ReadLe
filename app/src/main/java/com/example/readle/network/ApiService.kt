package com.example.readle.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.readle.data.KEY_BOOK
import retrofit2.http.Headers
import android.util.Log
import com.example.readle.data.BookDesc
import com.example.readle.data.KEY_QUOTE
import com.example.readle.data.MotivationQuote
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


// BOOK API
object BookApiClient {

    private const val BOOK_URL = "https://all-books-api.p.rapidapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BOOK_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // API interface
    interface BookApiService {
        @GET("getBooks")
        @Headers(
            "x-rapidapi-key: $KEY_BOOK",
            "x-rapidapi-host: all-books-api.p.rapidapi.com"
        )
        fun searchBooks(
            @Query("query") query: String
        ): Call<List<BookDesc>>
    }

    // Service to make API calls
    val bookApiService: BookApiService = retrofit.create(BookApiService::class.java)
}




// QUOTE API
object QuoteApiClient {

    private const val QUOTE_URL = "https://motivation-quotes4.p.rapidapi.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(QUOTE_URL)
        .build()
    // API interface
    interface QuoteApiService {
        @Headers(
            "x-rapidapi-key: $KEY_QUOTE",
            "x-rapidapi-host: motivation-quotes4.p.rapidapi.com"
        )
        @GET("api")
        suspend fun getQuote(): MotivationQuote
    }

    // Service to make API calls
    val quoteApiService: QuoteApiService = retrofit.create(QuoteApiService::class.java)
}










