package org.alex.di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.alex.data.network.ApiClient
import org.alex.data.repository.ArticleRepository
import org.alex.data.repository.SourceRepository
import org.alex.settings.UserSettings

@Component
abstract class DataInjection {
    @Provides
    internal fun apiClient() = ApiClient

    @Provides
    internal fun userSettings() = UserSettings()

    abstract val sourceRepository: SourceRepository

    abstract val articleRepository: ArticleRepository
}
