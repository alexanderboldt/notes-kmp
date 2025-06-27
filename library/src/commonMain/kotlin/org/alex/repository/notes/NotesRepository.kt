package org.alex.repository.notes

import io.ktor.client.call.body
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

class NotesRepository {

    private val client: ApiClient = ApiClient

    private val userSettings = UserSettings()

    suspend fun getAll(): List<Note> {
        return client.getAllNotes(userSettings.getAccessToken()).body<List<Note>>()
    }
}
