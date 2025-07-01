package org.alex.repository.source

import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    val sources: List<Source>
)
