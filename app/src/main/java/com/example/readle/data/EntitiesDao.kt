package com.example.readle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE user_id = :userId")
    suspend fun getUser(userId: Int): User?

    @Query("DELETE FROM users WHERE user_id = :userId")
    suspend fun deleteUser(userId: Int)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUserByCredentials(username: String, password: String): User?
}

@Dao
interface FavoriteDao {

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE user_id = :userId")
    suspend fun getFavoritesByUserId(userId: Int): List<Favorite>
}

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM book WHERE isbn = :isbn")
    suspend fun getBookByIsbn(isbn: String): Book?

    @Delete
    suspend fun deleteBook(book: Book)
}


@Dao
interface AuthorDao {

    @Insert
    suspend fun insertAuthor(author: Author)

    @Query("SELECT * FROM author WHERE author_id = :authorId")
    suspend fun getAuthorById(authorId: Int): Author?
}
