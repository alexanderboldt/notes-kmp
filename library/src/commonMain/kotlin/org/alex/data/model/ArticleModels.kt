package org.alex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val totalResults: Int,
    val articles: List<Article>
)

@Serializable
data class Article(
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String?
)
