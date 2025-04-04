package com.example.bbcnews.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.ui.newsScreen.NewsUiState
import usecase.GetDateSortedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: NewsRepositoryImpl
) : ViewModel() {

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
                news.articles = GetDateSortedUseCase()(news.articles)
                _uiState.value = NewsUiState.Success(news)
            } catch (e: Exception) {
                _uiState.value = NewsUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}