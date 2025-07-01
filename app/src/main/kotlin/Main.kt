package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.configuration.RepositoryInjection
import org.alex.configuration.create
import org.alex.repository.notes.Note

fun main() = runBlocking {
    val inject = RepositoryInjection::class.create()

    val userRepository = inject.userRepository
    val notesRepository = inject.notesRepository

    // login
    userRepository
        .login("peter", "peter")
        .onSuccess { println("User successfully logged in!") }
        .onError { statusCode -> println("Could not log in! Statuscode: $statusCode") }

    // create a note
    notesRepository
        .create(Note(0, "Desktop aufräumen", "Tastatur und Maus nicht vergessen", 0, 0))
        .onSuccess { println("Deleted note!") }
        .onError { println("Could not create note!") }

    // get one note
    notesRepository
        .getOne(35)
        .onSuccess { note -> printNote(note) }
        .onError { println("Could not fetch one note!") }

    // update
    notesRepository
        .update(46, Note(0, "Desktop und Büro aufräumen", null, 0 , 0))
        .onSuccess { note -> printNote(note)  }
        .onError { println("Could not update note!") }

    // delete a note
    notesRepository
        .delete(40)
        .onSuccess { println("Deleted note!") }
        .onError { println("Could not delete note!") }

    // get all notes
    notesRepository
        .getAll()
        .onSuccess { notes ->
            notes.forEach { note ->
                printNote(note)
                println("---------------------------")
            }
        }.onError { println("Could not fetch notes!") }

    return@runBlocking
}

private fun printNote(note: Note) {
    println(note.id)
    println(note.title)
    println(note.description)
    println(note.createdAt)
    println(note.updatedAt)
}
