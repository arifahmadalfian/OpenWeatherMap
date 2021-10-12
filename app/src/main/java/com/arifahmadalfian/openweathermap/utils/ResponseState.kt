package com.arifahmadalfian.openweathermap.utils

sealed class ResponseState<out T> {
    object Empty : ResponseState<Nothing>()
    object Loading : ResponseState<Nothing>()
    data class Success<T>(val data: T): ResponseState<T>()
    data class Error(val error: Throwable): ResponseState<Nothing>()
}