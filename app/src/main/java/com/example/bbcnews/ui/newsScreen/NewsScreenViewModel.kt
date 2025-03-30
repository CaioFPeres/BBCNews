package com.example.bbcnews.ui.newsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.Article

class NewsScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<Article?>(null)
    var uiState: StateFlow<Article?> = _uiState

    fun assignArticle(article: Article) {
        if(_uiState.value != article) {
            _uiState.value = article
            replaceWithUrl()
        }
    }

    private fun replaceWithUrl() {
        val idx = _uiState.value!!.content.indexOf(" [+")
        if (idx != -1) {
            val replacement = " For the complete article, please visit: "
            val content = StringBuilder(_uiState.value!!.content)
            var k = 0
            for (i in idx..<idx + replacement.length) {
                if (i < content.length)
                    content[i] = replacement[k++]
                else
                    content.append(replacement[k++])
            }

            _uiState.value!!.content = content.toString()
        }
    }
}