package com.yarendemirkaya.core

sealed class ResponseState<out T> {
    data class Error(val message: String) : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
}