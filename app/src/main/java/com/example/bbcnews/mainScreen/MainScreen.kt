package com.example.bbcnews.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bbcnews.model.NewsUiState
import com.example.bbcnews.model.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: NewsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(70.dp),
                title = { Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "BBC News") }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (uiState) {
                    is NewsUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is NewsUiState.Success -> {
                        val news = (uiState as NewsUiState.Success).news
                        Text(text = news.toString())
                    }

                    is NewsUiState.Error -> {
                        Text(text = "Error: ${(uiState as NewsUiState.Error).message}")
                    }
                }
            }
        }
    }
}