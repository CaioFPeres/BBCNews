package com.example.bbcnews.data.remote
import model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// This is using the apiKey directly into the project code,
// which shouldn't be used in a development environment,
// specially when upload the code to a public repository.
// Instead, one should use BuildConfig by setting a API variable at gradle.properties.
// But I'm doing this way otherwise it would not run seemlessly (with one click).
// getNews parameter should have: @Query("apiKey") apiKey: String = BuildConfig.API_KEY
interface NewsAPI {
    @GET("top-headlines?")
    fun getNews(
        @Header("User-Agent") userAgent: String = "Mozilla/5.0", // needed for this API
        @Query("apiKey") apiKey: String = "5c1eb62ede6e4dbc88e217456d6f78cd"
    ): Call<News?>?
}