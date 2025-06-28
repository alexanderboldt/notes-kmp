package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.configuration.RepositoryInjection
import org.alex.configuration.create

fun main() = runBlocking {
    val inject = RepositoryInjection::class.create()

    val userRepository = inject.userRepository
    val notesRepository = inject.notesRepository

    userRepository.login("peter", "peter")

    val notes = notesRepository.getAll()

    notes.forEach { note ->
        println(note.id)
        println(note.title)
        println(note.description)
        println(note.createdAt)
        println(note.updatedAt)
        println("---------------------------")
    }

    println("notes: $notes")
}
