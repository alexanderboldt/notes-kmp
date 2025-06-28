package org.alex.util

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.alex.repository.Result

suspend inline fun <reified T> HttpResponse.onSuccess(block: suspend (body: T) -> Unit): HttpResponse {
    if (status == HttpStatusCode.OK) block(body<T>())
    return this
}

suspend inline fun <reified T> HttpResponse.toResult(): org.alex.repository.Result<T> {
    return if (status == HttpStatusCode.OK) {
        org.alex.repository.Result.Success(body<T>())
    } else {
        Result.Error(status.value)
    }
}
