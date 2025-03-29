package com.example.bbcnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bbcnews.mainScreen.MainScreen
import com.example.bbcnews.model.NewsRepository
import com.example.bbcnews.model.NewsViewModel
import com.example.bbcnews.ui.theme.BBCNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = NewsRepository()
        val viewModel = NewsViewModel(repository)

        enableEdgeToEdge()
        setContent {
            BBCNewsTheme {
                MainScreen(viewModel)
            }
        }
    }
}