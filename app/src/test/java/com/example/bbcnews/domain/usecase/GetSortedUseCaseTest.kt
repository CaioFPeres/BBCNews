package com.example.bbcnews.domain.usecase

import kotlinx.coroutines.test.runTest
import model.Article
import model.Source
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import usecase.GetDateSortedUseCase
import java.time.format.DateTimeParseException

class GetDateSortedUseCaseTest {
    private lateinit var getDateSortedNewsUseCase: GetDateSortedUseCase

    @Before
    fun setUp() {
        getDateSortedNewsUseCase = GetDateSortedUseCase()
    }

    @Test
    fun `invoke should handle empty list`() = runTest {
        val articles = emptyList<Article>()
        val sorted = getDateSortedNewsUseCase(articles)

        assertEquals(articles, sorted)
    }

    @Test
    fun `invoke should throw DateTimeParseException when date is invalid`() = runTest {
        val articles = listOf(
            createArticle("Invalid date 1"),
            createArticle("Invalid date 2"),
            createArticle("Invalid date 3")
        )

        assertThrows(DateTimeParseException::class.java) {
            val sorted = getDateSortedNewsUseCase(articles)
            print(sorted)
        }
    }

    @Test
    fun `invoke should sort articles by date in ascending order`() = runTest {
        val articles = listOf(
            createArticle("2024-03-27T15:00:00Z"),
            createArticle("2024-03-27T12:00:00Z"),
            createArticle("2024-03-27T18:00:00Z")
        )

        val sortedArticles = getDateSortedNewsUseCase(articles)

        assertEquals("2024-03-27T18:00:00Z", sortedArticles[0].publishedAt)
        assertEquals("2024-03-27T15:00:00Z", sortedArticles[1].publishedAt)
        assertEquals("2024-03-27T12:00:00Z", sortedArticles[2].publishedAt)
    }

    private fun createArticle(date: String): Article {
        return Article(
            Source("bbc-news", "BBC News"),
            "Author",
            "Title",
            "Description",
            "url",
            "imageUrl",
            date,
            "Content"
        )
    }
}