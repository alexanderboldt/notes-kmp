package org.alex.repository.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val totalResults: Int,
    val articles: List<Article>
)
