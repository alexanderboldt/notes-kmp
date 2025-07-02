package org.alex.data.repository

import me.tatarka.inject.annotations.Inject
import org.alex.data.network.ApiClient
import org.alex.settings.UserSettings

@Inject
class SourceRepository internal constructor(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getSources() = client.getSources(userSettings.getAccessToken())
}