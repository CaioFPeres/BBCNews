package com.example.bbcnews.ui.newsScreen

import model.News

sealed class NewsUiState {
    data object Loading : NewsUiState()
    data class Success(val news: News) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}