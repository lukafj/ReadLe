package com.example.readle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE user_id = :userId")
    fun getUser(userId: Int): Flow<User?>

    @Query("DELETE FROM users WHERE user_id = :userId")
    fun deleteUser(userId: Int)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByCredentials(username: String, password: String): Flow<User?>
}

@Dao
interface FavoriteDao {

    @Insert
     fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite WHERE user_id = :userId")
     fun getFavoritesByUserId(userId: Int): Flow<List<Favorite>>
}

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Query("SELECT * FROM book WHERE isbn = :isbn")
    fun getBookByIsbn(isbn: String): Book

    @Delete
    fun deleteBook(book: Book)
}


@Dao
interface AuthorDao {

    @Insert
    fun insertAuthor(author: Author)

    @Query("SELECT * FROM author WHERE author_id = :authorId")
    fun getAuthorById(authorId: Int): Flow<Author?>
}
