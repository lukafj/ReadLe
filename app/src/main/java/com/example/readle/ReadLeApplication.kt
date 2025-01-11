package com.example.readle

import AppContainer
import AppDataContainer
import android.app.Application
import com.example.readle.data.ReadLeDatabase

class ReadLeApplication: Application() {
    //The instance of AppContainer is used by other
    // classes to obtain dependencies.
    lateinit var repository: AppContainer

    override fun onCreate() {
        super.onCreate()
        val database = ReadLeDatabase.getDatabase(this)
        repository = AppDataContainer(this)
    }
}