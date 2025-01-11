package com.example.readle.ui

import ReadLeRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readle.data.Book
import com.example.readle.data.BookDesc
import com.example.readle.data.User
import com.example.readle.network.BookApiClient
import com.example.readle.network.QuoteApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException



class ReadLeViewModel(private val repository: ReadLeRepository): ViewModel() {

    private val _books = MutableStateFlow<List<BookDesc>>(emptyList())
    val books: StateFlow<List<BookDesc>> = _books.asStateFlow()

    private val _uiState = MutableStateFlow(ReadLeUIState())
    val uiState: StateFlow<ReadLeUIState> = _uiState.asStateFlow()

    init {
        setReadLe()
    }

    fun setReadLe() {
        viewModelScope.launch {
            _uiState.value = ReadLeUIState()
            repository.insertUser(User(0, "admin", "admin", "lj15233@student.uni-lj.si"))
            repository.insertBook(Book(
                isbn = "9780044403371",
                title = "The Hobbit",
                author_id = 1,
                description = "Join Bilbo as he embarks upon a journey to take back a kingdom, and a very important jewel, with twelve dwarves and a wizard named Gandalf the Grey.",
                imageUrl = "https://m.media-amazon.com/images/I/41iWAOOaw4L._SY445_SX342_.jpg",
                publisher = "John Ronald Reuel Tolkien",
                amazonLink = "https://www.amazon.de/-/en/Hobbit-oder-zur%C3%BCck-Illustrationen-Alan/dp/3608938001?crid=TPUUP9RP80FL&dib=eyJ2IjoiMSJ9.zfcZtNi0k9V5TaT-s6XujR9RpbZ0MzysixVtBqD9eiRV4UtiNbnPz638pNlsM8zc-tMWoyDYElIJT8e6CQPy2vMUeTewZ4d-RiXMrFSQNx-x8EgM3_cF2TnuTtYHmGwvgFSE3CFBKMHLBFaQW8mxSXwF7NHj9pttTYh-qXeRbaM7u8-U7EsrFc4InDGTrXYxhJ7OxhOp6lLTVmxY0XSqy3osu6MP-MIx_0xTHH1Sxy8.xAxv-Z811mmEBXe9k6sFR-qp7xIli-Qixq-0OPnQ-GU&dib_tag=se&keywords=The+hobbit+hardcover&nsdOptOutParam=true&qid=1736628593&sprefix=the+hobbit+hardcove%2Caps%2C164&sr=8-1",
                rank = 1,
            ))
            repository.insertBook(Book(
                isbn = "9780747532743",
                title = "Harry Potter and the Philosopher's Stone",
                author_id = 2,
                description = "Harry Potter discovers he's a wizard on his 11th birthday and begins his journey at Hogwarts School of Witchcraft and Wizardry, where he uncovers the truth about his parents' mysterious deaths.",
                imageUrl = "https://res.cloudinary.com/bloomsbury-atlas/image/upload/w_568,c_scale,dpr_1.5/jackets/9781408855652.jpg",
                publisher = "Bloomsbury Publishing",
                amazonLink = "https://www.amazon.com/Harry-Potter-Philosophers-Stone-Rowling/dp/0747532745",
                rank = 2,
            ))
        }
    }

    fun addUser(
        user_id: Int,
        username: String,
        password: String,
        email: String
    ) {
        viewModelScope.launch {
            repository.insertUser(User(
                user_id = user_id,
                username = username,
                password = password,
                email = email,
            ))
            Log.d("RegisterScreen", "New user added")
        }
    }

    fun authenticateUser(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = repository.authenticateUser(username, password)
            onResult(user != null) // Return true if user exists, false otherwise
        }
    }



    fun searchBooks(query: String) {
        viewModelScope.launch {
            // Use Retrofit to fetch the books
            BookApiClient.bookApiService.searchBooks(query).enqueue(object : Callback<List<BookDesc>> {
                override fun onResponse(
                    call: Call<List<BookDesc>>,
                    response: Response<List<BookDesc>>
                ) {
                    if (response.isSuccessful) {
                        Log.i("DEBUG", "Books Retrieved")

                        _books.value = response.body() ?: emptyList()
                    } else {
                        Log.e("API", "Error retrieving books")
                        _books.value = emptyList()
                    }
                }

                override fun onFailure(call: Call<List<BookDesc>>, t: Throwable) {
                    Log.e("API", "Error failed to retrieve books")
                    _books.value = emptyList()
                }
            })
        }
    }


    fun getQuote() {
        viewModelScope.launch {
            try {
                val newQuote = QuoteApiClient.quoteApiService.getQuote()
                Log.i("DEBUG", "Quote Retrieved: ${newQuote.quote}")
                _uiState.update {
                        currentState -> currentState.copy(quote = newQuote)
                }
                Log.i("API", "Quote retrieved successfully + $newQuote")
            } catch (e: IOException) {
                Log.e("API", "Error retrieving the quote - IO Exception: " + e.message)
            } catch (e: HttpException) {
                Log.e("API", "Error retrieving the quote - HTTP Exception: " + e.message)
            }
        }
    }

}