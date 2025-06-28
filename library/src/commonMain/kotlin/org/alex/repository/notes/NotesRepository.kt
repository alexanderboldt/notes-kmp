package org.alex.repository.notes

import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import me.tatarka.inject.annotations.Inject
import org.alex.api.ApiClient
import org.alex.repository.Result
import org.alex.storage.UserSettings

@Inject
class NotesRepository(private val client: ApiClient, private val userSettings: UserSettings) {

    suspend fun getAll(): Result<List<Note>> {
        val response = client.getAllNotes(userSettings.getAccessToken())

        return if (response.status == HttpStatusCode.OK) {
            Result.Success(response.body())
        } else {
            Result.Error(response.status.value)
        }
    }
}
