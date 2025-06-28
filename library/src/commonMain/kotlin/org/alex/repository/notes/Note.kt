package org.alex.repository.notes

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: Int,
    val title: String,
    val description: String?,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)