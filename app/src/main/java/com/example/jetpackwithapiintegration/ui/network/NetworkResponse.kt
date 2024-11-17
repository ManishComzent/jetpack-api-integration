package com.example.jetpackwithapiintegration.ui.network

sealed class NetworkResponse<out T> {

    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Failure(val message: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()
}