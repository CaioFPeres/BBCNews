package com.example.bbcnews.model

import RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NewsRepository {
    private var apiService: NewsAPI = RetrofitClient()
        .create("https://newsapi.org/v2/", NewsAPI::class.java)

    suspend fun getNewsData(): News = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val call = apiService.getNews()

            call?.enqueue(object : retrofit2.Callback<News?> {
                override fun onResponse(call: retrofit2.Call<News?>, response: retrofit2.Response<News?>) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body()!!)
                    } else {
                        continuation.resumeWithException(Exception("API error: ${response.code()}"))
                    }
                }

                override fun onFailure(call: retrofit2.Call<News?>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}