package org.alex.configuration

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.alex.api.ApiClient
import org.alex.repository.article.ArticleRepository
import org.alex.repository.source.SourceRepository
import org.alex.storage.UserSettings

@Component
abstract class RepositoryInjection {
    @Provides
    fun apiClient() = ApiClient

    @Provides
    fun userSettings() = UserSettings()

    abstract val sourceRepository: SourceRepository

    abstract val articleRepository: ArticleRepository
}
