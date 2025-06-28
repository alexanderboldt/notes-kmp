package org.alex.repository

sealed class Result<out T> {
    data class Success<T>(val body: T) : Result<T>()
    data class Error(val statusCode: Int) : Result<Nothing>()
}