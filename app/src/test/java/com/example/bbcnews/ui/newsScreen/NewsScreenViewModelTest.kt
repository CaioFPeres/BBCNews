package com.example.bbcnews.ui.newsScreen

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import model.Article
import model.Source
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import usecase.ReplaceContentWithUrlUseCase

class NewsScreenViewModelTest {

    private lateinit var viewModel: NewsScreenViewModel

    @Before
    fun setup() {
        viewModel = NewsScreenViewModel()
    }

    @Test
    fun `assignArticle updates uiState with modified content`() = runTest {
        // Given
        val article = Article(
            source = Source("bbc-news", "BBC News"),
            author = "Author",
            title = "Title",
            description = "Description",
            url = "url",
            urlToImage = "imageUrl",
            publishedAt = "2024-03-27T12:00:00Z",
            content = "Some content [+ more content"
        )
        val expectedContent = ReplaceContentWithUrlUseCase()(article.content)

        // When
        viewModel.assignArticle(article)

        // Then
        val result = viewModel.uiState.first()

        assertEquals(expectedContent, result?.content)
    }

    @Test
    fun `assignArticle does not update uiState if article is the same`() = runTest {
        // Given
        val article1 = Article(
            source = Source("bbc-news", "BBC News"),
            author = "Author",
            title = "Title",
            description = "Description",
            url = "url",
            urlToImage = "imageUrl",
            publishedAt = "2024-03-27T12:00:00Z",
            content = "Some content more content"
        )

        val article2 = Article(
            source = Source("bbc-news", "BBC News"),
            author = "Author",
            title = "Title",
            description = "Description",
            url = "url",
            urlToImage = "imageUrl",
            publishedAt = "2024-03-27T12:00:00Z",
            content = "Some content more content"
        )

        viewModel.assignArticle(article1)

        // When
        viewModel.assignArticle(article2)

        // Then
        val result = viewModel.uiState.first()
        assertEquals(article1.hashCode(), result?.hashCode())
    }
}
