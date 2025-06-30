package org.alex.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.alex.repository.notes.Note

/**
 * Manages the connection to the backend and contains the individual routes.
 */
object ApiClient {

    private const val KEYCLOAK_URL = "http://localhost:8080/realms/notes/protocol/openid-connect/token"
    private const val KEYCLOAK_CLIENT_ID = "notes-client"
    private const val KEYCLOAK_CLIENT_SECRET = "WCV8MP3xIySAmFU7yBucXYIKNbxeD404"
    private const val BASE_URL = "http://localhost:9000/api/"
    private const val NOTES_URL = "v1/notes"

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

    suspend fun createNote(accessToken: String, note: Note) = client.post(NOTES_URL) {
        bearerAuth(accessToken)
        setBody(note)
    }

    suspend fun getAllNotes(accessToken: String) = client.get(NOTES_URL) {
        bearerAuth(accessToken)
    }

    suspend fun getOne(accessToken: String, id: Int) = client.get("$NOTES_URL/$id") {
        bearerAuth(accessToken)
    }

    suspend fun update(accessToken: String, id: Int, note: Note) = client.put("$NOTES_URL/$id") {
        bearerAuth(accessToken)
        setBody(note)
    }

    suspend fun delete(accessToken: String, id: Int) = client.delete("$NOTES_URL/$id") {
        bearerAuth(accessToken)
    }
}
