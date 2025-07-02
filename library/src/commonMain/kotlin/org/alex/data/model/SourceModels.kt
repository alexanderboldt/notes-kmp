package org.alex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    val sources: List<Source>
)

@Serializable
data class Source(
    val id: String,
    val name: String
)
