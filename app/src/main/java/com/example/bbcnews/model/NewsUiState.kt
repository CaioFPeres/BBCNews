package com.example.bbcnews.model

sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val news: News) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}