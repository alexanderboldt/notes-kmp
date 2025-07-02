package org.alex.data.network

sealed class Result<out T> {
    data class Success<T>(val body: T) : Result<T>()
    data class Error(val statusCode: Int) : Result<Nothing>()

    suspend fun onSuccess(block: suspend (body: T) -> Unit): Result<T> {
        if (this is Success) block(body)
        return this
    }

    suspend fun onError(block: suspend (statusCode: Int) -> Unit): Result<T> {
        if (this is Error) block(statusCode)
        return this
    }
}