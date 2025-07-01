package org.alex.repository.source

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

@Inject
class SourceRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getSources() = client.getSources(userSettings.getAccessToken())
}
