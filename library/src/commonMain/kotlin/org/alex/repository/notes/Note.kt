package org.alex.repository.notes

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val title: String
)