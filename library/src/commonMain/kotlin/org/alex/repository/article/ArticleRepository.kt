package org.alex.repository.article

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

@Inject
class ArticleRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getTopHeadlines(sources: List<String>) = client.getTopHeadlines(sources, userSettings.getAccessToken())
}
