package com.example.readle

import android.util.Log


class Logger {

    fun debug(
        TAG: String,
        msg: String
    ) {
        Log.d(TAG, msg)
    }


    fun info(
        TAG: String,
        msg: String
    ) {
        Log.i(TAG, msg)
    }


    fun warn(
        TAG: String,
        msg: String
    ) {
        Log.w(TAG, msg)
    }


    fun error(
        TAG: String,
        msg: String
    ) {
        Log.e(TAG, msg)
    }
}

