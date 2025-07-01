package org.alex.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.alex.repository.article.ArticleResponse
import org.alex.repository.Result
import org.alex.repository.source.SourceResponse

object ApiClient {

    private const val BASE_URL = "https://newsapi.org/v2/"
    private const val SOURCES_URL = "top-headlines/sources"
    private const val TOP_HEADLINES_URL = "top-headlines"

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = false
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }

        defaultRequest {
            url(BASE_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    suspend fun getSources(apiKey: String) = client.get(SOURCES_URL) {
        parameter("language", "de")
        parameter("apiKey", apiKey)
    }.toResult<SourceResponse>()

    suspend fun getTopHeadlines(sources: List<String>, apiKey: String) = client.get(TOP_HEADLINES_URL) {
        parameter("sources", sources.joinToString(","))
        parameter("apiKey", apiKey)
    }.toResult<ArticleResponse>()
}

private suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return if (status.isSuccess()) {
        Result.Success(body<T>())
    } else {
        Result.Error(status.value)
    }
}
