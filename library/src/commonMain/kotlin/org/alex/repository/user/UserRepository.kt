package org.alex.repository.user

import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.repository.Result
import org.alex.storage.UserSettings

@Inject
class UserRepository (private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun login(username: String, password: String): Result<Unit> {
        val response = client.login(username, password)

        return if (response.status == HttpStatusCode.OK) {
            userSettings.putAccessToken(response.body<User>().accessToken)
            Result.Success(Unit)
        } else {
            Result.Error(response.status.value)
        }
    }
}
