package org.alex.repository.user

import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

class UserRepository {

    private val client: ApiClient = ApiClient

    private val userSettings = UserSettings()

    suspend fun login(username: String, password: String): Boolean {
        val response = client.login(username, password)

        return if (response.status == HttpStatusCode.OK) {
            userSettings.putAccessToken(response.body<User>().accessToken)
            true
        } else {
            false
        }
    }
}
