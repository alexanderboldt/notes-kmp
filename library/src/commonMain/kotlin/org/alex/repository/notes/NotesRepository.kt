package org.alex.repository.notes

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.storage.UserSettings

@Inject
class NotesRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun create(note: Note) = client.createNote(userSettings.getAccessToken(), note)

    suspend fun getAll() = client.getAllNotes(userSettings.getAccessToken())

    suspend fun getOne(id: Int) = client.getOne(userSettings.getAccessToken(), id)

    suspend fun update(id: Int, note: Note) = client.update(userSettings.getAccessToken(), id, note)

    suspend fun delete(id: Int) = client.delete(userSettings.getAccessToken(), id)
}
