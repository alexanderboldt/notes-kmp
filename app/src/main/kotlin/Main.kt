package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.repository.notes.NotesRepository
import org.alex.repository.user.UserRepository

fun main() = runBlocking {
    val userRepository = UserRepository()
    val notesRepository = NotesRepository()

    userRepository.login("peter", "peter")

    val notes = notesRepository.getAll()

    notes.forEach { note ->
        println(note.id)
        println(note.title)
    }

    println("notes: $notes")
}