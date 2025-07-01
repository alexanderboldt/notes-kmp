package org.alex.repository.source

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String,
    val name: String
)
