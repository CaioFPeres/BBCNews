package repository

import model.News

interface NewsRepository {
    suspend fun getNewsData(): News
}