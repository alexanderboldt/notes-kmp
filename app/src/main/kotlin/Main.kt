package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.configuration.RepositoryInjection
import org.alex.configuration.create
import org.alex.repository.Result
import org.alex.repository.notes.Note

fun main() = runBlocking {
    val inject = RepositoryInjection::class.create()

    val userRepository = inject.userRepository
    val notesRepository = inject.notesRepository

    // login
    val resultLogin = userRepository.login("peter", "peter")

    if (resultLogin is Result.Error) {
        println("Could not log in!")
        return@runBlocking
    }

    // create a note
    val resultNoteCreate = notesRepository.create(Note(0, "Desktop aufräumen", "Tastatur und Maus nicht vergessen", 0, 0))

    if (resultNoteCreate is Result.Error) {
        println("Could not create note!")
        return@runBlocking
    }

    resultNoteCreate as Result.Success
    printNote(resultNoteCreate.body)

    // get one note
    val resultNote = notesRepository.getOne(35)

    if (resultNote is Result.Error) {
        println("Could not fetch one note!")
        return@runBlocking
    }

    resultNote as Result.Success
    printNote(resultNote.body)

    // update
    val resultNoteUpdated = notesRepository.update(46, Note(0, "Desktop und Büro aufräumen", null, 0 , 0))

    if (resultNoteUpdated is Result.Error) {
        println("Could not update note!")
        return@runBlocking
    }

    resultNoteUpdated as Result.Success
    printNote(resultNote.body)

    // delete a note
    val resultNoteDeleted = notesRepository.delete(40)

    if (resultNoteDeleted is Result.Error) {
        println("Could not delete note!")
        return@runBlocking
    }

    // get all notes
    val resultNotes = notesRepository.getAll()

    if (resultNotes is Result.Error) {
        println("Could not fetch notes!")
        return@runBlocking
    }

    resultNotes as Result.Success
    resultNotes.body.forEach { note ->
        printNote(note)
        println("---------------------------")
    }
}

fun printNote(note: Note) {
    println(note.id)
    println(note.title)
    println(note.description)
    println(note.createdAt)
    println(note.updatedAt)
}
