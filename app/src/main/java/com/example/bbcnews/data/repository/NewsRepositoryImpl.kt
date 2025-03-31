package com.example.bbcnews.data.repository

import com.example.bbcnews.data.remote.NewsAPI
import com.example.bbcnews.data.remote.RetrofitClient
import model.News
import repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NewsRepositoryImpl(
    private val retrofitClient: RetrofitClient
) : NewsRepository {
    private val newsApi = retrofitClient.create(NewsAPI::class.java)

    override suspend fun getNewsData(): News = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val call = newsApi.getNews()

            call?.enqueue(object : retrofit2.Callback<News?> {
                override fun onResponse(call: retrofit2.Call<News?>, response: retrofit2.Response<News?>) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body()!!)
                    } else {
                        continuation.resumeWithException(Exception("API error: ${response.code()}")) //Won't crash on error
                    }
                }

                override fun onFailure(call: retrofit2.Call<News?>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}