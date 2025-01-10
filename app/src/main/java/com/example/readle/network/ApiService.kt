package com.example.readle.network

import com.example.readle.data.BookResponse
import com.example.readle.data.MotivationQuote
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import com.example.readle.data.KEY_BOOK
import retrofit2.Call


//BOOK API
object BookApiClient {
    private const val BASE_URL_BOOK = "https://all-books-api.p.rapidapi.com/"
    private const val HOST_BOOK = "all-books-api.p.rapidapi.com"

    private val retrofitBook = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL_BOOK)
        .build()

    public interface AllBooksApiService {
        @Headers(
            "x-rapidapi-key: $KEY_BOOK",
            "x-rapidapi-host: $HOST_BOOK"
        )
        @GET("search")
        fun searchBooks(
            @Query("query") query: String
        ): Call<BookResponse>
    }

    object bookApiService {
        val retrofitService: AllBooksApiService by lazy { retrofitBook.create(AllBooksApiService::class.java) }
    }
}


//QUOTE API