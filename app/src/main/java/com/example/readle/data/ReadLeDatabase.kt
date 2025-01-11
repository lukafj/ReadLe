package com.example.readle.data


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.readle.R


@Database(entities = [User::class, Favorite::class, Book::class, Author::class], version = 1)
abstract class ReadLeDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao

    companion object {

        @Volatile
        private var INSTANCE: ReadLeDatabase? = null

        fun getDatabase(context: Context): ReadLeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, ReadLeDatabase::class.java, "readle_database")
                    .build()
                    .also { INSTANCE = it }
            }

        }
    }


}