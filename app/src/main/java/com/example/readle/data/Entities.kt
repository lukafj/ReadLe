package com.example.readle.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int,
    val username: String,
    val password: String,
    val email: String
)

@Entity(
    tableName = "favorite",
    primaryKeys = ["user_id", "isbn"],
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["user_id"]),
        ForeignKey(entity = Book::class, parentColumns = ["isbn"], childColumns = ["isbn"])
    ]
)
data class Favorite(
    val user_id: Int,
    val isbn: String
)

@Entity(tableName = "book")
data class Book(
    @PrimaryKey val isbn: String,
    val title: String,
    val author_id: Int,
    val description: String,
    val imageUrl: String,
    val publisher: String,
    val amazonLink: String,
    val rank: Int
)

@Entity(tableName = "author")
data class Author(
    @PrimaryKey(autoGenerate = true) val author_id: Int,
    val name: String,
    val surname: String,
    val birth_date: String
)