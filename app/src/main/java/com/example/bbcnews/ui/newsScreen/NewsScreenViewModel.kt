package com.example.bbcnews.ui.newsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.Article
import usecase.ReplaceContentWithUrlUseCase

class NewsScreenViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<Article?>(null)
    var uiState: StateFlow<Article?> = _uiState

    fun assignArticle(article: Article) {
        if (_uiState.value != article) {
            _uiState.value = article.copy(content = ReplaceContentWithUrlUseCase()(article.content))
        }
    }
}