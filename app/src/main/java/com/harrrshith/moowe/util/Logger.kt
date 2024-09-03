package com.harrrshith.moowe.util

import android.util.Log

fun <T> T.showLogE(message: T?) {
    if (message == null) return
    val newMessage = when (message) {
        is String -> message
        is Int -> message.toString()
        else -> message.toString()
    }
    Log.e("$this", newMessage)
}