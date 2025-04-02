package com.example.bbcnews.data.remote

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RetrofitClientTest {
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setUp() {
        val url = "https://newsapi.org/v2/"
        retrofitClient = RetrofitClient(url)
    }

    @Test
    fun `create should return non-null apiService'`() = runTest {
        val apiService1 = retrofitClient.create(NewsAPI::class.java)

        assertNotNull(apiService1)
    }

}
