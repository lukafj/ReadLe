package com.example.readle.data

import ReadLeRepository

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

    override suspend fun getBook(isbn: String): Book {
        val book = bookDao.getBookByIsbn(isbn = isbn)
        if (book != null) {
            return book
        } else {
            //defaultBook
            return Book(
                isbn = "9780044403371",
                title = "The Hobbit",
                author_id = 1,
                description = "Join Bilbo as he embarks upon a journey to take back a kingdom, and a very important jewel, with twelve dwarves and a wizard named Gandalf the Grey.",
                imageUrl = "",
                publisher = "John Ronald Reuel Tolkien",
                amazonLink = "https://www.amazon.de/-/en/Hobbit-oder-zur%C3%BCck-Illustrationen-Alan/dp/3608938001?crid=TPUUP9RP80FL&dib=eyJ2IjoiMSJ9.zfcZtNi0k9V5TaT-s6XujR9RpbZ0MzysixVtBqD9eiRV4UtiNbnPz638pNlsM8zc-tMWoyDYElIJT8e6CQPy2vMUeTewZ4d-RiXMrFSQNx-x8EgM3_cF2TnuTtYHmGwvgFSE3CFBKMHLBFaQW8mxSXwF7NHj9pttTYh-qXeRbaM7u8-U7EsrFc4InDGTrXYxhJ7OxhOp6lLTVmxY0XSqy3osu6MP-MIx_0xTHH1Sxy8.xAxv-Z811mmEBXe9k6sFR-qp7xIli-Qixq-0OPnQ-GU&dib_tag=se&keywords=The+hobbit+hardcover&nsdOptOutParam=true&qid=1736628593&sprefix=the+hobbit+hardcove%2Caps%2C164&sr=8-1",
                rank = 1,
            )
        }
    }



    override suspend fun insertUser(user: User) {
        userDao.insertUser(user = user)
    }

    override suspend fun deleteUser(id: Int) {
        userDao.deleteUser(userId = id)
    }

    override suspend fun getUser(id: Int) {
        userDao.getUser(userId = id)
    }

    override suspend fun authenticateUser(username: String, password: String): User? {
        return userDao.getUserByCredentials(username = username, password = password)
    }

}