package org.alex.repository.article

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String
)