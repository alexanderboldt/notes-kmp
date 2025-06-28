package org.alex.repository.user

import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

@Inject
class UserRepository (private val client: ApiClient, private val userSettings: UserSettings) {

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
