package com.example.bbcnews.ui.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.domain.model.News
import com.example.bbcnews.domain.usecase.GetDateSortedNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

class MainScreenViewModel(private val repository: NewsRepositoryImpl) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                _uiState.value = NewsUiState.Loading
                val news = repository.getNewsData()
                news.articles = GetDateSortedNewsUseCase()(news.articles)
                _uiState.value = NewsUiState.Success(news)
            } catch (e: Exception) {
                _uiState.value = NewsUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}