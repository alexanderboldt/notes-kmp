package org.alex.configuration

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import org.alex.api.ApiClient
import org.alex.repository.notes.NotesRepository
import org.alex.repository.user.UserRepository
import org.alex.storage.UserSettings

@Component
abstract class RepositoryInjection {
    @Provides
    fun apiClient() = ApiClient

    @Provides
    fun userSettings() = UserSettings()

    abstract val userRepository: UserRepository

    abstract val notesRepository: NotesRepository
}
