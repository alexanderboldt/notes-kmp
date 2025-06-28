package org.alex.repository.notes

import io.ktor.client.call.body
import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

@Inject
class NotesRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getAll(): List<Note> {
        return client.getAllNotes(userSettings.getAccessToken()).body<List<Note>>()
    }
}
