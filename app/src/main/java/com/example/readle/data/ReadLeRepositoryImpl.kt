package com.example.readle.data

import ReadLeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReadLeRepositoryImpl(
    private val userDao: UserDao,
    private val favoriteDao : FavoriteDao,
    private val bookDao: BookDao,
    private val authorDao: AuthorDao
) : ReadLeRepository {
    override suspend fun insertBook(book: Book) {
        bookDao.insertBook(book = book)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book = book)
    }

    override suspend fun getBook(isbn: String): Flow<Book> {
        // Query the database for the book, wrapped in a Flow
        val book = bookDao.getBookByIsbn(isbn)

        return flow {

            if (book != null) {
                emit(book)
            } else {
                emit(
                    Book(
                        isbn = "9780044403371",
                        title = "The Hobbit",
                        author_id = 1,
                        description = "Join Bilbo as he embarks upon a journey to take back a kingdom, and a very important jewel, with twelve dwarves and a wizard named Gandalf the Grey.",
                        imageUrl = "https://m.media-amazon.com/images/I/41iWAOOaw4L._SY445_SX342_.jpg",
                        publisher = "John Ronald Reuel Tolkien",
                        amazonLink = "https://www.amazon.de/-/en/Hobbit-oder-zur%C3%BCck-Illustrationen-Alan/dp/3608938001?crid=TPUUP9RP80FL&dib=eyJ2IjoiMSJ9.zfcZtNi0k9V5TaT-s6XujR9RpbZ0MzysixVtBqD9eiRV4UtiNbnPz638pNlsM8zc-tMWoyDYElIJT8e6CQPy2vMUeTewZ4d-RiXMrFSQNx-x8EgM3_cF2TnuTtYHmGwvgFSE3CFBKMHLBFaQW8mxSXwF7NHj9pttTYh-qXeRbaM7u8-U7EsrFc4InDGTrXYxhJ7OxhOp6lLTVmxY0XSqy3osu6MP-MIx_0xTHH1Sxy8.xAxv-Z811mmEBXe9k6sFR-qp7xIli-Qixq-0OPnQ-GU&dib_tag=se&keywords=The+hobbit+hardcover&nsdOptOutParam=true&qid=1736628593&sprefix=the+hobbit+hardcove%2Caps%2C164&sr=8-1",
                        rank = 1
                    )
                )
            }
        }
    }


    override suspend fun insertUser(user: User) {
        userDao.insertUser(user = user)
    }

    override suspend fun deleteUser(userId: Int) {
        userDao.deleteUser(userId = userId)
    }

    override suspend fun getUser(userId: Int) :Flow<User?>{
        return userDao.getUser(userId = userId)
    }

    override suspend fun authenticateUser(username: String, password: String): Flow<User?> {
        return userDao.getUserByCredentials(username = username, password = password)
    }

}