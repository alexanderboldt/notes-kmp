package org.alex.repository.user

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.repository.Result
import org.alex.storage.UserSettings

@Inject
class UserRepository (private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun login(username: String, password: String): Result<User> {
        return client
            .login(username, password)
            .onSuccess { user -> userSettings.putAccessToken(user.accessToken) }
            .onError { userSettings.deleteAccessToken() }
    }
}
