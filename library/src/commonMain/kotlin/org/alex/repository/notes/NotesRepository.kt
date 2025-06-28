package org.alex.repository.notes

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.repository.Result
import org.alex.util.toResult
import org.alex.storage.UserSettings

@Inject
class NotesRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getAll(): Result<List<Note>> = client.getAllNotes(userSettings.getAccessToken()).toResult()
}
