package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.configuration.RepositoryInjection
import org.alex.configuration.create
import org.alex.repository.Result

fun main() = runBlocking {
    val inject = RepositoryInjection::class.create()

    val userRepository = inject.userRepository
    val notesRepository = inject.notesRepository

    val resultLogin = userRepository.login("peter", "peter")

    if (resultLogin is Result.Error) {
        println("Could not log in!")
        return@runBlocking
    }

    val resultNotes = notesRepository.getAll()

    if (resultNotes is Result.Error) {
        println("Could not fetch notes!")
        return@runBlocking
    }

    resultNotes as Result.Success

    resultNotes.body.forEach { note ->
        println(note.id)
        println(note.title)
        println(note.description)
        println(note.createdAt)
        println(note.updatedAt)
        println("---------------------------")
    }
}
