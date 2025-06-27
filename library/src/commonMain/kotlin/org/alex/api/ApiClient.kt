package org.alex.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Manages the connection to the backend and contains the individual routes.
 */
internal object ApiClient {

    private val KEYCLOAK_URL = "http://localhost:8080/realms/notes/protocol/openid-connect/token"
    private val KEYCLOAK_CLIENT_ID = "notes-client"
    private val KEYCLOAK_CLIENT_SECRET = "WCV8MP3xIySAmFU7yBucXYIKNbxeD404"
    private val BASE_URL = "http://localhost:9000/api/"
    private val NOTES = "v1/notes"

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = false
                    ignoreUnknownKeys = true
                }
            )
        }

        defaultRequest {
            url(BASE_URL)
            header("Client-Secret", "e4bbe5b7a4c1eb55652965aee885dd59bd2ee7f4")
        }
    }

    suspend fun login(username: String, password: String) = client.submitForm(
        KEYCLOAK_URL,
        parameters {
            append("client_id", KEYCLOAK_CLIENT_ID)
            append("client_secret", KEYCLOAK_CLIENT_SECRET)
            append("grant_type", "password")
            append("username", username)
            append("password", password)
        }
    )

    suspend fun getAllNotes(accessToken: String) = client.get(NOTES) {
        header(HttpHeaders.Authorization, "Bearer $accessToken")
    }
}
