package com.example.bbcnews.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test
import usecase.ReplaceContentWithUrlUseCase

class ReplaceContentWithUrlUseCaseTest {
    private val useCase = ReplaceContentWithUrlUseCase()

    @Test
    fun `when content contains the substring should replace with url message`() {
        val content = "Some content [+ more content"
        val expected = "Some content For the complete article, please visit: "

        val result = useCase(content)
        assertEquals(expected, result)
    }

    @Test
    fun `when content doesn't contain the substring should return original content`() {
        val content = "Some content without marker"
        val result = useCase(content)
        assertEquals(content, result)
    }
}
