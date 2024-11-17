package com.example.jetpackwithapiintegration.ui

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

object Global {
    fun createTextRequestBody(text: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), text)
    }

}