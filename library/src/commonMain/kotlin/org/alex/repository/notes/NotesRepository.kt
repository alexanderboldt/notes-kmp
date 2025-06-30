package org.alex.repository.notes

import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.repository.Result
import org.alex.util.toResult
import org.alex.storage.UserSettings

@Inject
class NotesRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun create(note: Note): Result<Note> = client.createNote(userSettings.getAccessToken(), note).toResult()

    suspend fun getAll(): Result<List<Note>> = client.getAllNotes(userSettings.getAccessToken()).toResult()

    suspend fun getOne(id: Int): Result<Note> = client.getOne(userSettings.getAccessToken(), id).toResult()

    suspend fun update(id: Int, note: Note): Result<Note> = client.update(userSettings.getAccessToken(), id, note).toResult()

    suspend fun delete(id: Int): Result<Unit> = client.delete(userSettings.getAccessToken(), id).toResult()
}
