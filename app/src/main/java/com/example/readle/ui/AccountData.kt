package com.example.readle.ui

import android.media.Image

data class AccountData(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val profilePicture: Image?
)
