package com.example.bbcnews.data.repository

import com.example.bbcnews.data.remote.NewsAPI
import com.example.bbcnews.data.remote.RetrofitClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryImplTest {
    private lateinit var retrofitClient: RetrofitClient
    private lateinit var newsApi: NewsAPI
    private lateinit var repositoryImpl: NewsRepositoryImpl
    private val url = "https://newsapi.org/v2/"

    @Before
    fun setUp() {
        retrofitClient = RetrofitClient(url)
        newsApi = retrofitClient.create(NewsAPI::class.java)
        repositoryImpl = NewsRepositoryImpl(retrofitClient)
    }

    @Test
    fun `getNewsData should return appropriate news data when call is successful`() = runBlocking {
        val news = repositoryImpl.getNewsData()
        assert(Response.success(news).isSuccessful)
    }

    @Test(expected = Exception::class)
    fun `getNewsData should throw exception when api call fails`(): Unit = runBlocking {
        val _url = url + "~"
        retrofitClient = RetrofitClient(_url)
        newsApi = retrofitClient.create(NewsAPI::class.java)
        repositoryImpl = NewsRepositoryImpl(retrofitClient)

        assertThrows(Exception::class.java) {
            GlobalScope.launch {
                repositoryImpl.getNewsData()
            }
        }
    }
}