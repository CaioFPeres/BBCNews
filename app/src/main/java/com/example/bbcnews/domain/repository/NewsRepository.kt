package com.example.bbcnews.domain.repository

import com.example.bbcnews.domain.model.News

interface NewsRepository {
    suspend fun getNewsData(): News
}