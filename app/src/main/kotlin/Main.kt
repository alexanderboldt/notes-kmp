package org.alex

import kotlinx.coroutines.runBlocking
import org.alex.di.DataInjection
import org.alex.di.create

fun main() = runBlocking {
    val inject = DataInjection::class.create()

    val sourceRepository = inject.sourceRepository
    val articleRepository = inject.articleRepository

    var sources = listOf<String>()

    sourceRepository
        .getSources()
        .onSuccess { response ->
            response.sources.forEach { source ->
                println(source.id)
                println(source.name)
                println()
            }
            sources = response.sources.map { it.id }
        }

    articleRepository
        .getTopHeadlines(sources)
        .onSuccess {
            it.articles.forEach { article ->
                println(article.author)
                println(article.title)
                println(article.description)
                println(article.urlToImage)
                println()
            }
        }

    return@runBlocking
}
