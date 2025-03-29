package com.example.bbcnews.ui.mainScreen

import com.example.bbcnews.domain.model.News

sealed class NewsUiState {
    data object Loading : NewsUiState()
    data class Success(val news: News) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}